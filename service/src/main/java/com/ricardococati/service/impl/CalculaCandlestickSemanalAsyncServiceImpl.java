package com.ricardococati.service.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.service.CalculaCandlestickSemanalAsyncService;
import com.ricardococati.service.CalculaCandlestickSemanalByDataService;
import com.ricardococati.service.CalculaCandlestickSemanalService;
import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
public class CalculaCandlestickSemanalAsyncServiceImpl implements
    CalculaCandlestickSemanalAsyncService {

  private CalculaCandlestickSemanalService semanalService;
  private CalculaCandlestickSemanalByDataService semanalServiceByData;
  private TaskExecutor taskExecutor;

  @Autowired
  public CalculaCandlestickSemanalAsyncServiceImpl(
      @Qualifier("semanalService") CalculaCandlestickSemanalService semanalService,
      @Qualifier("semanalServiceByData") CalculaCandlestickSemanalByDataService semanalServiceByData,
      @Qualifier("taskExecutor") TaskExecutor taskExecutor) {
    this.semanalService = semanalService;
    this.semanalServiceByData = semanalServiceByData;
    this.taskExecutor = taskExecutor;
  }

  @Override
  public void execute() throws Exception {
    executeAsynchronously(null);
  }

  @Override
  public void executeByData(LocalDate dtPregrao) throws Exception {
    executeAsynchronously(dtPregrao);
  }

  public void executeAsynchronously(final LocalDate dtPregao) {
    taskExecutor.execute(() -> {
      log.info("Inicia task calculaSemanal");
      try {
        if(nonNull(dtPregao)) {
          semanalServiceByData.execute(dtPregao);
        } else{
          semanalService.execute();
        }
      } catch (Exception e) {
        log.error("Erro ao executar thread: {}", e.getMessage());
      }
      log.info("Termina task calculaSemanal");
    });
  }

}
