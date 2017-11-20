package com.ricardococati.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ricardococati.service.IIntegrationService;
import com.ricardococati.service.impl.IntegrationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScheduledBatchExecution {

    private final IIntegrationService	 service;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    @Autowired
    public ScheduledBatchExecution(IntegrationService service) {
        this.service = service;
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void executaAgendador() {
        log.info("Inicia execução PROCESSO BATCH em " + sdf.format(new Date()));
        try {
            service.execute();
        }catch (Exception e){
            log.error(" Causa: " + e.getCause() + " Mensagem de Erro: " + e.getMessage());
        }
        log.info("Termina execução PROCESSO BATCH em " + sdf.format(new Date()));
    }

}
