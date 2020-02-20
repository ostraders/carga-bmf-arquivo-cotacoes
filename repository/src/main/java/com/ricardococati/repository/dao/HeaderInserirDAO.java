package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.HeaderDTO;

public interface HeaderInserirDAO {

  Boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception;

}
