package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.entities.CandlestickSemanal;
import com.ricardococati.repository.dao.GeraSequenciaDAO;
import com.ricardococati.repository.dao.CandlestickSemanalInserirDAO;
import com.ricardococati.repository.dao.sqlutil.CandlestickSemanalInserirSQLUtil;
import com.ricardococati.repository.util.SQLAppender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CandlestickSemanalInserirDAOImpl implements CandlestickSemanalInserirDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final GeraSequenciaDAO genericDAO;
  private final CandlestickSemanalInserirSQLUtil sqlUtil;

  @Override
  public Boolean incluirCandlestickSemanal(CandlestickSemanal semanal) {
    int retorno = 0;
    final SQLAppender sql = new SQLAppender(100);
    try {
      semanal.setIdCandleSemanal(
          genericDAO.getSequence("CANDLESTICK_SEMANAL_SEQ").longValue()
      );
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParameters(semanal));
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_SEMANAL: {}", ex.getMessage());
      throw ex;
    }
    return retorno > 0;
  }

}
