package com.ricardococati.service;

import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.model.entities.CandlestickSemanal;
import java.util.List;

public interface BuildCandlestickSemanalService {

  CandlestickSemanal build(final List<CandlestickDiario> candlestickDiarios);

}
