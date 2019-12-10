package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.HeaderDTO;

public interface IHeaderDAO {

  boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception;

}
