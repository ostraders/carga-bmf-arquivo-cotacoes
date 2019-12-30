package com.ricardococati.service.converter;

import com.ricardococati.model.dto.CandlestickDiario;
import com.ricardococati.model.dto.CandlestickDiarioMessage;
import com.ricardococati.model.dto.CandlestickSemanal;
import com.ricardococati.model.dto.CandlestickSemanalMessage;
import com.ricardococati.model.dto.Cotacao;
import com.ricardococati.model.enums.CodbdiEnum;
import com.ricardococati.model.enums.TipoMercadoEnum;
import com.ricardococati.repository.util.Funcoes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CandlestickConverter {

  public CandlestickDiario convert(Cotacao cotacao) {
    if (verificaTipoCotacao(cotacao)) {
      return CandlestickDiario.builder()
          .dtpreg(cotacao.getDtpreg())
          .codneg(cotacao.getCodneg())
          .preabe(cotacao.getPreabe())
          .preult(cotacao.getPreult())
          .premin(cotacao.getPremin())
          .premax(cotacao.getPremax())
          .voltot(cotacao.getVoltot())
          .idSemanaAno(Funcoes.stringToIdWeekOfYear(cotacao.getDtpreg()))
          .semanaGerada(false)
          .build();
    }
    return null;
  }

  private boolean verificaTipoCotacao(Cotacao cotacao) {
    boolean retorno = false;
    if (CodbdiEnum.getListCodbdi().contains(cotacao.getCodbdi())
        && TipoMercadoEnum.AVISTA.getCod().equals(cotacao.getTpmerc())) {
      retorno = true;
    }
    return retorno;
  }

  public CandlestickDiarioMessage convertMessage(CandlestickDiario diarioDTO) {
    return CandlestickDiarioMessage.builder()
        .dtpreg(diarioDTO.getDtpreg().toString())
        .codneg(diarioDTO.getCodneg())
        .preabe(diarioDTO.getPreabe())
        .preult(diarioDTO.getPreult())
        .premin(diarioDTO.getPremin())
        .premax(diarioDTO.getPremax())
        .voltot(diarioDTO.getVoltot())
        .semana(Funcoes.stringToIdWeekOfYear(diarioDTO.getDtpreg()))
        .semanaGerada(false)
        .build();
  }

  public CandlestickSemanalMessage convertMessageSemanal(CandlestickSemanal diarioDTO) {
    return CandlestickSemanalMessage.builder()
        .dtpregini(diarioDTO.getDtpregini().toString())
        .dtpregfim(diarioDTO.getDtpregfim().toString())
        .codneg(diarioDTO.getCodneg())
        .preabe(diarioDTO.getPreabe())
        .preult(diarioDTO.getPreult())
        .premin(diarioDTO.getPremin())
        .premax(diarioDTO.getPremax())
        .voltot(diarioDTO.getVoltot())
        .semana(diarioDTO.getSemana())
        .build();
  }

}
