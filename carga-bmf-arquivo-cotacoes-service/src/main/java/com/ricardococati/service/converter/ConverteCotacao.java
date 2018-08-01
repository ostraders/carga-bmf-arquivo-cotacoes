package com.ricardococati.service.converter;

import com.ricardococati.dto.CandlestickDiario;
import com.ricardococati.dto.Cotacao;
import com.ricardococati.util.Funcoes;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
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
    diario.setSemana(Funcoes.stringToIdWeekOfYear(cotacao.getDtpreg()));
    diario.setSemanaGerada(false);
    return diario;
  }

}
