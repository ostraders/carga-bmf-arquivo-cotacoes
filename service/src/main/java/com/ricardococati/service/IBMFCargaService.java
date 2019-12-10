package com.ricardococati.service;

import com.ricardococati.model.dto.BMFCargaDTO;

import java.util.List;

public interface IBMFCargaService {
	
    void insereDados(List<? extends BMFCargaDTO> bmfCargaDTOS);

}
