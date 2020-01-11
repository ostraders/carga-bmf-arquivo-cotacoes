package com.ricardococati.service.impl;

import static java.util.Objects.isNull;

import com.ricardococati.kafka.topic.TopicEnum;
import com.ricardococati.model.dto.CandlestickDiario;
import com.ricardococati.model.dto.CandlestickSemanal;
import com.ricardococati.model.dto.CandlestickSemanalMessage;
import com.ricardococati.repository.dao.CandlestickDiarioDAO;
import com.ricardococati.repository.dao.CandlestickSemanalDAO;
import com.ricardococati.repository.dao.InserirCandlestickSemanalDAO;
import com.ricardococati.repository.event.PostgresEventListener;
import com.ricardococati.service.BuildCandlestickSemanalService;
import com.ricardococati.service.CalculaCandlestickSemanalService;
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
public class CalculaCandlestickSemanalServiceImpl implements CalculaCandlestickSemanalService {

  private static final boolean SEMANA_GERADA = false;
  private final CandlestickSemanalDAO semanalDAO;
  private final InserirCandlestickSemanalDAO inserirSemanalDAO;
  private final CandlestickDiarioDAO diarioDAO;
  private final PostgresEventListener listener;
  private final CandlestickConverter candlestickConverter;
  private final BuildCandlestickSemanalService buildSemanal;

  @Override
  public void execute() {
    try {
      diarioDAO.buscaCodNeg()
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

  private String geraCandleStickSemanal(final String codneg) {
    log.info("Código de negociação: " + codneg);
    List<CandlestickDiario> diarioDTOList = diarioDAO.buscaCandleDiarioPorCodNegSemanaGerada(codneg);
    Map<String, List<CandlestickDiario>> mapDiario = getListCandlestickToStringMap(diarioDTOList);
    mapDiario
        .entrySet()
        .forEach(integerEntry -> {
          CandlestickSemanal candlestickSemanal =
              buildSemanal.build(mapDiario.get(integerEntry.getKey()));
          candlestickSemanal.setSemana(integerEntry.getValue().get(0).getIdSemanaAno());
          candlestickSemanal.setCodneg(codneg);
          inserirSemanalDAO.incluirCandlestickSemanal(candlestickSemanal);
          CandlestickSemanalMessage message = candlestickConverter
              .convertMessageSemanal(candlestickSemanal);
          listener.onAfterSave(message, TopicEnum.CANDLESTICK_SEMANAL.getTopicName());
        });
    log.info("Finalizando Código de negociação: " + codneg);
    return codneg;
  }

  private Map<String, List<CandlestickDiario>> getListCandlestickToStringMap(
      final List<CandlestickDiario> dtoList
  ) {
    Map<String, List<CandlestickDiario>> mapCandlestick =
        generateMapListNull(dtoList);
    mapCandlestick
        .entrySet()
        .forEach(entry -> {
          List<CandlestickDiario> candlestickList = new ArrayList<>();
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

  private Map<String, List<CandlestickDiario>> generateMapListNull(
      List<CandlestickDiario> listcandlestickDiarios) {
    Map<String, List<CandlestickDiario>> mapCandlestick = new HashMap<>();
    for (CandlestickDiario dto : listcandlestickDiarios) {
      final String strKey = dto.getIdSemanaAno() + "#" + dto.getDtpreg().getYear();
      mapCandlestick.put(strKey, null);
    }
    return mapCandlestick;
  }

}
