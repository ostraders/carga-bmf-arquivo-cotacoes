package com.ricardococati.carga.usecases.impl;

import com.ricardococati.carga.adapters.repositories.CandlestickDiarioAtualizarDAO;
import com.ricardococati.carga.adapters.repositories.CandlestickSemanalAtualizarDAO;
import com.ricardococati.carga.entities.domains.SplitInplit;
import com.ricardococati.carga.usecases.AtualizarCandlesticksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarCandlesticksServiceImpl implements AtualizarCandlesticksService {

  private final CandlestickDiarioAtualizarDAO atualizarDiarioDAO;
  private final CandlestickSemanalAtualizarDAO atualizarSemanalDAO;

  @Override
  public Boolean executeSplitInplit(final SplitInplit splitInplit) throws Exception {
    Boolean retorno = atualizarDiarioDAO.atualizaSplitInplit(splitInplit);
    if (retorno) {
      retorno = atualizarSemanalDAO.atualizaSplitInplit(splitInplit);
    }
    return retorno;
  }

}
