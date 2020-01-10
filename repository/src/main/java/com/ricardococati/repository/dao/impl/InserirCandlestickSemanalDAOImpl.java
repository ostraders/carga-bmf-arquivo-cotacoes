package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.dto.CandlestickSemanal;
import com.ricardococati.repository.dao.GenericDAO;
import com.ricardococati.repository.dao.InserirCandlestickSemanalDAO;
import com.ricardococati.repository.dao.sqlutil.CandlestickSemanalSQLUtil;
import com.ricardococati.repository.dao.sqlutil.InserirCandlestickSemanalSQLUtil;
import com.ricardococati.repository.util.SQLAppender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InserirCandlestickSemanalDAOImpl implements InserirCandlestickSemanalDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final GenericDAO genericDAO;
  private final InserirCandlestickSemanalSQLUtil sqlUtil;

  @Override
  public Boolean incluirCandlestickSemanal(CandlestickSemanal semanal) {
    int retorno = 0;
    final SQLAppender sql = new SQLAppender(100);
    try {
      semanal.setIdCandleSemanal(
          genericDAO.getSequence("CANDLESTICK_SEMANAL_SEQ", template).longValue()
      );
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParameters(semanal));
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_DIARIO: " + ex.getMessage());
      throw ex;
    }
    return retorno > 0;
  }

}
