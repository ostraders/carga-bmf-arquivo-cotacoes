package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.entities.SplitInplit;
import com.ricardococati.repository.dao.CandlestickSemanalAtualizarDAO;
import com.ricardococati.repository.dao.sqlutil.CandlestickSemanalAtualizarSQLUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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
  public Boolean atualizaSplitInplit(final SplitInplit splitInplit) {
    int retorno = 0;
    try {
      retorno = template.update(
          sqlUtil.getUpdateSplitInplit(splitInplit.getOperacao().getTipo()),
          sqlUtil.toParametersUpdateSplitInplit(splitInplit)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método split: " + ex.getMessage());
      throw ex;
    }
    return retorno > 0;
  }
}
