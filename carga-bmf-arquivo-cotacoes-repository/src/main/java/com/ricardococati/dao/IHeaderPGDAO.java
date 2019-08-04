package com.ricardococati.dao;

import com.ricardococati.dto.HeaderDTO;

public interface IHeaderPGDAO {

  boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception;

}
