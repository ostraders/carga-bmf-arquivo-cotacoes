package com.ricardococati.repository.dao;

import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.model.entities.SplitInplit;
import java.time.LocalDate;
import java.util.List;

public interface CandlestickDiarioDAO {

  Boolean incluirCandlestickDiario(final CandlestickDiario candlestickDiarioDTO) throws Exception;

  List<String> buscaCodNeg();

  List<CandlestickDiario> buscaCandleDiarioPorCodNegSemanaGerada(String codneg);

  List<CandlestickDiario> buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(
      LocalDate primeiroDiaUtilSemanaCorrente,
      String codneg
  );
}
