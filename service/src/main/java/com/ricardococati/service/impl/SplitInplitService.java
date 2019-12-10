package com.ricardococati.service.impl;

import com.ricardococati.model.dto.SplitInplit;
import com.ricardococati.repository.dao.ICandlestickDiarioDAO;
import com.ricardococati.repository.dao.ICandlestickSemanalDAO;
import com.ricardococati.repository.dao.ICotacaoDAO;
import com.ricardococati.service.ISplitInplitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SplitInplitService implements ISplitInplitService {

  private final ICotacaoDAO cotacaoDAO;
  private final ICandlestickDiarioDAO candlestickDiarioDAO;
  private final ICandlestickSemanalDAO candlestickSemanalDAO;

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
