package com.ricardococati.service.impl;

import com.ricardococati.model.entities.SplitInplit;
import com.ricardococati.repository.dao.AtualizarCandlestickDiarioDAO;
import com.ricardococati.repository.dao.AtualizarCandlestickSemanalDAO;
import com.ricardococati.service.AtualizarCandlesticksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarCandlesticksServiceImpl implements AtualizarCandlesticksService {

  private final AtualizarCandlestickDiarioDAO atualizarDiarioDAO;
  private final AtualizarCandlestickSemanalDAO atualizarSemanalDAO;

  @Override
  public Boolean executeSplitInplit(final SplitInplit splitInplit) {
    Boolean retorno = atualizarDiarioDAO.atualizaSplitInplit(splitInplit);
    if (retorno) {
      retorno = atualizarSemanalDAO.atualizaSplitInplit(splitInplit);
    }
    return retorno;
  }

}
