package com.ricardococati.carga.adapters.repositories.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.CandlestickSemanalInserirDAO;
import com.ricardococati.carga.adapters.repositories.GeraSequenciaDAO;
import com.ricardococati.carga.adapters.repositories.sqlutil.CandlestickSemanalInserirSQLUtil;
import com.ricardococati.carga.entities.domains.CandlestickSemanal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
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
  public Boolean incluirCandlestickSemanal(CandlestickSemanal semanal) throws Exception {
    int retorno = 0;
    if (isNull(semanal)
        || isNull(semanal.getDtpregini())
        || isNull(semanal.getCodneg())) {
      throw new DataIntegrityViolationException(
          "Violação de chave na inserção de CANDLESTICK_SEMANAL");
    }
    try {
      semanal.setIdCandleSemanal(
          genericDAO.getSequence("CANDLESTICK_SEMANAL_SEQ").longValue()
      );
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParameters(semanal));
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_SEMANAL: {} ", ex.getMessage());
      throw new Exception("Erro na execução do método CANDLESTICK_SEMANAL");
    }
    return retorno > 0;
  }

}
