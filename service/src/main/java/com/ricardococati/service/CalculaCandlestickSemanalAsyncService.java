package com.ricardococati.service;

import java.time.LocalDate;

public interface CalculaCandlestickSemanalAsyncService {

	void execute() throws Exception;

	void executeByData(final LocalDate dtPregrao) throws Exception;

}
