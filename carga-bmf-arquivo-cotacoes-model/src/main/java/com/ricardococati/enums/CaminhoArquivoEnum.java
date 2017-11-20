package com.ricardococati.enums;

import lombok.Getter;

public enum CaminhoArquivoEnum {

	CAMINHO_ARQUIVO_PROCESSO("file:/data/concipag/cargaBoleto/execucao/*"),//
	CAMINHO_ARQUIVO_ENTRADA("/data/concipag/cargaBoleto/entrada/"), //
	CAMINHO_ARQUIVO_ERRO("/data/concipag/cargaBoleto/erro/"),//
	CAMINHO_ARQUIVO_EXECUCAO("/data/concipag/cargaBoleto/execucao/"),//
	CAMINHO_ARQUIVO_SAIDA("/data/concipag/cargaBoleto/saida/");

	@Getter
	private String caminho;

	private CaminhoArquivoEnum(String caminho) {
		this.caminho = caminho;
	}

}
