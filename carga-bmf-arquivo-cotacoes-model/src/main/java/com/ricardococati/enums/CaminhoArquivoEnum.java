package com.ricardococati.enums;

import lombok.Getter;

public enum CaminhoArquivoEnum {

	CAMINHO_ARQUIVO_PROCESSO("file:/data/bmfCarga/execucao/*"),//
	CAMINHO_ARQUIVO_ENTRADA("/data/bmfCarga/entrada/"), //
	CAMINHO_ARQUIVO_ERRO("/data/bmfCarga/erro/"),//
	CAMINHO_ARQUIVO_EXECUCAO("/data/bmfCarga/execucao/"),//
	CAMINHO_ARQUIVO_SAIDA("/data/bmfCarga/saida/");

	@Getter
	private String caminho;

	private CaminhoArquivoEnum(String caminho) {
		this.caminho = caminho;
	}

}
