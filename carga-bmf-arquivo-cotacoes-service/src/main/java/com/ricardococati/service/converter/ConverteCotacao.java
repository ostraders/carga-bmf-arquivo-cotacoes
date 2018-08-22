package com.ricardococati.service.converter;

import com.ricardococati.dto.CandlestickDiario;
import com.ricardococati.dto.Cotacao;
import com.ricardococati.enums.CodbdiEnum;
import com.ricardococati.enums.TipoMercadoEnum;
import com.ricardococati.util.Funcoes;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConverteCotacao {

  public CandlestickDiario converterCotacaoParaCandlestick(Cotacao cotacao){
    CandlestickDiario diario = null;
    if(verificaTipoCotacao(cotacao)) {
      diario = new CandlestickDiario();
      diario.setId(UUID.randomUUID().toString());
      diario.setDtpreg(cotacao.getDtpreg());
      diario.setCodneg(cotacao.getCodneg());
      diario.setPreabe(cotacao.getPreabe());
      diario.setPreult(cotacao.getPreult());
      diario.setPremin(cotacao.getPremin());
      diario.setPremax(cotacao.getPremax());
      diario.setVoltot(cotacao.getVoltot());
      diario.setSemana(Funcoes.stringToIdWeekOfYear(cotacao.getDtpreg()));
      diario.setSemanaGerada(false);
    }
    return diario;
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
