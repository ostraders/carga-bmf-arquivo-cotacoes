package com.ricardococati.dto;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@Data
@EqualsAndHashCode(callSuper=false)
public class Trailer extends BMFCargaDTO {

	private static final long serialVersionUID = 3911421273455014242L;
	@Id
	private String id;
	private Long tipoRegistro;
	private String nomeDoArquivo;
	private String codigoDaOrigem;
	private Date dataDaGeracaoDoArquivo;
	private Long totalDeRegistros;
	private String reserva;

}
