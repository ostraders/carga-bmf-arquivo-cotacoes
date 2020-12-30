package com.ricardococati.carga.adapters.repositories;

import com.ricardococati.carga.entities.domains.CandlestickDiario;
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
