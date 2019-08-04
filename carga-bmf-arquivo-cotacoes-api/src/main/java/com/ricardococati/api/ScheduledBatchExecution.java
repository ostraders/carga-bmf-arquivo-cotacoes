package com.ricardococati.api;

import com.ricardococati.enums.CaminhoArquivoEnum;
import com.ricardococati.service.IBMFCargaService;
import com.ricardococati.service.ICandlestickService;
import com.ricardococati.service.IIntegrationService;
import com.ricardococati.service.impl.BMFCargaService;
import com.ricardococati.service.impl.CandlestickService;
import com.ricardococati.service.impl.IntegrationService;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScheduledBatchExecution {

  private final IIntegrationService service;
  private final IBMFCargaService cargaService;
  private final ICandlestickService candlestickService;
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
  public static final boolean SEMANA_GERADA = false;

  @Autowired
  public ScheduledBatchExecution(IntegrationService service, BMFCargaService cargaService, CandlestickService candlestickService) {
    this.service = service;
    this.cargaService = cargaService;
    this.candlestickService = candlestickService;
  }

  @Scheduled(cron = "0 0/1 * * * *")
  public void executaAgendador() {
    log.info("Inicia execução PROCESSO BATCH em " + sdf.format(new Date()));
    try {
      File arquivosDiretorioOrigem = new File(
          CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho());
      File arrayArquivos[] = arquivosDiretorioOrigem.listFiles();
      if (arquivosDiretorioOrigem.exists() && arrayArquivos.length > 0) {
        service.execute();
      } else {
        final int size = cargaService.listaCandlestickDiarioPorSemanaGerada(SEMANA_GERADA).size();
        if(size > 0) {
          System.out.println("Passou aqui: size == " + size);
          candlestickService.execute();
        }
      }
    } catch (Exception e) {
      log.error(" Causa: " + e.getCause() + " Mensagem de Erro: " + e.getMessage());
    }
    log.info("Termina execução PROCESSO BATCH em " + sdf.format(new Date()));
  }

}
