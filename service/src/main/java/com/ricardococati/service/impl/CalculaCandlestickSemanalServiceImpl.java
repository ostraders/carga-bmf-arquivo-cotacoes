package com.ricardococati.service.impl;

import com.ricardococati.kafka.topic.TopicEnum;
import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.model.entities.CandlestickSemanal;
import com.ricardococati.model.entities.CandlestickSemanalMessage;
import com.ricardococati.repository.dao.CandlestickDiarioBuscarDAO;
import com.ricardococati.repository.dao.CandlestickSemanalInserirDAO;
import com.ricardococati.repository.event.PostgresEventListener;
import com.ricardococati.service.BuildCandlestickSemanalService;
import com.ricardococati.service.CalculaCandlestickSemanalService;
import com.ricardococati.service.converter.CandlestickConverter;
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
  private final CandlestickSemanalInserirDAO inserirSemanalDAO;
  private final CandlestickDiarioBuscarDAO diarioDAO;
  private final PostgresEventListener listener;
  private final CandlestickConverter candlestickConverter;
  private final BuildCandlestickSemanalService buildSemanal;

  @Override
  public void execute() {
    try {
      diarioDAO.buscaCodNeg()
          .parallelStream()
          .filter(Objects::nonNull)
          .forEach(codneg -> {
            try {
              geraCandleStickSemanal(codneg);
            } catch (Exception e) {
              log.error("Erro ao calcular Candlestick {} {} ",
                  e.getMessage(),
                  e.getCause()
              );
            }
          });
    } catch (Exception e) {
      log.error("Erro ao calcular Candlestick {} {} ",
          e.getMessage(),
          e.getCause()
      );
    }
  }

  private String geraCandleStickSemanal(final String codneg) throws Exception {
    log.info("Código de negociação: " + codneg);
    List<CandlestickDiario> diarioDTOList = diarioDAO
        .buscaCandleDiarioPorCodNegSemanaGerada(codneg);
    Map<String, List<CandlestickDiario>> mapDiario = getListCandlestickToStringMap(diarioDTOList);
    try {
      mapDiario
          .entrySet()
          .forEach(integerEntry -> {
            CandlestickSemanal candlestickSemanal =
                buildSemanal.build(mapDiario.get(integerEntry.getKey()));
            candlestickSemanal.setSemana(integerEntry.getValue().get(0).getIdSemanaAno());
            candlestickSemanal.setCodneg(codneg);
            try {
              inserirSemanalDAO.incluirCandlestickSemanal(candlestickSemanal);
            } catch (Exception ex) {
              log.error("Erro ao tentar gerar candle semanal {} ", ex.getMessage());
            }
            CandlestickSemanalMessage message = candlestickConverter
                .convertMessageSemanal(candlestickSemanal);
            listener.onAfterSave(message, TopicEnum.CANDLESTICK_SEMANAL.getTopicName());
          });
      log.info("Finalizando Código de negociação: " + codneg);
    } catch (Exception ex) {
      log.error("Erro ao tentar gerar candle semanal {} ", ex.getMessage());
    }
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
              .filter(dto -> entry.getKey()
                  .equals(dto.getIdSemanaAno() + "#" + dto.getDtpreg().getYear()))
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
