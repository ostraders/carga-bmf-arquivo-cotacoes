package com.ricardococati.dao;

import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.HeaderDTO;

public interface IHeaderDAO {

	public boolean incluirHeaderArquivo(HeaderDTO headerDTO, ArquivoDTO arquivoDTO) throws Exception;

}
