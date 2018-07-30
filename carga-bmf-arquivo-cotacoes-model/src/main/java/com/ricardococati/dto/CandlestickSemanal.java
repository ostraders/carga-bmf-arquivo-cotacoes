package com.ricardococati.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "candlestickSemanal")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CandlestickSemanal extends BMFCargaDTO {

  private static final long serialVersionUID = 505011356059052924L;
  @Id
  private String id;
  private LocalDate dtpregini;
  private LocalDate dtpregfim;
  private Integer semana;
  private String nomres;
  private BigDecimal preabe;
  private BigDecimal premax;
  private BigDecimal premin;
  private BigDecimal preult;
  private BigDecimal voltot;

}
