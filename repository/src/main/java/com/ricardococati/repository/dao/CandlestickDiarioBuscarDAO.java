package com.ricardococati.repository.dao;

import com.ricardococati.model.entities.CandlestickDiario;
import java.time.LocalDate;
import java.util.List;

public interface CandlestickDiarioBuscarDAO {

  List<String> buscaCodNeg() throws Exception;

  List<CandlestickDiario> buscaCandleDiarioPorCodNegSemanaGerada(String codneg) throws Exception;

  List<CandlestickDiario> buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(
      LocalDate primeiroDiaUtilSemanaCorrente,
      String codneg
  ) throws Exception;
}
