package com.ricardococati.carga.usecases.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.carga.adapters.message.event.CandlestickEventListener;
import com.ricardococati.carga.adapters.message.topic.TopicosDiarioSemanal;
import com.ricardococati.carga.adapters.repositories.CandlestickDiarioBuscarDAO;
import com.ricardococati.carga.adapters.repositories.CandlestickSemanalInserirDAO;
import com.ricardococati.carga.entities.domains.CandlestickDiario;
import com.ricardococati.carga.entities.domains.CandlestickSemanal;
import com.ricardococati.carga.entities.domains.CandlestickSemanalMessage;
import com.ricardococati.carga.usecases.BuildCandlestickSemanalService;
import com.ricardococati.carga.usecases.CalculaCandlestickSemanalService;
import com.ricardococati.carga.utils.converter.CandlestickConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculaCandlestickSemanalServiceImpl implements CalculaCandlestickSemanalService {

  private final CandlestickSemanalInserirDAO inserirSemanalDAO;
  private final CandlestickDiarioBuscarDAO diarioDAO;
  private final CandlestickEventListener listener;
  private final CandlestickConverter candlestickConverter;
  private final BuildCandlestickSemanalService buildSemanal;

  @Override
  public Boolean execute() throws Exception {
    try {
      final List<String> listCodNeg = diarioDAO.buscaCodNeg();
      if (nonNull(listCodNeg)) {
        listCodNeg
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
                throw new RuntimeException("Erro ao calcular Candlestick");
              }
            });
      }
    } catch (Exception e) {
      log.error("Erro ao calcular Candlestick {} {} ",
          e.getMessage(),
          e.getCause()
      );
      throw new Exception("Erro ao calcular Candlestick");
    }
    return Boolean.TRUE;
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
              throw new RuntimeException("Erro ao tentar gerar candle semanal");
            }
            CandlestickSemanalMessage message = candlestickConverter
                .convertMessageSemanal(candlestickSemanal);
            listener.onAfterSave(message, TopicosDiarioSemanal.CANDLESTICK_SEMANAL.getTopicName());
          });
      log.info("Finalizando Código de negociação: " + codneg);
    } catch (Exception ex) {
      log.error("Erro ao tentar gerar candle semanal {} ", ex.getMessage());
      throw new Exception("Erro ao tentar gerar candle semanal");
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