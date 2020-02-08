package com.ricardococati.repository.dao;

import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.model.entities.SplitInplit;
import java.time.LocalDate;
import java.util.List;

public interface CandlestickDiarioDAO {

  Boolean incluirCandlestickDiario(final CandlestickDiario candlestickDiarioDTO) throws Exception;

  Boolean split(SplitInplit splitInplit);

  Boolean inplit(SplitInplit splitInplit);

  List<String> buscaCodNeg();

  List<CandlestickDiario> buscaCandleDiarioPorCodNeg(String codneg);

  Boolean salvaCandlestickDiario(CandlestickDiario candlestickDiarioDTO);

  List<CandlestickDiario> buscaCandleDiarioPorCodNegSemanaGerada(String codneg);

  List<CandlestickDiario> buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(
      LocalDate primeiroDiaUtilSemanaCorrente,
      String codneg
  );
}
