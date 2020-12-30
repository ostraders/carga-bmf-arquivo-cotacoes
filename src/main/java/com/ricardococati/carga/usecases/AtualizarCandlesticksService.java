package com.ricardococati.carga.usecases;

import com.ricardococati.carga.entities.domains.SplitInplit;

public interface AtualizarCandlesticksService {

  Boolean executeSplitInplit(final SplitInplit splitInplit) throws Exception;

}
