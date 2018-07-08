package com.ricardococati.dao;

import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.BMFCargaDTO;

public interface IValidacaoDAO {

	public Long verificaExisteBoleto(BMFCargaDTO BMFCargaDTO, ArquivoDTO arquivoDTO) throws Exception;

}
