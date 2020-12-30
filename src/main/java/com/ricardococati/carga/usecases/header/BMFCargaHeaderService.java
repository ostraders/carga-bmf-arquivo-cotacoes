package com.ricardococati.carga.usecases.header;

import com.ricardococati.carga.entities.domains.header.Header;

public interface BMFCargaHeaderService {
	
    Boolean insereDados(Header header) throws Exception;

}
