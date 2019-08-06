package com.ricardococati.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper=false)
public class Header extends BMFCargaDTO {

	private static final long serialVersionUID = 4338858549320272919L;
	private String id;
	private Long tipoRegistro;
	private String nomeDoArquivo;
	private String codigoDaOrigem;
	private LocalDate dataDaGeracaoDoArquivo;
	private String reserva;

}
