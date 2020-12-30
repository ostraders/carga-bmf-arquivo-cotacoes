package com.ricardococati.carga.usecases.candlestick;

import com.ricardococati.carga.entities.domains.splitinplit.SplitInplit;

public interface AtualizarCandlesticksService {

  Boolean executeSplitInplit(final SplitInplit splitInplit) throws Exception;

}
