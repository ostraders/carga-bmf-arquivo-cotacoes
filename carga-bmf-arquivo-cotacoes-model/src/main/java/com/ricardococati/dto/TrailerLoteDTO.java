package com.ricardococati.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TrailerLoteDTO extends BMFCargaDTO {

	private static final long serialVersionUID = 3911421273455014242L;
	private Long codCompensacaoBco;
	private Long loteServico;
	private Long tipoRegistro;
	private String dscCnab2;
	private Long quantidadeRegistroLote;
	private Long somatoriaValore;
	private Long somatoriaQtdMoedas;
	private Long nroAvisoDebito;
	private String dscCnab22;
	private String codOcorrenciaRet;

}
