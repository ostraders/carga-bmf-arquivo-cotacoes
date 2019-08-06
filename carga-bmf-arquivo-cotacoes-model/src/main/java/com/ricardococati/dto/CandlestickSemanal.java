package com.ricardococati.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CandlestickSemanal extends BMFCargaDTO {

  private static final long serialVersionUID = 505011356059052924L;
  private String id;
  private LocalDate dtpregini;
  private LocalDate dtpregfim;
  private Integer semana;
  private String codneg;
  private BigDecimal preabe;
  private BigDecimal premax;
  private BigDecimal premin;
  private BigDecimal preult;
  private BigDecimal voltot;

}
