package com.ricardococati.carga.entities.domains.header;

import com.ricardococati.carga.entities.domains.arquivo.Arquivo;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class Header extends Arquivo {

	private String id;
	private Long tipoRegistro;
	private String nomeDoArquivo;
	private String codigoDaOrigem;
	private LocalDate dataDaGeracaoDoArquivo;
	private String reserva;

}
