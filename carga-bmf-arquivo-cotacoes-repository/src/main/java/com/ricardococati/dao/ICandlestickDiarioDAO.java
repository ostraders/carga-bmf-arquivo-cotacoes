package com.ricardococati.dao;

import com.ricardococati.dto.CandlestickDiarioDTO;

public interface ICandlestickDiarioDAO {

  boolean incluirCandlestickDiario(final CandlestickDiarioDTO candlestickDiarioDTO) throws Exception;

}
