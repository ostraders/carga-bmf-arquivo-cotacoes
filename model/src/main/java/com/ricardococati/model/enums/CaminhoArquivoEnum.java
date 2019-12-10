package com.ricardococati.model.enums;

import lombok.Getter;

public enum CaminhoArquivoEnum {

	CAMINHO_ARQUIVO_PROCESSO("file:/data/bmfCarga/execucao/*"),//
	CAMINHO_ARQUIVO_ENTRADA("/data/bmfCarga/entrada/"), //
	CAMINHO_ARQUIVO_ERRO("/data/bmfCarga/erro/"),//
	CAMINHO_ARQUIVO_EXECUCAO("/data/bmfCarga/execucao/"),//
	CAMINHO_ARQUIVO_SUCESSO("/data/bmfCarga/sucesso/");

	@Getter
	private String caminho;

	private CaminhoArquivoEnum(String caminho) {
		this.caminho = caminho;
	}

}
