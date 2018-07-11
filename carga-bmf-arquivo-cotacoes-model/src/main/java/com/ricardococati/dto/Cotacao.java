package com.ricardococati.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@EqualsAndHashCode(callSuper=false)
public class Cotacao extends BMFCargaDTO {
	
	private static final long serialVersionUID = 505011356059052924L;
	private Long tipoRegistro;
	private Date dtpreg;
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
	private Date datven;
	private Long fatcot;
	private BigDecimal ptoexe;
	private String codisi;
	private Long dismes;

}
