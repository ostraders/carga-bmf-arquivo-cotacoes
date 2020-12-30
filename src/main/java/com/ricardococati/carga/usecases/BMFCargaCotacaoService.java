package com.ricardococati.carga.usecases;

import com.ricardococati.carga.entities.domains.Cotacao;

public interface BMFCargaCotacaoService {
	
    Boolean insereDados(Cotacao cotacao) throws Exception;

}
