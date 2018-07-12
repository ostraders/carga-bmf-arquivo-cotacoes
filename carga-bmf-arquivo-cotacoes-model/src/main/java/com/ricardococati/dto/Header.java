package com.ricardococati.dto;

import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "header")
@EqualsAndHashCode(callSuper=false)
public class Header extends BMFCargaDTO {

	private static final long serialVersionUID = 4338858549320272919L;
	@Id
	private String id;
	private Long tipoRegistro;
	private String nomeDoArquivo;
	private String codigoDaOrigem;
	private LocalDate dataDaGeracaoDoArquivo;
	private String reserva;

}
