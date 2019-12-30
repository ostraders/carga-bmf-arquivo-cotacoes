package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.HeaderDTO;

public interface HeaderDAO {

  boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception;

}
