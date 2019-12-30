package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.CandlestickSemanal;
import com.ricardococati.model.dto.SplitInplit;

public interface CandlestickSemanalDAO {

  Integer contaCandleDiarioSemCandleSemanalGerado();

  Boolean split(SplitInplit splitInplit);

  Boolean inplit(SplitInplit splitInplit);

  Boolean salvaCandlestickSemanal(final CandlestickSemanal semanal);

}
