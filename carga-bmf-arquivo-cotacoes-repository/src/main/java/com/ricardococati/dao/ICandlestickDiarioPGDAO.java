package com.ricardococati.dao;

import com.ricardococati.dto.CandlestickDiarioDTO;

public interface ICandlestickDiarioPGDAO {

  boolean incluirCandlestickDiario(final CandlestickDiarioDTO candlestickDiarioDTO) throws Exception;

}
