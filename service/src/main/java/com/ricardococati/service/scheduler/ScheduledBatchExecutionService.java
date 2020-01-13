package com.ricardococati.service.scheduler;

import com.ricardococati.model.enums.CaminhoArquivoEnum;
import com.ricardococati.service.CalculaCandlestickSemanalService;
import com.ricardococati.service.IntegrationService;
import com.ricardococati.service.util.ControlaIdArquivoUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledBatchExecutionService {

  private final IntegrationService service;
  private final CalculaCandlestickSemanalService candlestickSemanalService;
  private final ControlaIdArquivoUtil idArquivoUtil;
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

  @Scheduled(cron = "0 0/1 * * * *")
  public void executaAgendador() {
    log.info("Inicia execução PROCESSO BATCH em " + sdf.format(new Date()));
    try {
      if (existemArquivosNoDiretorio()) {
        executaGeracaoCandleDiario();
      } else {
        executaGeracaoCandleSemanal();
      }
    } catch (Exception e) {
      log.error(" Causa: " + e.getCause() + " Mensagem de Erro: " + e.getMessage());
    }
    log.info("Termina execução PROCESSO BATCH em " + sdf.format(new Date()));
  }

  private Boolean existemArquivosNoDiretorio() {
    final String caminho = CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho();
    File arquivosDiretorioOrigem = new File(caminho);
    File arrayArquivos[] = arquivosDiretorioOrigem.listFiles();
    if (arquivosDiretorioOrigem.exists() && arrayArquivos.length > 0) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  private void executaGeracaoCandleDiario() throws Exception {
    idArquivoUtil.setIdentificadorArquivo(getIdArquivo());
    service.execute();
  }

  private Long getIdArquivo() throws Exception {
    return service.getIdArquivoSequence("ARQUIVO_SEQ");
  }

  private void executaGeracaoCandleSemanal() throws Exception {
    log.info("Inicia cálculo semanal");
    //candlestickSemanalService.execute(LocalDate.now());
  }

}
