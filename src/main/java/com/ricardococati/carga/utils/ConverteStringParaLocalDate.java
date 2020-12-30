package com.ricardococati.carga.utils;

import static java.util.Objects.nonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class ConverteStringParaLocalDate {

  public static LocalDate converte(final String source) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate retorno = null;
    if (nonNull(source) && !source.isEmpty()) {
      retorno = LocalDate.parse(validFormat(source), dtf);
    }
    return retorno;
  }

  private static String validFormat(final String strDate) {
    if (strDate.length() > 8) {
      return strDate.substring(0, 8);
    }
    return strDate;
  }

}
