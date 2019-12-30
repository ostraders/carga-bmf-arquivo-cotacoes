package com.ricardococati.service;

public interface IntegrationService {

	void execute() throws Exception;

	Long getIdArquivoSequence(final String nomeSequence);

}
