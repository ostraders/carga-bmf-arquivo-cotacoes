package com.ricardococati.service;

import java.util.List;

import com.ricardococati.dto.BoletoDTO;

public interface IBoletoService {
	
    public void insereDados(List<? extends BoletoDTO> listBoletoDTO);

}
