package com.ricardococati.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
