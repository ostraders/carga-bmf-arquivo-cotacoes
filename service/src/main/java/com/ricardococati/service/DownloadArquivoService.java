package com.ricardococati.service;

import java.io.IOException;

public interface DownloadArquivoService {

	String doanloadArquivo(final String dataFormatada, final String caminho) throws IOException;

}
