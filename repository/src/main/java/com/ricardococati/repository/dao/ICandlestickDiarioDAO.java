package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.CandlestickDiarioDTO;
import com.ricardococati.model.dto.SplitInplit;
import java.util.List;

public interface ICandlestickDiarioDAO {

  Boolean incluirCandlestickDiario(final CandlestickDiarioDTO candlestickDiarioDTO) throws Exception;

  Boolean split(SplitInplit splitInplit);

  Boolean inplit(SplitInplit splitInplit);

  List<String> buscaCodNegSemanaGeradaFalse();

  List<CandlestickDiarioDTO> buscaCandleDiarioPorCodNeg(String codneg);

  Boolean salvaCandlestickDiario(CandlestickDiarioDTO candlestickDiarioDTO);
}
