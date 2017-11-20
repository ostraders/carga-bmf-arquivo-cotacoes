package com.ricardococati.exception;

/**
 * 
 * @author lincoln.coelho
 * 
 */
public class LinhaInvalidaException extends Exception {

	private static final long serialVersionUID = 1569331160493705539L;
	
	private String identificacaoRegistro;
	private String descricao;
	
	public LinhaInvalidaException(String identificacaoRegistro, String descricao) {
		this.identificacaoRegistro = identificacaoRegistro;
		this.descricao = descricao;
	}
	
	public String getIdentificacaoRegistro() {
		return identificacaoRegistro;
	}

	public String getDescricao() {
		return descricao;
	}

}
