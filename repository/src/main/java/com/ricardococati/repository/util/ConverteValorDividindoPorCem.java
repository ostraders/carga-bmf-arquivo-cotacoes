package com.ricardococati.repository.util;

import static java.util.Objects.nonNull;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ConverteValorDividindoPorCem {

  public static BigDecimal divisao(final BigDecimal bigDecimal) {
    BigDecimal retorno = new BigDecimal(0);
    if (nonNull(bigDecimal)) {
      retorno = bigDecimal.divide(new BigDecimal(100D));
    }
    return retorno;
  }

}
