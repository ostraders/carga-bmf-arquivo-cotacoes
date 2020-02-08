package com.ricardococati.service;

import com.ricardococati.model.entities.CandlestickSemanal;
import java.util.List;

public interface BuscarCandlestickSemanalService {

	List<CandlestickSemanal> buscarCandleSemanalPorPrimeiroDiaSemana();

}
