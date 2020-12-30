package com.ricardococati.carga.usecases.candlestick;

import java.time.LocalDate;

public interface CalculaCandlestickSemanalByDataService {

  void execute(final LocalDate dataOrigem) throws Exception;

}
