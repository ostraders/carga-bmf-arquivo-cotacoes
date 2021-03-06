package com.ricardococati.carga.entities.domains.candlestick;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandlestickSemanalMessage {

  private Long idCandleSemanal;
  private String dtpregini;
  private String dtpregfim;
  private Integer semana;
  private String codneg;
  private BigDecimal preabe;
  private BigDecimal premax;
  private BigDecimal premin;
  private BigDecimal preult;
  private BigDecimal voltot;

}
