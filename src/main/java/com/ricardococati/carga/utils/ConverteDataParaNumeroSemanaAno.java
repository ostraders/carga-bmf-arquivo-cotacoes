package com.ricardococati.carga.utils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConverteDataParaNumeroSemanaAno {

  public static Integer converte(final LocalDate data) {
    SimpleDateFormat formatDataContinua = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = Calendar.getInstance();
    try {
      Date dateObj = formatDataContinua.parse(data.toString());
      calendar.setTime(dateObj);
    } catch (Exception e) {
      log.error("Erro ao tentar converter data: {} ", e.getMessage());
    }
    return calendar.get(Calendar.WEEK_OF_YEAR);
  }

  public static String geraDataInicialEFinalDaSemana(final LocalDate data) {
    final Locale defaultLocale = new Locale("pt", "BR");
    DayOfWeek firstDayOfWeek = WeekFields.of(defaultLocale).getFirstDayOfWeek();
    LocalDate primeiroDiaSemana = data.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));

    DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6);
    LocalDate ultimoDiaSemana = data.with(TemporalAdjusters.nextOrSame(lastDayOfWeek));
    return primeiroDiaSemana.toString() + "#" + ultimoDiaSemana.toString();
  }

}
