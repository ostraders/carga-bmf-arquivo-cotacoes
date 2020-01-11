package com.ricardococati.service.impl;

import com.ricardococati.service.CalculaCandlestickSemanalAsyncService;
import com.ricardococati.service.CalculaCandlestickSemanalService;
import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class CalculaCandlestickSemanalAsyncServiceImpl implements
    CalculaCandlestickSemanalAsyncService {

  private final CalculaCandlestickSemanalService semanalService;
  private final TaskExecutor taskExecutor;

  public void executeAsynchronously(final LocalDate dataOrigem) {
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

  @Override
  public void execute(LocalDate dataOrigem) throws Exception {
    executeAsynchronously(dataOrigem);
  }

}
