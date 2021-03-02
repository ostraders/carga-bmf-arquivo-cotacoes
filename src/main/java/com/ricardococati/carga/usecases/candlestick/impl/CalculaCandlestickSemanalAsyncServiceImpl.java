package com.ricardococati.carga.usecases.candlestick.impl;

import com.ricardococati.carga.usecases.candlestick.CalculaCandlestickSemanalAsyncService;
import com.ricardococati.carga.usecases.candlestick.CalculaCandlestickSemanalService;
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
  private final TaskExecutor taskExecutor;

  @Override
  public void execute() throws Exception {
    taskExecutor.execute(() -> {
      log.info("Inicia task calculaSemanal");
      try {
        semanalService.execute();
      } catch (Exception e) {
        log.error("Erro ao executar thread: {}", e.getMessage());
      }
      log.info("Termina task calculaSemanal");
    });
  }

}
