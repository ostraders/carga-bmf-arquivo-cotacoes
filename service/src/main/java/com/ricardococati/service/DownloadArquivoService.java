package com.ricardococati.service;

import java.io.IOException;

public interface DownloadArquivoService {

	Boolean doanloadArquivo(final String dataFormatada, final String caminho) throws IOException;

}
