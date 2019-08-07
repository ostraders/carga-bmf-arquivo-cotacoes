package com.ricardococati.service;

public interface IIntegrationService {

	void execute() throws Exception;

	Long getIdArquivoSequence(final String nomeSequence);

}
