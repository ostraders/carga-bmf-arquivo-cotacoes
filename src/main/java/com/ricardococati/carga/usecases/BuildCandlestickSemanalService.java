package com.ricardococati.carga.usecases;

import com.ricardococati.carga.entities.domains.CandlestickDiario;
import com.ricardococati.carga.entities.domains.CandlestickSemanal;
import java.util.List;

public interface BuildCandlestickSemanalService {

  CandlestickSemanal build(final List<CandlestickDiario> candlestickDiarios);

}
