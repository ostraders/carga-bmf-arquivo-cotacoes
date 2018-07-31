package com.ricardococati.service;

import com.ricardococati.dto.CandlestickDiario;
import com.ricardococati.dto.CandlestickSemanal;
import com.ricardococati.dto.Empresa;
import java.util.List;

import com.ricardococati.dto.BMFCargaDTO;

public interface IBMFCargaService {
	
    public void insereDados(List<? extends BMFCargaDTO> bmfCargaDTOS);

    public List<CandlestickDiario> listaCandlestickDiarioPorEmpresa(String nomres);

    public List<Empresa> listEmpresas();

    public void salvaCandlestickSemanal(CandlestickSemanal candlestickSemanal);

    public void salvaCandlestickDiario(CandlestickDiario candlestickDiario);

}
