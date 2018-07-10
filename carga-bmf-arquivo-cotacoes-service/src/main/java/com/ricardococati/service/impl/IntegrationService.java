package com.ricardococati.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.StringTokenizer;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ricardococati.diretorios.IGerenciadorArquivos;
import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.enums.CaminhoArquivoEnum;
import com.ricardococati.service.IIntegrationService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
  private IGerenciadorArquivos gerenciadorArquivos;

  @Autowired
  private ArquivoDTO arquivoDTO;

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
            capturaTrackingID(arquivo);
            gerenciadorArquivos.moveArquivoParaDiretorio(diretorioExecucao, arquivo);
            executeProcessoBatch();
            gerenciadorArquivos.moverArquivosEntreDiretorios(
                CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO.getCaminho(),
                CaminhoArquivoEnum.CAMINHO_ARQUIVO_SAIDA.getCaminho());
          }
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

  /***
   * Metodo responsável por capturar informações de:
   *  - sender
   *  - receiver
   *  - doctype
   *  - trackingId
   * do nome do arquivo.
   */
  private void capturaTrackingID(File arquivo) throws Exception {
    try {
      StringTokenizer strToken = new StringTokenizer(arquivo.getName());
      arquivoDTO.setTamanhoArquivo(arquivo.length());
      arquivoDTO.setNomeArquivo(arquivo.getName());
      arquivoDTO.setSender(strToken.nextToken("@").toUpperCase());
      arquivoDTO.setReceiver(strToken.nextToken("@").toUpperCase());
      arquivoDTO.setDoctype(strToken.nextToken("@").toUpperCase());
      arquivoDTO.setTrackingID(strToken.nextToken().toUpperCase().substring(0, 15));
    } catch (Exception e) {
      log.error("Ocorreu um erro ao parsear o nome do arquivo " + "  -  " + e.getMessage());
      e.printStackTrace();
    }
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
