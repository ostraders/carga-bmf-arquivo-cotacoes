package com.ricardococati.carga.usecases;

import com.ricardococati.carga.usecases.candlestick.CalculaCandlestickSemanalService;
import com.ricardococati.carga.usecases.candlestick.impl.CalculaCandlestickSemanalAsyncServiceImpl;
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
  private TaskExecutor taskExecutor;

  @Test
  public void execute() throws Exception {
    target.execute();
  }

}