package com.ricardococati.service.impl;

import com.ricardococati.model.entities.CandlestickSemanal;
import com.ricardococati.repository.dao.CandlestickSemanalBuscarDAO;
import com.ricardococati.service.BuscarCandlestickSemanalService;
import com.ricardococati.service.util.DateServiceUtils;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class BuscarCandlestickSemanalServiceImpl implements BuscarCandlestickSemanalService {

  private final CandlestickSemanalBuscarDAO semanalDAO;

  @Override
  public List<CandlestickSemanal> buscarCandleSemanalPorPrimeiroDiaSemana() {
    return semanalDAO.buscarCandleSemanalPorPrimeiroDiaSemana(
        DateServiceUtils.obterPrimeiroDiaUtilDaSemana(LocalDate.now())
    );
  }

}
