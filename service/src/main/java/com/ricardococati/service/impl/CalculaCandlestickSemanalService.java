package com.ricardococati.service.impl;

import static java.util.Objects.isNull;

import com.ricardococati.kafka.topic.TopicEnum;
import com.ricardococati.model.dto.CandlestickDiarioDTO;
import com.ricardococati.model.dto.CandlestickSemanalDTO;
import com.ricardococati.model.dto.CandlestickSemanalMessage;
import com.ricardococati.repository.dao.ICandlestickDiarioDAO;
import com.ricardococati.repository.dao.ICandlestickSemanalDAO;
import com.ricardococati.repository.event.PostgresEventListener;
import com.ricardococati.service.ICalculaCandlestickSemanalService;
import com.ricardococati.service.converter.CandlestickConverter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class CalculaCandlestickSemanalService implements ICalculaCandlestickSemanalService {

  private static final boolean SEMANA_GERADA = false;
  private final ICandlestickSemanalDAO semanalDAO;
  private final ICandlestickDiarioDAO diarioDAO;
  private final PostgresEventListener listener;
  private final CandlestickConverter candlestickConverter;

  @Override
  public void execute() {
    try {
      diarioDAO.buscaCodNegSemanaGeradaFalse()
          .stream()
          .filter(Objects::nonNull)
          .forEach(codneg -> {
            String codnegProcessado = geraCandleStickSemanal(codneg);
            log.info("Código de negócio calculado para semana: " + codnegProcessado);
          });
    } catch (Exception e) {
      log.error("Erro ao calcular Candlestick {} {} " , e.getMessage(), e.getCause());
    }
  }

  @Override
  public Integer contaCandlestickDiarioSemanaGeradaFalse() {
    Integer retorno = 0;
    try {
      retorno = semanalDAO.contaCandleDiarioSemCandleSemanalGerado();
    } catch (Exception e) {
      String mensagemErro = "Ocorreu um erro na contagem dos candles diarios {} ";
      log.error(mensagemErro, e.getMessage());
      throw e;
    }
    return retorno;
  }

  private String geraCandleStickSemanal(final String codneg) {
    log.info("Código de negociação: " + codneg);
    List<CandlestickDiarioDTO> diarioDTOList = diarioDAO.buscaCandleDiarioPorCodNeg(codneg);
    Map<String, List<CandlestickDiarioDTO>> mapDiario = getListCandlestickToStringMap(diarioDTOList);
    mapDiario
        .entrySet()
        .forEach(integerEntry -> {
          CandlestickSemanalDTO candlestickSemanal =
              calculaCandleStickPorSemana(mapDiario.get(integerEntry.getKey()));
          candlestickSemanal.setSemana(integerEntry.getValue().get(0).getIdSemanaAno());
          candlestickSemanal.setCodneg(codneg);
          semanalDAO.salvaCandlestickSemanal(candlestickSemanal);
          CandlestickSemanalMessage message = candlestickConverter
              .convertMessageSemanal(candlestickSemanal);
          listener.onAfterSave(message, TopicEnum.CANDLESTICK_SEMANAL.getTopicName());
        });
    log.info("Finalizando Código de negociação: " + codneg);
    atualizaListaCandlestickDiarioSemanaGerada(diarioDTOList);
    return codneg;
  }

  private void atualizaListaCandlestickDiarioSemanaGerada(
      List<CandlestickDiarioDTO> candlestickList) {
    candlestickList
        .forEach(candlestickDiarioDTO -> {
          candlestickDiarioDTO.setSemanaGerada(true);
          diarioDAO.salvaCandlestickDiario(candlestickDiarioDTO);
        });
  }

  private CandlestickSemanalDTO calculaCandleStickPorSemana(
      List<CandlestickDiarioDTO> candlestickDiarios) {
    CandlestickSemanalDTO candlestickSemanal = new CandlestickSemanalDTO();
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

  private LocalDate calculaDtpregini(CandlestickSemanalDTO candlestickSemanal,
      CandlestickDiarioDTO candlestickDiario) {
    if (isNull(candlestickSemanal.getDtpregini()) ||
        candlestickDiario.getDtpreg().isBefore(candlestickSemanal.getDtpregini())) {
      candlestickSemanal.setDtpregini(candlestickDiario.getDtpreg());
    }
    return candlestickSemanal.getDtpregini();
  }

  private LocalDate calculaDtpregfim(CandlestickSemanalDTO candlestickSemanal,
      CandlestickDiarioDTO candlestickDiario) {
    if (isNull(candlestickSemanal.getDtpregfim()) ||
        candlestickDiario.getDtpreg().isAfter(candlestickSemanal.getDtpregfim())) {
      candlestickSemanal.setDtpregfim(candlestickDiario.getDtpreg());
    }
    return candlestickSemanal.getDtpregfim();
  }

  private BigDecimal calculaPreabe(CandlestickSemanalDTO candlestickSemanal,
      CandlestickDiarioDTO candlestickDiario) {
    if (isNull(candlestickSemanal.getPreabe()) ||
        candlestickSemanal.getDtpregini().isEqual(candlestickDiario.getDtpreg())) {
      candlestickSemanal.setPreabe(candlestickDiario.getPreabe());
    }
    return candlestickSemanal.getPreabe();
  }

  private BigDecimal calculaPremax(CandlestickSemanalDTO candlestickSemanal,
      CandlestickDiarioDTO candlestickDiario) {
    if (isNull(candlestickSemanal.getPremax())
        || candlestickDiario.getPremax().compareTo(candlestickSemanal.getPremax()) > 0) {
      candlestickSemanal.setPremax(candlestickDiario.getPremax());
    }
    return candlestickSemanal.getPremax();
  }

  private BigDecimal calculaPremin(CandlestickSemanalDTO candlestickSemanal,
      CandlestickDiarioDTO candlestickDiario) {
    if (isNull(candlestickSemanal.getPremin())
        || candlestickDiario.getPremin().compareTo(candlestickSemanal.getPremin()) < 0) {
      candlestickSemanal.setPremin(candlestickDiario.getPremin());
    }
    return candlestickSemanal.getPremin();
  }

  private BigDecimal calculaPreult(CandlestickSemanalDTO candlestickSemanal,
      CandlestickDiarioDTO candlestickDiario) {
    if (isNull(candlestickSemanal.getPreult()) ||
        candlestickSemanal.getDtpregfim().isEqual(candlestickDiario.getDtpreg())) {
      candlestickSemanal.setPreult(candlestickDiario.getPreult());
    }
    return candlestickSemanal.getPreult();
  }

  private BigDecimal calculaVoltot(CandlestickSemanalDTO candlestickSemanal,
      CandlestickDiarioDTO candlestickDiario) {
    if (isNull(candlestickSemanal.getVoltot())) {
      candlestickSemanal.setVoltot(candlestickDiario.getVoltot());
    } else {
      candlestickSemanal.getVoltot().add(candlestickDiario.getVoltot());
    }
    return candlestickSemanal.getVoltot();
  }

  private Map<String, List<CandlestickDiarioDTO>> getListCandlestickToStringMap(
      final List<CandlestickDiarioDTO> dtoList
  ) {
    Map<String, List<CandlestickDiarioDTO>> mapCandlestick =
        generateMapListNull(dtoList);
    mapCandlestick
        .entrySet()
        .forEach(entry -> {
          List<CandlestickDiarioDTO> candlestickList = new ArrayList<>();
          dtoList
              .stream()
              .filter(dto -> entry.getKey().equals(dto.getIdSemanaAno() + "#" + dto.getDtpreg().getYear()))
              .forEach(dto -> {
                candlestickList.add(dto);
                mapCandlestick.replace(entry.getKey(), candlestickList);
              });
        });
    return mapCandlestick;
  }

  private Map<String, List<CandlestickDiarioDTO>> generateMapListNull(
      List<CandlestickDiarioDTO> listcandlestickDiarios) {
    Map<String, List<CandlestickDiarioDTO>> mapCandlestick = new HashMap<>();
    for (CandlestickDiarioDTO dto : listcandlestickDiarios) {
      final String strKey = dto.getIdSemanaAno() + "#" + dto.getDtpreg().getYear();
      mapCandlestick.put(strKey, null);
    }
    return mapCandlestick;
  }

}
