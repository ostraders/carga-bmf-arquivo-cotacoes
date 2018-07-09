package com.ricardococati.enums;

import lombok.Getter;

public enum TipoRegistroEnum {

	HEADER("00", "HEADER"),
	DETALHE("01", "G"),
	TRAILER("99", "TRAILER");

	private TipoRegistroEnum(String cod, String nome){
		this.cod = cod;
		this.nome = nome;
	}

	@Getter
	private String cod;
	@Getter
	private String nome;

}
