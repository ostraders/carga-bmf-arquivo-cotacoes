package com.ricardococati.carga.adapters.repositories;

import com.ricardococati.carga.entities.domains.CandlestickDiario;

public interface CandlestickDiarioInserirDAO {

  Boolean incluirCandlestickDiario(
      final CandlestickDiario candlestickDiarioDTO
  ) throws Exception;

}
