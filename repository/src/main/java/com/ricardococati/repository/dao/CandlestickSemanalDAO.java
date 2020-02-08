package com.ricardococati.repository.dao;

import com.ricardococati.model.entities.CandlestickSemanal;
import java.time.LocalDate;
import java.util.List;

public interface CandlestickSemanalDAO {

  List<CandlestickSemanal> buscarCandleSemanalPorPrimeiroDiaSemana(
      final LocalDate obterPrimeiroDiaUtilSemanaCorrente
  );

}
