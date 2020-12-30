package com.ricardococati.carga.usecases.cotacao;

import com.ricardococati.carga.entities.domains.cotacao.Cotacao;

public interface BMFCargaCotacaoService {
	
    Boolean insereDados(Cotacao cotacao) throws Exception;

}
