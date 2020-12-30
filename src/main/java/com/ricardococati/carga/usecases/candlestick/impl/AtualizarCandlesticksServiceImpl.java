package com.ricardococati.carga.usecases.candlestick.impl;

import com.ricardococati.carga.adapters.repositories.candlestick.CandlestickDiarioAtualizarDAO;
import com.ricardococati.carga.adapters.repositories.candlestick.CandlestickSemanalAtualizarDAO;
import com.ricardococati.carga.entities.domains.splitinplit.SplitInplit;
import com.ricardococati.carga.usecases.candlestick.AtualizarCandlesticksService;
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
