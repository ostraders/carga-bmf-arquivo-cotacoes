package com.ricardococati.carga.usecases.candlestick.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanal;
import com.ricardococati.carga.usecases.candlestick.BuildCandlestickSemanalService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class BuildCandlestickSemanalServiceImpl implements
    BuildCandlestickSemanalService {

  @Override
  public CandlestickSemanal build(
      final List<CandlestickDiario> candlestickDiarios
  ) {
    CandlestickSemanal candlestickSemanal = new CandlestickSemanal();
    candlestickDiarios
        .stream()
        .filter(Objects::nonNull)
        .forEach(candlestickDiario -> {
          candlestickSemanal.setDtpregini(calculaDtpregini(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setDtpregfim(calculaDtpregfim(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setPreabe(calculaPreabe(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setPremin(calculaPremin(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setPremax(calculaPremax(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setPreult(calculaPreult(candlestickSemanal, candlestickDiario));
          candlestickSemanal.setVoltot(calculaVoltot(candlestickSemanal, candlestickDiario));
        });
    return candlestickSemanal;
  }

  private LocalDate calculaDtpregini(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getDtpregini()) ||
        candlestickDiario.getDtpreg().isBefore(candlestickSemanal.getDtpregini())) {
      candlestickSemanal.setDtpregini(candlestickDiario.getDtpreg());
    }
    return candlestickSemanal.getDtpregini();
  }

  private LocalDate calculaDtpregfim(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getDtpregfim()) ||
        candlestickDiario.getDtpreg().isAfter(candlestickSemanal.getDtpregfim())) {
      candlestickSemanal.setDtpregfim(candlestickDiario.getDtpreg());
    }
    return candlestickSemanal.getDtpregfim();
  }

  private BigDecimal calculaPreabe(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getPreabe()) ||
        candlestickSemanal.getDtpregini().isEqual(candlestickDiario.getDtpreg())) {
      candlestickSemanal.setPreabe(candlestickDiario.getPreabe());
    }
    return candlestickSemanal.getPreabe();
  }

  private BigDecimal calculaPremax(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getPremax())
        || candlestickDiario.getPremax().compareTo(candlestickSemanal.getPremax()) > 0) {
      candlestickSemanal.setPremax(candlestickDiario.getPremax());
    }
    return candlestickSemanal.getPremax();
  }

  private BigDecimal calculaPremin(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getPremin())
        || candlestickDiario.getPremin().compareTo(candlestickSemanal.getPremin()) < 0) {
      candlestickSemanal.setPremin(candlestickDiario.getPremin());
    }
    return candlestickSemanal.getPremin();
  }

  private BigDecimal calculaPreult(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getPreult()) ||
        candlestickSemanal.getDtpregfim().isEqual(candlestickDiario.getDtpreg())) {
      candlestickSemanal.setPreult(candlestickDiario.getPreult());
    }
    return candlestickSemanal.getPreult();
  }

  private BigDecimal calculaVoltot(CandlestickSemanal candlestickSemanal,
      CandlestickDiario candlestickDiario) {
    if (isNull(candlestickSemanal.getVoltot())) {
      candlestickSemanal.setVoltot(candlestickDiario.getVoltot());
    } else {
      candlestickSemanal.getVoltot().add(candlestickDiario.getVoltot());
    }
    return candlestickSemanal.getVoltot();
  }

}

