package com.ricardococati.carga.usecases.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.carga.usecases.CalculaCandlestickSemanalAsyncService;
import com.ricardococati.carga.usecases.CalculaCandlestickSemanalByDataService;
import com.ricardococati.carga.usecases.CalculaCandlestickSemanalService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculaCandlestickSemanalAsyncServiceImpl implements
    CalculaCandlestickSemanalAsyncService {

  private final CalculaCandlestickSemanalService semanalService;
  private final CalculaCandlestickSemanalByDataService semanalServiceByData;
  private final TaskExecutor taskExecutor;

  @Override
  public void execute() throws Exception {
    executeAsynchronously(null);
  }

  @Override
  public void executeByData(final LocalDate dtPregrao) throws Exception {
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
