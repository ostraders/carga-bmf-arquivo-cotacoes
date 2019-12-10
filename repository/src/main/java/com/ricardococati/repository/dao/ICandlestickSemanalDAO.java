package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.CandlestickSemanalDTO;
import com.ricardococati.model.dto.SplitInplit;

public interface ICandlestickSemanalDAO {

  Integer contaCandleDiarioSemCandleSemanalGerado();

  Boolean split(SplitInplit splitInplit);

  Boolean inplit(SplitInplit splitInplit);

  Boolean salvaCandlestickSemanal(final CandlestickSemanalDTO semanal);

}
