package com.ricardococati.carga.adapters.repositories.candlestick;

import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
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
