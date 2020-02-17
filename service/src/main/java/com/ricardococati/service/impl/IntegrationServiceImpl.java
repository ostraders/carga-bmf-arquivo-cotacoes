package com.ricardococati.service.impl;

import com.ricardococati.model.enums.CaminhoArquivoEnum;
import com.ricardococati.repository.dao.GeraSequenciaDAO;
import com.ricardococati.service.BMFCargaCotacaoService;
import com.ricardococati.service.GerenciadorArquivosService;
import com.ricardococati.service.IntegrationService;
import java.io.File;
import java.io.Serializable;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class IntegrationServiceImpl implements IntegrationService, Serializable {

  private static final long serialVersionUID = 25671121174988145L;

  @Qualifier("jobExecutionBatch")
  private final Job jobExecutionBatch;

  private final JobLauncher jobLauncher;

  private JobExecution execution;

  private final BMFCargaCotacaoService cargaService;

  private final GerenciadorArquivosService gerenciadorArquivos;

  private final GeraSequenciaDAO genericDAO;

  File diretorioExecucao = null;
  File arquivo = null;
  File diretorioSaida = null;

  @Override
  public void execute() throws Exception {
    try {
      movimentaArquivoEExecutaBatch();
    } catch (Exception e) {
      String mensagemErro = "Ocorreu um erro na execução do processo: {} ";
      log.error(mensagemErro, e.getMessage());
      throw e;
    }
  }

  private void movimentaArquivoEExecutaBatch() throws Exception {
    try {
      File arquivosDiretorioOrigem = new File(
          CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho());
      diretorioExecucao = new File(CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO.getCaminho());
      diretorioSaida = new File(CaminhoArquivoEnum.CAMINHO_ARQUIVO_SUCESSO.getCaminho());
      File arrayArquivos[] = arquivosDiretorioOrigem.listFiles();
      int i = 0;
      /** Move os arquivos para o diretório de execução 1 por vez **/
      if (arrayArquivos.length > 0) {
        for (int j = arrayArquivos.length; i < j; i++) {
          arquivo = arrayArquivos[i];
          if (gerenciadorArquivos.validarArquivoLock(arquivo)) {
            gerenciadorArquivos.moverArquivosEntreDiretorios(
                CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho(),
                CaminhoArquivoEnum.CAMINHO_ARQUIVO_SUCESSO.getCaminho());
            continue;
          }
          arquivo = gerenciadorArquivos.renomearArquivo(arquivo);
          gerenciadorArquivos.moveArquivoParaDiretorio(diretorioExecucao, arquivo);
          gerenciadorArquivos.moveArquivoParaDiretorio(diretorioExecucao, arquivo);
          executeProcessoBatch();
          gerenciadorArquivos.moverArquivosEntreDiretorios(
              CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO.getCaminho(),
              CaminhoArquivoEnum.CAMINHO_ARQUIVO_SUCESSO.getCaminho());
        }
      }
    } catch (Exception e) {
      String mensagemErro = "Ocorreu um erro no processamento do arquivo {} ";
      log.error(mensagemErro, e.getMessage());
      throw e;
    }
  }

  private void executeProcessoBatch() throws Exception {
    try {
      JobParameters param = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
          .toJobParameters();
      execution = jobLauncher.run(jobExecutionBatch, param);
      log.info("Exit Status : " + execution.getStatus());
    } catch (Exception e) {
      String mensagemErro = "Ocorreu um erro na execução do job do processo batch: {} ";
      log.error(mensagemErro, e.getMessage());
      throw e;
    }
  }

  @Override
  public Long getIdArquivoSequence(final String nomeSequence) {
    return genericDAO.getSequence(nomeSequence).longValue();
  }

}
