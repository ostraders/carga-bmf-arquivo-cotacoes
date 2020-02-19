package com.ricardococati.repository.dao;

import com.ricardococati.model.entities.CandlestickDiario;

public interface CandlestickDiarioInserirDAO {

  Boolean incluirCandlestickDiario(
      final CandlestickDiario candlestickDiarioDTO
  ) throws Exception;

}
