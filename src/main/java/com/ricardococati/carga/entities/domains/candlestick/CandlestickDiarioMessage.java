package com.ricardococati.carga.entities.domains.candlestick;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandlestickDiarioMessage {

  private Long idCandleDiario;
  private String dtpreg;
  private String codneg;
  private BigDecimal preabe;
  private BigDecimal premax;
  private BigDecimal premin;
  private BigDecimal preult;
  private BigDecimal voltot;
  private Integer semana;
  private Boolean semanaGerada;

}
