package com.ricardococati.carga.usecases.integracao;

public interface IntegracaoService {

	void execute() throws Exception;

	Long getIdArquivoSequence(final String nomeSequence) throws Exception;

}
