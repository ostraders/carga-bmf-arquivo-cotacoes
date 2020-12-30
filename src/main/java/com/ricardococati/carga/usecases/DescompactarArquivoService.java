package com.ricardococati.carga.usecases;

import java.io.IOException;

public interface DescompactarArquivoService {

	Boolean descompactaArquivoCotacao(final String dataFormatada) throws IOException;

}
