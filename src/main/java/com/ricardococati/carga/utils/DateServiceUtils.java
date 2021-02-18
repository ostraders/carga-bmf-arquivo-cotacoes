package com.ricardococati.carga.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateServiceUtils {

  public static LocalDate obterPrimeiroDiaUtilDaSemana(final LocalDate dataOrigem) {
    LocalDate dataRetorno = dataOrigem;
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

  public static Boolean hojeEh(
      final DayOfWeek dataAtualSource,
      final DayOfWeek dataAtualTest
  ) {
    return dataAtualSource.equals(dataAtualTest);
  }

}
