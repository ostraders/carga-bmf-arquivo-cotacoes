package com.ricardococati.dao;

import com.ricardococati.dto.ArquivoDTO;

public interface IArquivoDAO {

	public Long buscaDadosIntercambio(ArquivoDTO arquivoDTO) throws Exception;

}
