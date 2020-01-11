package com.ricardococati.service;

import com.ricardococati.model.dto.CandlestickDiario;
import com.ricardococati.model.dto.CandlestickSemanal;
import java.util.List;

public interface BuildCandlestickSemanalService {

  CandlestickSemanal build(final List<CandlestickDiario> candlestickDiarios);

}
