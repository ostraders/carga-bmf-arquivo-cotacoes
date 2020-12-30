package com.ricardococati.carga.adapters.repositories;

import com.ricardococati.carga.entities.dto.HeaderDTO;

public interface HeaderInserirDAO {

  Boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception;

}
