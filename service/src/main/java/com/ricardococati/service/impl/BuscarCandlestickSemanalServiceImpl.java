package com.ricardococati.service.impl;

import com.ricardococati.model.dto.CandlestickSemanal;
import com.ricardococati.repository.dao.CandlestickSemanalDAO;
import com.ricardococati.service.BuscarCandlestickSemanalService;
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

  private final CandlestickSemanalDAO semanalDAO;

  @Override
  public List<CandlestickSemanal> buscarCandleSemanalPorPrimeiroDiaSemana() {
    return semanalDAO.buscarCandleSemanalPorPrimeiroDiaSemana(
        obterPrimeiroDiaUtilSemanaCorrente()
    );
  }

  private LocalDate obterPrimeiroDiaUtilSemanaCorrente(){
    LocalDate dataRetorno = LocalDate.now();
    switch (dataRetorno.getDayOfWeek()) {
      case SUNDAY:
        dataRetorno = dataRetorno.minusDays(6);
        break;
      case SATURDAY:
        dataRetorno = dataRetorno.minusDays(5);
        break;
      case FRIDAY:
        dataRetorno = dataRetorno.minusDays(4);
        break;
      case THURSDAY:
        dataRetorno = dataRetorno.minusDays(3);
        break;
      case WEDNESDAY:
        dataRetorno = dataRetorno.minusDays(2);
        break;
      case TUESDAY:
        dataRetorno = dataRetorno.minusDays(1);
        break;
      default:
        break;
    }
    return dataRetorno;
  }

}
