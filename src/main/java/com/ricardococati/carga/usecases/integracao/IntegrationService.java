package com.ricardococati.carga.usecases.integracao;

public interface IntegrationService {

	void execute() throws Exception;

	Long getIdArquivoSequence(final String nomeSequence) throws Exception;

}
