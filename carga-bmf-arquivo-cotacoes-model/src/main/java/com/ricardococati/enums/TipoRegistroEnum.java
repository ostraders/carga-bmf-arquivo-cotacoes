package com.ricardococati.enums;

import lombok.Getter;

public enum TipoRegistroEnum {

	HEADER("0", "HEADER"),
	HEADER_LOTE("1", "HEADER_LOTE"),
	DETALHE_SEGMENTO_G("3", "G"),
	TRAILER_LOTE("5", "TRAILER_LOTE"),
	TRAILER("9", "TRAILER");

	private TipoRegistroEnum(String cod, String nome){
		this.cod = cod;
		this.nome = nome;
	}

	@Getter
	private String cod;
	@Getter
	private String nome;

}
