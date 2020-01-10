package com.ricardococati.service;

import com.ricardococati.model.dto.CandlestickSemanal;
import java.util.List;

public interface CalculaCandlestickSemanalService {

	void execute() throws Exception;

  void execute(final List<CandlestickSemanal> semanalList) throws Exception;

  Integer contaCandlestickDiarioSemanaGeradaFalse();
}
