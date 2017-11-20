package com.ricardococati.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DetalheSegmentoGDTO extends BoletoDTO {
	
	private static final long serialVersionUID = 505011356059052924L;
	private Long codBoleto;
	private Long codCompensacaoBco;
	private Long loteServico;
	private Long tipoRegistro;
	private Long nroSequencial;
	private String codSegmento;
	private String formaPgto;
	private Long codOcorrenciaBase;
	private String codBarra;
	private Long tpoInscCedente;
	private Long inscCedente;
	private String nomeCedente;
	private Date dtaVencimento;
	private BigDecimal vlrTitulo;
	private Long qtdMoeda;
	private Long codMoeda;
	private String nroDocCobranca;
	private Long codAgCobranca;
	private String dvAgencia;
	private String pracaCobranca;
	private String codCarteira;
	private String especieTitulo;
	private Date dtaEmissaoTitulo;
	private BigDecimal jurosMoraPorDia;
	private Long codDesconto;
	private Date dtaDesconto;
	private BigDecimal vlrDesconto;
	private Long codProtesto;
	private Long prazoProtesto;
	private Date dtaLimitePagtoTitulo;
	private Long ocorProcessamento;

}
