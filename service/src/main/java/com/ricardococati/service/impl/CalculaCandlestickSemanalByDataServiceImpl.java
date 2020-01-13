package com.ricardococati.service.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.ricardococati.kafka.topic.TopicEnum;
import com.ricardococati.model.dto.CandlestickDiario;
import com.ricardococati.model.dto.CandlestickSemanal;
import com.ricardococati.model.dto.CandlestickSemanalMessage;
import com.ricardococati.repository.dao.CandlestickDiarioDAO;
import com.ricardococati.repository.dao.CandlestickSemanalDAO;
import com.ricardococati.repository.dao.InserirCandlestickSemanalDAO;
import com.ricardococati.repository.event.PostgresEventListener;
import com.ricardococati.service.BuildCandlestickSemanalService;
import com.ricardococati.service.CalculaCandlestickSemanalByDataService;
import com.ricardococati.service.converter.CandlestickConverter;
import com.ricardococati.service.util.DateServiceUtils;
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
public class CalculaCandlestickSemanalByDataServiceImpl implements
    CalculaCandlestickSemanalByDataService {

  private static final boolean SEMANA_GERADA = false;
  private final CandlestickSemanalDAO semanalDAO;
  private final InserirCandlestickSemanalDAO inserirSemanalDAO;
  private final CandlestickDiarioDAO diarioDAO;
  private final PostgresEventListener listener;
  private final CandlestickConverter candlestickConverter;
  private final BuildCandlestickSemanalService buildSemanal;

  @Override
  public void execute(final LocalDate dataOrigem) {
    try {
      diarioDAO.buscaCodNeg()
          .stream()
          .filter(Objects::nonNull)
          .forEach(codneg -> {
            String codnegProcessado = geraCandleStickSemanal(codneg, dataOrigem);
            log.info("Código de negócio calculado para semana: " + codnegProcessado);
          });
    } catch (Exception e) {
      log.error("Erro ao calcular Candlestick {} {} ", e.getMessage(), e.getCause());
    }
  }

  private String geraCandleStickSemanal(final String codneg, final LocalDate dataOrigem) {
    log.info("Código de negociação: " + codneg);
    LocalDate primeiroDiaSemana = DateServiceUtils.obterPrimeiroDiaUtilDaSemana(dataOrigem);
    List<CandlestickDiario> diarioDTOList =
        diarioDAO.buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(
            primeiroDiaSemana,
            codneg
        );
    if(diarioDTOList != null && diarioDTOList.size() > 0) {
      CandlestickSemanal candlestickSemanal = buildSemanal.build(diarioDTOList);
      final Integer idSemanaAno = diarioDTOList.get(0).getIdSemanaAno();
      candlestickSemanal.setSemana(idSemanaAno);
      candlestickSemanal.setCodneg(codneg);
      inserirSemanalDAO.incluirCandlestickSemanal(candlestickSemanal);
      CandlestickSemanalMessage message = candlestickConverter
          .convertMessageSemanal(candlestickSemanal);
      listener.onAfterSave(message, TopicEnum.CANDLESTICK_SEMANAL.getTopicName());
      log.info("Finalizando Código de negociação: " + codneg);
    }
    return codneg;
  }

}
