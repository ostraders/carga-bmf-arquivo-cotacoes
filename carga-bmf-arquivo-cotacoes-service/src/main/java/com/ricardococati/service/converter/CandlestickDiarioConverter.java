package com.ricardococati.service.converter;

import com.ricardococati.dto.CandlestickDiarioDTO;
import com.ricardococati.dto.Cotacao;
import com.ricardococati.enums.CodbdiEnum;
import com.ricardococati.enums.TipoMercadoEnum;
import com.ricardococati.util.Funcoes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CandlestickDiarioConverter {

  public CandlestickDiarioDTO convert(Cotacao cotacao){
    if(verificaTipoCotacao(cotacao)) {
      return CandlestickDiarioDTO.builder()
      .dtpreg(cotacao.getDtpreg())
      .codneg(cotacao.getCodneg())
      .preabe(cotacao.getPreabe())
      .preult(cotacao.getPreult())
      .premin(cotacao.getPremin())
      .premax(cotacao.getPremax())
      .voltot(cotacao.getVoltot())
      .semana(Funcoes.stringToIdWeekOfYear(cotacao.getDtpreg()))
      .semanaGerada(false)
      .mediaMovelGerada(false)
      .build();
    }
    return null;
  }

  private boolean verificaTipoCotacao(Cotacao cotacao) {
    boolean retorno = false;
    if(CodbdiEnum.getListCodbdi().contains(cotacao.getCodbdi())
    && TipoMercadoEnum.AVISTA.getCod().equals(cotacao.getTpmerc())){
      retorno = true;
    }
    return retorno;
  }

}
