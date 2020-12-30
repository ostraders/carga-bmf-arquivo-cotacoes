package com.ricardococati.carga.adapters.repositories.header;

import com.ricardococati.carga.entities.domains.header.dto.HeaderDTO;

public interface HeaderInserirDAO {

  Boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception;

}
