package com.ricardococati.enums;

import lombok.Getter;

public enum NomeDoSistemaParaLogFornaxEnum {
	
	NOME_SISTEMA("hiker-concipagcargabow");

	private NomeDoSistemaParaLogFornaxEnum(String nome){
		this.nome = nome;
	}

	@Getter
	private String nome;

}
