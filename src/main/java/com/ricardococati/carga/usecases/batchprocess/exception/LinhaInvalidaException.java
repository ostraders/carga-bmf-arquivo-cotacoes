package com.ricardococati.carga.usecases.batchprocess.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LinhaInvalidaException extends Exception {

	private String identificacaoRegistro;
	private String descricao;
	
}
