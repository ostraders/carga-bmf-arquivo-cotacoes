package com.ricardococati.carga.adapters.repositories;

import com.ricardococati.carga.entities.domains.SplitInplit;

public interface CandlestickSemanalAtualizarDAO {

  Boolean atualizaSplitInplit(final SplitInplit splitInplit) throws Exception;

}
