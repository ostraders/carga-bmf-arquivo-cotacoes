package com.ricardococati.service;

import java.time.LocalDate;

public interface CalculaCandlestickSemanalAsyncService {

	void execute(LocalDate dataOrigem) throws Exception;

}
