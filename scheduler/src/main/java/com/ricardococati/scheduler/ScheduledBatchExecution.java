package com.ricardococati.scheduler;

import com.ricardococati.model.enums.CaminhoArquivoEnum;
import com.ricardococati.service.ICalculaCandlestickSemanalService;
import com.ricardococati.service.IIntegrationService;
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
public class ScheduledBatchExecution {

  private final IIntegrationService service;
  private final ICalculaCandlestickSemanalService candlestickSemanalService;
  private final ControlaIdArquivoUtil idArquivoUtil;
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

  @Scheduled(cron = "0 0/1 * * * *")
  public void executaAgendador() {
    log.info("Inicia execução PROCESSO BATCH em " + sdf.format(new Date()));
    try {
      File arquivosDiretorioOrigem = new File(
          CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho());
      File arrayArquivos[] = arquivosDiretorioOrigem.listFiles();
      if (arquivosDiretorioOrigem.exists() && arrayArquivos.length > 0) {
        idArquivoUtil.setIdentificadorArquivo(service.getIdArquivoSequence("ARQUIVO_SEQ"));
        service.execute();
      } else {
        final Integer size = candlestickSemanalService.contaCandlestickDiarioSemanaGeradaFalse();
        if(size > 0) {
          log.info("Inicia cálculo semanal");
          candlestickSemanalService.execute();
        }
      }
    } catch (Exception e) {
      log.error(" Causa: " + e.getCause() + " Mensagem de Erro: " + e.getMessage());
    }
    log.info("Termina execução PROCESSO BATCH em " + sdf.format(new Date()));
  }

}
