package com.ricardococati.dao;

import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.BoletoDTO;

public interface IValidacaoDAO {

	public Long verificaExisteBoleto(BoletoDTO boletoDTO, ArquivoDTO arquivoDTO) throws Exception;

}
