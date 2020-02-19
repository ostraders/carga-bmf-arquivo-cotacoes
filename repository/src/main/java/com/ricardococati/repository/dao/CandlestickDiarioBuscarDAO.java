package com.ricardococati.repository.dao;

import com.ricardococati.model.entities.CandlestickDiario;
import java.time.LocalDate;
import java.util.List;

public interface CandlestickDiarioBuscarDAO {

  List<String> buscaCodNeg();

  List<CandlestickDiario> buscaCandleDiarioPorCodNegSemanaGerada(String codneg);

  List<CandlestickDiario> buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(
      LocalDate primeiroDiaUtilSemanaCorrente,
      String codneg
  );
}
