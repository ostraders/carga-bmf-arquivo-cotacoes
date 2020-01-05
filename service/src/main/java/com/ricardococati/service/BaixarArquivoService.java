package com.ricardococati.service;

import java.time.LocalDate;

public interface BaixarArquivoService {

	Boolean baixarArquivoCotacao(final LocalDate dtpreg) throws Exception;

}
