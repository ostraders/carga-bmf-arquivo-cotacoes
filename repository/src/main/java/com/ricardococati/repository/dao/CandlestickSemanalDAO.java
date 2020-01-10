package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.CandlestickSemanal;
import com.ricardococati.model.dto.SplitInplit;
import java.time.LocalDate;
import java.util.List;

public interface CandlestickSemanalDAO {

  Integer contaCandleDiarioSemCandleSemanalGerado();

  Boolean split(SplitInplit splitInplit);

  Boolean inplit(SplitInplit splitInplit);

  List<CandlestickSemanal> buscarCandleSemanalPorPrimeiroDiaSemana(
      final LocalDate obterPrimeiroDiaUtilSemanaCorrente
  );
}
