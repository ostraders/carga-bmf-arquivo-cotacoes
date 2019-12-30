package com.ricardococati.service.impl;

import com.ricardococati.model.dto.SplitInplit;
import com.ricardococati.repository.dao.CandlestickDiarioDAO;
import com.ricardococati.repository.dao.CandlestickSemanalDAO;
import com.ricardococati.repository.dao.CotacaoDAO;
import com.ricardococati.service.SplitInplitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SplitInplitServiceImpl implements SplitInplitService {

  private final CotacaoDAO cotacaoDAO;
  private final CandlestickDiarioDAO candlestickDiarioDAO;
  private final CandlestickSemanalDAO candlestickSemanalDAO;

  @Override
  public Boolean split(SplitInplit splitInplit) {
    Boolean retorno = Boolean.FALSE;
    if(cotacaoDAO.split(splitInplit)){
      retorno = candlestickDiarioDAO.split(splitInplit);
      if(retorno){
        retorno = candlestickSemanalDAO.split(splitInplit);
      }
    }
    return retorno;
  }

  @Override
  public Boolean inplit(SplitInplit splitInplit) {
    Boolean retorno = Boolean.FALSE;
    if(cotacaoDAO.inplit(splitInplit)){
      retorno = candlestickDiarioDAO.inplit(splitInplit);
        if(retorno) {
          retorno = candlestickSemanalDAO.inplit(splitInplit);
        }
      }
    return retorno;
  }
}
