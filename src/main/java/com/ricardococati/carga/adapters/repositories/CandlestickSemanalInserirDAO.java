package com.ricardococati.carga.adapters.repositories;

import com.ricardococati.carga.entities.domains.CandlestickSemanal;

public interface CandlestickSemanalInserirDAO {

  Boolean incluirCandlestickSemanal(final CandlestickSemanal semanal) throws Exception;

}
