package com.ricardococati.carga.entities.domains.cotacao;

import com.ricardococati.carga.entities.domains.arquivo.Arquivo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class Cotacao extends Arquivo {

  private String id;
  private Long tipoRegistro;
  private LocalDate dtpreg;
  private String codbdi;
  private String codneg;
  private Long tpmerc;
  private String nomres;
  private String especi;
  private String prazot;
  private String modref;
  private BigDecimal preabe;
  private BigDecimal premax;
  private BigDecimal premin;
  private BigDecimal premed;
  private BigDecimal preult;
  private BigDecimal preofc;
  private BigDecimal preofv;
  private Long totneg;
  private Long quatot;
  private BigDecimal voltot;
  private BigDecimal preexe;
  private Long indopc;
  private LocalDate datven;
  private Long fatcot;
  private BigDecimal ptoexe;
  private String codisi;
  private Long dismes;

}
