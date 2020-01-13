package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.CandlestickSemanal;
import java.time.LocalDate;
import java.util.List;

public interface CandlestickSemanalDAO {

  List<CandlestickSemanal> buscarCandleSemanalPorPrimeiroDiaSemana(
      final LocalDate obterPrimeiroDiaUtilSemanaCorrente
  );

}
