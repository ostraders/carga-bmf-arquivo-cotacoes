package com.ricardococati.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HeaderDTO extends BoletoDTO {

	private static final long serialVersionUID = 4338858549320272919L;
	private Long nroArquivo;
	private Long nroBco;
	private Long loteServico;
	private Long tipoRegistro;
	private String exclusivoCnab;
	private Long tipoInscricaoEmpresa;
	private Long nroInscricaoEmpresa;
	private String nroConvenio;
	private Long nroAgencia;
	private String dvAgencia;
	private Long nroContaCorrente;
	private String dvConta;
	private String dvAgConta;
	private String nomeEmpresa;
	private String nomeBco;
	private String exclusivoCnab2;
	private Long nroRemessaRet;
	private String dataGeracaoArquivo;
	private Long horaGeracaoArquivo;
	private Long nroSequencialArquivo;
	private Long nroVersaoLayoutArquivo;
	private Long densidadeGravacaoArquivo;
	private String reservadorBco;
	private String reservadoEmpresa;
	private String exclusivoCnab3;

}
