package com.ricardococati.carga.usecases;

import com.ricardococati.carga.entities.response.BaixarArquivo;
import java.time.LocalDate;

public interface BaixarArquivoService {

	BaixarArquivo baixarArquivoCotacao(final LocalDate dtpreg) throws Exception;

}
