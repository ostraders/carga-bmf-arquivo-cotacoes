package com.ricardococati.carga.adapters.controllers.splitinplit.converter;

import com.ricardococati.carga.entities.domains.splitinplit.SplitInplit;
import com.ricardococati.carga.entities.enums.OperacaoSplitInplit;
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
