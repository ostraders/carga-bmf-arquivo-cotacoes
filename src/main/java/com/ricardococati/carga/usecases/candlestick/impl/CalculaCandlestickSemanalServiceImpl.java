package com.ricardococati.carga.usecases.candlestick.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.carga.adapters.messages.action.CandlestickActionListener;
import com.ricardococati.carga.adapters.messages.topic.TopicosDiarioSemanal;
import com.ricardococati.carga.adapters.repositories.candlestick.CandlestickDiarioBuscarDAO;
import com.ricardococati.carga.adapters.repositories.candlestick.CandlestickSemanalInserirDAO;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanal;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanalMessage;
import com.ricardococati.carga.usecases.candlestick.BuildCandlestickSemanalService;
import com.ricardococati.carga.usecases.candlestick.CalculaCandlestickSemanalService;
import com.ricardococati.carga.usecases.cotacao.converter.CandlestickConverter;
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
  private final CandlestickActionListener listener;
  private final CandlestickConverter candlestickConverter;
  private final BuildCandlestickSemanalService buildSemanal;

  @Override
  public List<CandlestickSemanal> execute() throws Exception {
    List<CandlestickSemanal> resultList = new ArrayList<>();
    try {
      final List<String> listCodNeg = diarioDAO.buscaCodNeg();
      if (nonNull(listCodNeg)) {
        listCodNeg
            .parallelStream()
            .filter(Objects::nonNull)
            .forEach(codneg -> {
              try {
                resultList.addAll(geraCandleStickSemanal(codneg));
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
    return resultList;
  }

  private List<CandlestickSemanal> geraCandleStickSemanal(final String codneg) throws Exception {
    List<CandlestickSemanal> resultList = new ArrayList<>();
    log.info("Código de negociação: " + codneg);
    List<CandlestickDiario> diarioDTOList = diarioDAO.buscaCandleDiarioPorCodNeg(codneg);
    Map<String, List<CandlestickDiario>> mapDiario = getCandlestickListToMapStringList(diarioDTOList);
    try {
      mapDiario
          .entrySet()
          .forEach(integerEntry -> {
            CandlestickSemanal candlestickSemanal =
                buildSemanal.build(mapDiario.get(integerEntry.getKey()));
            candlestickSemanal.setSemana(integerEntry.getValue().get(0).getIdSemanaAno());
            candlestickSemanal.setCodneg(codneg);
            try {
              resultList.add(candlestickSemanal);
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
    return resultList;
  }

  private Map<String, List<CandlestickDiario>> getCandlestickListToMapStringList(
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
