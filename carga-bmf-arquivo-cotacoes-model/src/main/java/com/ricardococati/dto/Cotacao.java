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
	private String tipreg;
	private String dtpreg;
	private String codbdi;
	private String codneg;
	private String tpmerc;
	private String nomres;
	private String especi;
	private String prazot;
	private String modref;
	private String preabe;
	private String premax;
	private String premin;
	private String premed;
	private String preult;
	private String preofc;
	private String preofv;
	private String totneg;
	private String quatot;
	private String voltot;
	private String preexe;
	private String indopc;
	private String datven;
	private String fatcot;
	private String ptoexe;
	private String codisi;
	private String dismes;

}
