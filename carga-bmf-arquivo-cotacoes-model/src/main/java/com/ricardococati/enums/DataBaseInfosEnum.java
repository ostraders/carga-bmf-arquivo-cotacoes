package com.ricardococati.enums;

import lombok.Getter;

public enum DataBaseInfosEnum {
	
	SCHEMA("SCH_CONCIPAG");

	@Getter
	private String texto;

	private DataBaseInfosEnum(String texto) {
		this.texto = texto;
	}

}
