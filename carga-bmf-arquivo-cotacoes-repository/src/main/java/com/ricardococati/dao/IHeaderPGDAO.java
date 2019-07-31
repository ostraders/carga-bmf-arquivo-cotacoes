package com.ricardococati.dao;

import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.Header;
import com.ricardococati.dto.HeaderDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IHeaderPGDAO {

  boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception;

}
