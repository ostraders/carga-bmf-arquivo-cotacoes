package com.ricardococati.controller.converter;

import com.ricardococati.model.dto.SplitInplit;
import com.ricardococati.model.enums.OperacaoSplitInplit;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class SplitInplitConverter {

  public SplitInplit convert(
      final LocalDate dtPregao,
      final String codneg,
      final Integer qtdSplitInplit,
      final String operacao) {
    return SplitInplit
        .builder()
        .codneg(codneg)
        .dtpreg(dtPregao)
        .qtdSplitInplit(qtdSplitInplit)
        .operacao(OperacaoSplitInplit.valueOf(operacao.toUpperCase()))
        .build();
  }

}
