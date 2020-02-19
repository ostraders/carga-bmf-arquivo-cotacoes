package com.ricardococati.service.impl;

import com.ricardococati.model.entities.SplitInplit;
import com.ricardococati.repository.dao.CandlestickDiarioAtualizarDAO;
import com.ricardococati.repository.dao.CandlestickSemanalAtualizarDAO;
import com.ricardococati.service.AtualizarCandlesticksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarCandlesticksServiceImpl implements AtualizarCandlesticksService {

  private final CandlestickDiarioAtualizarDAO atualizarDiarioDAO;
  private final CandlestickSemanalAtualizarDAO atualizarSemanalDAO;

  @Override
  public Boolean executeSplitInplit(final SplitInplit splitInplit) {
    Boolean retorno = atualizarDiarioDAO.atualizaSplitInplit(splitInplit);
    if (retorno) {
      retorno = atualizarSemanalDAO.atualizaSplitInplit(splitInplit);
    }
    return retorno;
  }

}
