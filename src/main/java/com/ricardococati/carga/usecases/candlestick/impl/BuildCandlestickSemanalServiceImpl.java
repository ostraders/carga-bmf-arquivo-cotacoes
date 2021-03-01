package com.ricardococati.carga.usecases.candlestick.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanal;
import com.ricardococati.carga.usecases.candlestick.BuildCandlestickSemanalService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class BuildCandlestickSemanalServiceImpl implements BuildCandlestickSemanalService {

  @Override
  public CandlestickSemanal build(final List<CandlestickDiario> candlestickDiarios) {
    return CandlestickSemanal
        .builder()
        .dtpregini(calculaDtpregini(candlestickDiarios))
        .dtpregfim(calculaDtpregfim(candlestickDiarios))
        .preabe(calculaPreabe(candlestickDiarios))
        .premin(calculaPremin(candlestickDiarios))
        .premax(calculaPremax(candlestickDiarios))
        .preult(calculaPreult(candlestickDiarios))
        .voltot(calculaVoltot(candlestickDiarios))
        .build();
  }

  private BigDecimal calculaPremin(final List<CandlestickDiario> semanalList){
    return semanalList
        .stream()
        .filter(Objects::nonNull)
        .filter(candlestickDiario -> nonNull(candlestickDiario.getPremin()))
        .map(CandlestickDiario::getPremin)
        .min(Comparator.naturalOrder())
        .orElse(BigDecimal.ZERO);
  }

  private BigDecimal calculaPremax(final List<CandlestickDiario> semanalList){
    return semanalList
        .stream()
        .filter(Objects::nonNull)
        .filter(candlestickDiario -> nonNull(candlestickDiario.getPremax()))
        .map(CandlestickDiario::getPremax)
        .max(Comparator.naturalOrder())
        .orElse(BigDecimal.ZERO);
  }

  private LocalDate calculaDtpregini(final List<CandlestickDiario> semanalList){
    return semanalList
        .stream()
        .filter(Objects::nonNull)
        .filter(candlestickDiario -> nonNull(candlestickDiario.getDtpreg()))
        .map(CandlestickDiario::getDtpreg)
        .min(Comparator.naturalOrder())
        .orElse(LocalDate.now());
  }

  private LocalDate calculaDtpregfim(final List<CandlestickDiario> semanalList){
    return semanalList
        .stream()
        .filter(Objects::nonNull)
        .filter(candlestickDiario -> nonNull(candlestickDiario.getDtpreg()))
        .map(CandlestickDiario::getDtpreg)
        .max(Comparator.naturalOrder())
        .orElse(LocalDate.now());
  }

  private BigDecimal calculaPreabe(final List<CandlestickDiario> semanalList){
    return semanalList
        .stream()
        .filter(candlestickDiario -> nonNull(candlestickDiario.getPreabe()))
        .filter(candlestickDiario -> calculaDtpregini(semanalList).equals(candlestickDiario.getDtpreg()))
        .map(CandlestickDiario::getPreabe)
        .findFirst().get();
  }

  private BigDecimal calculaPreult(final List<CandlestickDiario> semanalList){
    return semanalList
        .stream()
        .filter(candlestickDiario -> nonNull(candlestickDiario.getPreult()))
        .filter(candlestickDiario -> calculaDtpregfim(semanalList).equals(candlestickDiario.getDtpreg()))
        .map(CandlestickDiario::getPreult)
        .findFirst().get();
  }

  private BigDecimal calculaVoltot(final List<CandlestickDiario> semanalList){
    return semanalList
        .stream()
        .filter(Objects::nonNull)
        .filter(candlestickDiario -> nonNull(candlestickDiario.getVoltot()))
        .map(CandlestickDiario::getVoltot)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
