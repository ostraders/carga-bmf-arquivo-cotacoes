package com.ricardococati.carga.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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

}
