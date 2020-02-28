package com.ricardococati.service;

import java.time.LocalDate;

public interface CalculaCandlestickSemanalByDataService {

  void execute(final LocalDate dataOrigem) throws Exception;

}
