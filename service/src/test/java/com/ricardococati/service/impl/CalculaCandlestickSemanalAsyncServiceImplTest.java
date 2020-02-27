package com.ricardococati.service.impl;

import com.ricardococati.service.CalculaCandlestickSemanalByDataService;
import com.ricardococati.service.CalculaCandlestickSemanalService;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.task.TaskExecutor;

@RunWith(MockitoJUnitRunner.class)
public class CalculaCandlestickSemanalAsyncServiceImplTest {

  @InjectMocks
  private CalculaCandlestickSemanalAsyncServiceImpl target;
  @Mock
  private CalculaCandlestickSemanalService semanalService;
  @Mock
  private CalculaCandlestickSemanalByDataService semanalServiceByData;
  @Mock
  private TaskExecutor taskExecutor;

  @Test
  public void execute() throws Exception {
    target.execute();
  }

  @Test
  public void executeByData() throws Exception {
    target.executeByData(LocalDate.now());
  }
}