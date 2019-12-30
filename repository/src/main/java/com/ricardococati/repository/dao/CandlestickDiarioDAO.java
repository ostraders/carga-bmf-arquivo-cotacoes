package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.CandlestickDiario;
import com.ricardococati.model.dto.SplitInplit;
import java.util.List;

public interface CandlestickDiarioDAO {

  Boolean incluirCandlestickDiario(final CandlestickDiario candlestickDiarioDTO) throws Exception;

  Boolean split(SplitInplit splitInplit);

  Boolean inplit(SplitInplit splitInplit);

  List<String> buscaCodNegSemanaGeradaFalse();

  List<CandlestickDiario> buscaCandleDiarioPorCodNeg(String codneg);

  Boolean salvaCandlestickDiario(CandlestickDiario candlestickDiarioDTO);
}
