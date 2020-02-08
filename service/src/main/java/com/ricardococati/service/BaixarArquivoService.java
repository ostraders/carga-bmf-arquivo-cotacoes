package com.ricardococati.service;

import com.ricardococati.model.response.BaixarArquivo;
import java.time.LocalDate;

public interface BaixarArquivoService {

	BaixarArquivo baixarArquivoCotacao(final LocalDate dtpreg) throws Exception;

}
