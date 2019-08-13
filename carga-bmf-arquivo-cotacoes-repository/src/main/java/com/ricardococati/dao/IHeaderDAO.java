package com.ricardococati.dao;

import com.ricardococati.dto.HeaderDTO;

public interface IHeaderDAO {

  boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception;

}
