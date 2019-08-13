package com.ricardococati.service.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.ricardococati.dto.CandlestickDiario;
import com.ricardococati.dto.CandlestickSemanal;
import com.ricardococati.service.IBMFCargaService;
import com.ricardococati.service.ICandlestickSemanalService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
public class CandlestickSemanalSemanalService implements ICandlestickSemanalService, Serializable {

  private static final long serialVersionUID = 37671121174988135L;
  public static final boolean SEMANA_GERADA = false;

  @Autowired
  private IBMFCargaService cargaService;

  @Override
  public void execute() {
    try {
      geraCandleStickSemanal();
    } catch (Exception e) {
      log.error("Erro ao calcular Candlestick", e.getMessage());
    }
  }

  @Override
  public int contaCandlestickDiarioSemanaGeradaFalse(boolean semanaGerada) {
    return 0;
  }

  private void geraCandleStickSemanal() {
    /*cargaService
        .listCodNegocio()
        .forEach(empresa -> {
          log.info("Código de negociação: " + empresa.getCodneg());
          List<CandlestickDiario> candlestickList =
              cargaService
                  .listaCandlestickDiarioPorEmpresaSemanaGerada(empresa.getCodneg(), SEMANA_GERADA);
          Map<Integer, List<CandlestickDiario>> mapDiario =
              getListCandlestickToMap(candlestickList);
          mapDiario
              .entrySet()
              .forEach(integerEntry -> {
                CandlestickSemanal candlestickSemanal = calculaCandleStickPorSemana(
                    mapDiario.get(integerEntry.getKey()));
                candlestickSemanal.setSemana(integerEntry.getKey());
                candlestickSemanal.setCodneg(empresa.getCodneg());
                cargaService.salvaCandlestickSemanal(candlestickSemanal);
              });
          atualizaListaCandlestickDiarioSemanaGerada(candlestickList);
        });*/
  }

  private void atualizaListaCandlestickDiarioSemanaGerada(List<CandlestickDiario> candlestickList) {
    /*candlestickList
        .forEach(candlestickDiario -> {
          candlestickDiario.setSemanaGerada(true);
          cargaService.salvaCandlestickDiario(candlestickDiario);
        });*/
  }

  private CandlestickSemanal calculaCandleStickPorSemana(
      List<CandlestickDiario> candlestickDiarios) {
    CandlestickSemanal candlestickSemanal = new CandlestickSemanal();
    candlestickDiarios
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

  private Map<Integer, List<CandlestickDiario>> getListCandlestickToMap(
      List<CandlestickDiario> listcandlestickDiarios) {
    return listcandlestickDiarios
        .stream()
        .filter(candlestickDiario -> nonNull(candlestickDiario.getSemana()))
        .collect(Collectors.groupingBy(candlestickDiario -> candlestickDiario.getSemana()));
  }

}
