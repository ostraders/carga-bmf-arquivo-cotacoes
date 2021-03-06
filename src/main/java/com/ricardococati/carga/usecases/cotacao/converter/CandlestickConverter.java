package com.ricardococati.carga.usecases.cotacao.converter;

import com.ricardococati.carga.utils.ConverteDataParaNumeroSemanaAno;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiarioMessage;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanal;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanalMessage;
import com.ricardococati.carga.entities.domains.cotacao.Cotacao;
import com.ricardococati.carga.entities.enums.CodbdiEnum;
import com.ricardococati.carga.entities.enums.TipoMercadoEnum;
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
          .idSemanaAno(ConverteDataParaNumeroSemanaAno.converte(cotacao.getDtpreg()))
          .idSemana(ConverteDataParaNumeroSemanaAno.geraDataInicialEFinalDaSemana(cotacao.getDtpreg()))
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
        .semana(ConverteDataParaNumeroSemanaAno.converte(diarioDTO.getDtpreg()))
        .idSemana(ConverteDataParaNumeroSemanaAno.geraDataInicialEFinalDaSemana(diarioDTO.getDtpreg()))
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
