package com.ricardococati.service.converter;

import com.ricardococati.dto.CandlestickDiario;
import com.ricardococati.dto.Cotacao;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ConverteCotacao {

  public CandlestickDiario converterCotacaoParaCandlestick(Cotacao cotacao){
    CandlestickDiario diario = new CandlestickDiario();
    diario.setId(UUID.randomUUID().toString());
    diario.setDtpreg(cotacao.getDtpreg());
    diario.setNomres(cotacao.getNomres());
    diario.setPreabe(cotacao.getPreabe());
    diario.setPreult(cotacao.getPreult());
    diario.setPremin(cotacao.getPremin());
    diario.setPremax(cotacao.getPremax());
    diario.setVoltot(cotacao.getVoltot());
    return diario;
  }

}
