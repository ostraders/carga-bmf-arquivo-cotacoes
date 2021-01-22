package com.ricardococati.carga.usecases.header;

import com.ricardococati.carga.entities.domains.header.Header;

public interface CargaHeaderService {
	
    Boolean insereDados(Header header) throws Exception;

}
