package com.ricardococati.carga.adapters.repositories.candlestick;

import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanal;

public interface CandlestickSemanalInserirDAO {

  Boolean incluirCandlestickSemanal(final CandlestickSemanal semanal) throws Exception;

}
