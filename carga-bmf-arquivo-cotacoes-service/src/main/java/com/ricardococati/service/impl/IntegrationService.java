package com.ricardococati.service.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.ricardococati.dto.CandlestickDiario;
import com.ricardococati.dto.CandlestickSemanal;
import com.ricardococati.enums.CaminhoArquivoEnum;
import com.ricardococati.service.IBMFCargaService;
import com.ricardococati.service.IGerenciadorArquivosService;
import com.ricardococati.service.IIntegrationService;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
public class IntegrationService implements IIntegrationService, Serializable {

  private static final long serialVersionUID = 25671121174988145L;

  @Autowired
  @Qualifier("jobExecutionBatch")
  private Job jobExecutionBatch;

  @Autowired
  private JobLauncher jobLauncher;

  private JobExecution execution;

  @Autowired
  private IBMFCargaService cargaService;

  @Autowired
  private IGerenciadorArquivosService gerenciadorArquivos;

  File diretorioExecucao = null;
  File arquivo = null;
  File diretorioSaida = null;
  private boolean arquivoValido = true;

  @Override
  public void execute() throws Exception {
    try {
      movimentaArquivoEExecutaBatch();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void movimentaArquivoEExecutaBatch() {
    try {
      File arquivosDiretorioOrigem = new File(
          CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho());
      diretorioExecucao = new File(CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO.getCaminho());
      diretorioSaida = new File(CaminhoArquivoEnum.CAMINHO_ARQUIVO_SAIDA.getCaminho());
      if (arquivosDiretorioOrigem.exists()) {
        File arrayArquivos[] = arquivosDiretorioOrigem.listFiles();
        int i = 0;
        /** Move os arquivos para o diretório de execução 1 por vez **/
        if (arrayArquivos.length > 0) {
          for (int j = arrayArquivos.length; i < j; i++) {
            arquivo = arrayArquivos[i];
            if (gerenciadorArquivos.validarArquivoLock(arquivo)) {
              return;
            }
            arquivo = gerenciadorArquivos.renomearArquivo(arquivo);
            gerenciadorArquivos.moveArquivoParaDiretorio(diretorioExecucao, arquivo);
            gerenciadorArquivos.moveArquivoParaDiretorio(diretorioExecucao, arquivo);
            executeProcessoBatch();
            gerenciadorArquivos.moverArquivosEntreDiretorios(
                CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO.getCaminho(),
                CaminhoArquivoEnum.CAMINHO_ARQUIVO_SAIDA.getCaminho());
          }
        } else {
          geraCandleStickSemanal();
        }
      } else {
        log.info("Diretórios não existem, por favor crie os diretórios!");
      }
    } catch (Exception e) {
      String mensagemErro = "Ocorreu um erro no processamento do arquivo ";
      log.error(mensagemErro + "  -  " + e.getMessage());
      e.printStackTrace();
    }
  }

  private void geraCandleStickSemanal() {
    cargaService
        .listEmpresas()
        .forEach(empresa -> {
          log.info("Nome empresa: " + empresa.getId());
          List<CandlestickDiario> candlestickList =
              cargaService
                  .listaCandlestickDiarioPorEmpresa(empresa.getId());
          Map<Integer, List<CandlestickDiario>> mapDiario =
              getListCandlestickToMap(candlestickList);
          mapDiario
              .entrySet()
              .forEach(integerEntry -> {
                CandlestickSemanal candlestickSemanal = calculaCandleStickPorSemana(
                    mapDiario.get(integerEntry.getKey()));
                candlestickSemanal.setSemana(integerEntry.getKey());
                candlestickSemanal.setNomres(empresa.getId());
                cargaService.salvaCandlestickSemanal(candlestickSemanal);
              });
          atualizaListaCandlestickDiarioSemanaGerada(candlestickList);
        });
  }

  private void atualizaListaCandlestickDiarioSemanaGerada(List<CandlestickDiario> candlestickList) {
    candlestickList
        .forEach(candlestickDiario -> {
          candlestickDiario.setSemanaGerada(true);
          cargaService.salvaCandlestickDiario(candlestickDiario);
        });
  }

  private CandlestickSemanal calculaCandleStickPorSemana(
      List<CandlestickDiario> candlestickDiarios) {
    CandlestickSemanal candlestickSemanal = new CandlestickSemanal();
    candlestickDiarios
        .forEach(candlestickDiario -> {
          candlestickSemanal.setDtpregini(calculaDtpregini(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setDtpregfim(calculaDtpregfim(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setPreabe(calculaPreabe(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setPremin(calculaPremin(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setPremax(calculaPremax(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setPreult(calculaPreult(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setVoltot(calculaVoltot(candlestickSemanal, candlestickDiario));
        });
    return candlestickSemanal;
  }

  private LocalDate calculaDtpregini(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getDtpregini()) ||
        candlestickDiario.getDtpreg().isBefore(candlestickSemanal.getDtpregini())) {
      candlestickSemanal.setDtpregini(candlestickDiario.getDtpreg());
    }
    return candlestickSemanal.getDtpregini();
  }

  private LocalDate calculaDtpregfim(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getDtpregfim()) ||
        candlestickDiario.getDtpreg().isAfter(candlestickSemanal.getDtpregfim())) {
      candlestickSemanal.setDtpregfim(candlestickDiario.getDtpreg());
    }
    return candlestickSemanal.getDtpregfim();
  }

  private BigDecimal calculaPreabe(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getPreabe()) ||
        candlestickSemanal.getDtpregini().isEqual(candlestickDiario.getDtpreg())) {
      candlestickSemanal.setPreabe(candlestickDiario.getPreabe());
    }
    return candlestickSemanal.getPreabe();
  }

  private BigDecimal calculaPremax(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if(isNull(candlestickSemanal.getPremax()) || candlestickDiario.getPremax().compareTo(candlestickSemanal.getPremax()) > 0){
      candlestickSemanal.setPremax(candlestickDiario.getPremax());
    }
    return candlestickSemanal.getPremax();
  }

  private BigDecimal calculaPremin(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if(isNull(candlestickSemanal.getPremin()) || candlestickDiario.getPremin().compareTo(candlestickSemanal.getPremin()) > 0){
      candlestickSemanal.setPremin(candlestickDiario.getPremin());
    }
    return candlestickSemanal.getPremin();
  }

  private BigDecimal calculaPreult(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getPreult()) ||
        candlestickSemanal.getDtpregfim().isEqual(candlestickDiario.getDtpreg())) {
      candlestickSemanal.setPreult(candlestickDiario.getPreult());
    }
    return candlestickSemanal.getPreult();
  }

  private BigDecimal calculaVoltot(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if(isNull(candlestickSemanal.getVoltot())) {
      candlestickSemanal.setVoltot(candlestickDiario.getVoltot());
    } else {
      candlestickSemanal.getVoltot().add(candlestickDiario.getVoltot());
    }
    return candlestickSemanal.getVoltot();
  }

  private Map<Integer, List<CandlestickDiario>> getListCandlestickToMap(
      List<CandlestickDiario> listcandlestickDiarios) {
    return listcandlestickDiarios
        .stream()
        .filter(candlestickDiario -> nonNull(candlestickDiario.getSemana()))
        .collect(Collectors.groupingBy(candlestickDiario -> candlestickDiario.getSemana()));
  }

  private void executeProcessoBatch() throws Exception {
    try {
      JobParameters param = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
          .toJobParameters();
      execution = jobLauncher.run(jobExecutionBatch, param);
      log.info("Exit Status : " + execution.getStatus());
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

}
