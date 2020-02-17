package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.HeaderDTO;

public interface HeaderDAO {

  Boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception;

}
