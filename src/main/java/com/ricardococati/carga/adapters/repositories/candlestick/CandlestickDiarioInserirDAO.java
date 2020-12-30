package com.ricardococati.carga.adapters.repositories.candlestick;

import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;

public interface CandlestickDiarioInserirDAO {

  Boolean incluirCandlestickDiario(
      final CandlestickDiario candlestickDiarioDTO
  ) throws Exception;

}
