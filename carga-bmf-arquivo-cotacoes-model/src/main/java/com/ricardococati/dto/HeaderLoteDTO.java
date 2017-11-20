package com.ricardococati.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HeaderLoteDTO extends BoletoDTO {

	private static final long serialVersionUID = 6111822115262921890L;
	private Long codBcoCompensacao;
	private Long loteServico;
	private Long tipoRegistro;
	private String tipoOperacao;
	private Long tipoServico;
	private String exclusivoCnab;
	private Long nroVersaoLayoutLote;
	private String exclusivoCnab2;
	private Long tipoInscricaoEmpresa;
	private Long nroInscricaoEmpresa;
	private String codConvenioBco;
	private Long agenciaMantenedoraConta;
	private String dvAgencia;
	private Long nroContaCorrente;
	private String dvConta;
	private String dvAgConta;
	private String nomeEmpresa;
	private String exclusivoCnab3;

}
