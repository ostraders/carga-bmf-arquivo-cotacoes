package com.ricardococati.carga.adapters.repositories.candlestick.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.candlestick.CandlestickSemanalAtualizarDAO;
import com.ricardococati.carga.adapters.repositories.candlestick.sqlutil.CandlestickSemanalAtualizarSQLUtil;
import com.ricardococati.carga.entities.domains.splitinplit.SplitInplit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CandlestickSemanalAtualizarDAOImpl implements CandlestickSemanalAtualizarDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final CandlestickSemanalAtualizarSQLUtil sqlUtil;

  @Override
  public Boolean atualizaSplitInplit(final SplitInplit splitInplit) throws Exception {
    int retorno = 0;
    if (isNull(splitInplit)
        || isNull(splitInplit.getOperacao())
        || isNull(splitInplit.getDtpreg())
        || isNull(splitInplit.getQtdSplitInplit())
        || isNull(splitInplit.getCodneg())) {
      throw new DataIntegrityViolationException(
          "Violação de chave na atualização de CANDLESTICK_SEMANAL");
    }
    try {
      retorno = template.update(
          sqlUtil.getUpdateSplitInplit(splitInplit.getOperacao().getTipo()),
          sqlUtil.toParametersUpdateSplitInplit(splitInplit)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_SEMANAL: {} ", ex.getMessage());
      throw new Exception("Erro na execução do método CANDLESTICK_SEMANAL");
    }
    return retorno > 0;
  }

}
