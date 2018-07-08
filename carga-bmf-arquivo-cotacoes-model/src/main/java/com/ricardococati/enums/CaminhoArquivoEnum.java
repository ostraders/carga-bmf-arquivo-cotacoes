package com.ricardococati.enums;

import lombok.Getter;

public enum CaminhoArquivoEnum {

	CAMINHO_ARQUIVO_PROCESSO("file:/data/bfmCarga/execucao/*"),//
	CAMINHO_ARQUIVO_ENTRADA("/data/bfmCarga/entrada/"), //
	CAMINHO_ARQUIVO_ERRO("/data/bfmCarga/erro/"),//
	CAMINHO_ARQUIVO_EXECUCAO("/data/bfmCarga/execucao/"),//
	CAMINHO_ARQUIVO_SAIDA("/data/bfmCarga/saida/");

	@Getter
	private String caminho;

	private CaminhoArquivoEnum(String caminho) {
		this.caminho = caminho;
	}

}
