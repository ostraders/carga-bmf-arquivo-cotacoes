package com.ricardococati.service.batchprocess.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinhaInvalidaException extends Exception {

	private String identificacaoRegistro;
	private String descricao;
	
}
