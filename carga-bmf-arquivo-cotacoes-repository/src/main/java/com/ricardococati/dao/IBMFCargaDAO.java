package com.ricardococati.dao;

import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.DetalheSegmentoGDTO;

public interface IBMFCargaDAO {

	public Long incluirBoleto(DetalheSegmentoGDTO detalheSegmentoGDTO, ArquivoDTO arquivoDTO) throws Exception;

}