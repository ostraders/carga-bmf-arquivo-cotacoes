package com.ricardococati.carga.usecases.arquivo;

import java.io.IOException;

public interface DescompactarArquivoService {

	Boolean descompactaArquivoCotacao(final String dataFormatada) throws IOException;

}
