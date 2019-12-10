package com.ricardococati.model.enums;

import lombok.Getter;

public enum TipoRegistroEnum {

	HEADER("00"),
	DETALHE("01"),
	TRAILER("99");

	private TipoRegistroEnum(String cod){
		this.cod = cod;
	}

	@Getter
	private String cod;

}
