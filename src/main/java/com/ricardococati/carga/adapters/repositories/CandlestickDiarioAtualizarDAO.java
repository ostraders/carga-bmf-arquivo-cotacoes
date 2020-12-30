package com.ricardococati.carga.adapters.repositories;

import com.ricardococati.carga.entities.domains.SplitInplit;

public interface CandlestickDiarioAtualizarDAO {

  Boolean atualizaSplitInplit(final SplitInplit splitInplit) throws Exception;

}