package com.ricardococati.carga.usecases.candlestick;

import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanal;
import java.util.List;

public interface CalculaCandlestickSemanalService {

	List<CandlestickSemanal> execute() throws Exception;

}
