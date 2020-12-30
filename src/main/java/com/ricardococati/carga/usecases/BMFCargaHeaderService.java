package com.ricardococati.carga.usecases;

import com.ricardococati.carga.entities.domains.Header;

public interface BMFCargaHeaderService {
	
    Boolean insereDados(Header header) throws Exception;

}
