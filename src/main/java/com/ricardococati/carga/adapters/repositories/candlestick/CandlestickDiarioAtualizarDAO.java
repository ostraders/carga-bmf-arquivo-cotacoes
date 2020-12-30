package com.ricardococati.carga.adapters.repositories.candlestick;

import com.ricardococati.carga.entities.domains.splitinplit.SplitInplit;

public interface CandlestickDiarioAtualizarDAO {

  Boolean atualizaSplitInplit(final SplitInplit splitInplit) throws Exception;

}