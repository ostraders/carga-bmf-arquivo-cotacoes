package com.ricardococati.carga.usecases.arquivo;

import java.io.IOException;

public interface DownloadArquivoService {

	String doanloadArquivo(final String dataFormatada, final String caminho) throws IOException;

}
