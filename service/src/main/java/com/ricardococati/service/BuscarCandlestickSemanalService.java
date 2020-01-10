package com.ricardococati.service;

import com.ricardococati.model.dto.CandlestickSemanal;
import java.util.List;

public interface BuscarCandlestickSemanalService {

	List<CandlestickSemanal> buscarCandleSemanalPorPrimeiroDiaSemana();

}
