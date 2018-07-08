package com.ricardococati.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TrailerDTO extends BMFCargaDTO {

	private static final long serialVersionUID = 3911421273455014242L;
	private Long codCompensacaoBco;
	private Long loteServico;
	private Long tipoRegistro;
	private String dscCnab1;
	private Long quantidadeLotesArquivo;
	private Long quantidadeRegistrosArquivo;
	private Long quantidadeContasLote;
	private String dscCnab2;

}
