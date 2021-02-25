package com.ricardococati.carga.entities.domains.candlestick;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandlestickDiario {

  private Long idCandleDiario;
  private LocalDate dtpreg;
  private String codneg;
  private BigDecimal preabe;
  private BigDecimal premax;
  private BigDecimal premin;
  private BigDecimal preult;
  private BigDecimal voltot;
  private Integer idSemanaAno;
  private String idSemana;
  private Boolean semanaGerada;

}
