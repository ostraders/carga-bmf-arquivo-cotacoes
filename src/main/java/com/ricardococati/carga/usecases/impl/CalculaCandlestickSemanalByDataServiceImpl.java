package com.ricardococati.carga.usecases.impl;

import com.ricardococati.carga.adapters.message.event.CandlestickEventListener;
import com.ricardococati.carga.adapters.message.topic.TopicosDiarioSemanal;
import com.ricardococati.carga.adapters.repositories.CandlestickDiarioBuscarDAO;
import com.ricardococati.carga.adapters.repositories.CandlestickSemanalInserirDAO;
import com.ricardococati.carga.entities.domains.CandlestickDiario;
import com.ricardococati.carga.entities.domains.CandlestickSemanal;
import com.ricardococati.carga.entities.domains.CandlestickSemanalMessage;
import com.ricardococati.carga.usecases.BuildCandlestickSemanalService;
import com.ricardococati.carga.usecases.CalculaCandlestickSemanalByDataService;
import com.ricardococati.carga.utils.converter.CandlestickConverter;
import com.ricardococati.carga.utils.geral.DateServiceUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculaCandlestickSemanalByDataServiceImpl implements
    CalculaCandlestickSemanalByDataService {

  private final CandlestickSemanalInserirDAO inserirSemanalDAO;
  private final CandlestickDiarioBuscarDAO diarioDAO;
  private final CandlestickEventListener listener;
  private final CandlestickConverter candlestickConverter;
  private final BuildCandlestickSemanalService buildSemanal;

  @Override
  public void execute(final LocalDate dataOrigem) throws Exception {
    try {
      diarioDAO.buscaCodNeg()
          .parallelStream()
          .filter(Objects::nonNull)
          .forEach(codneg -> {
            try {
              geraCandleStickSemanal(codneg, dataOrigem);
            } catch (Exception e) {
              log.error("Erro ao tentar gerar candle semanal");
              throw new RuntimeException("Erro ao tentar gerar candle semanal");
            }
          });
    } catch (Exception e) {
      log.error("Erro ao tentar gerar candle semanal {} {} ", e.getMessage(), e.getCause());
      throw new Exception("Erro ao tentar gerar candle semanal");
    }
  }

  private String geraCandleStickSemanal(final String codneg, final LocalDate dataOrigem)
      throws Exception {
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
      listener.onAfterSave(message, TopicosDiarioSemanal.CANDLESTICK_SEMANAL.getTopicName());
      log.info("Finalizando Código de negociação: " + codneg);
    }
    return codneg;
  }

}

