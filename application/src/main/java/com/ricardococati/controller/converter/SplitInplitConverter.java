package com.ricardococati.controller.converter;

import com.ricardococati.model.dto.SplitInplit;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class SplitInplitConverter {

  public SplitInplit convert(
      final LocalDate dtPregao,
      final String codneg,
      final Integer qtdSplitInplit){
    return SplitInplit
        .builder()
        .codneg(codneg)
        .dtpreg(dtPregao)
        .qtdSplitInplit(qtdSplitInplit)
        .build();
  }

}
