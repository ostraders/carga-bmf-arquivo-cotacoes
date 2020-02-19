package com.ricardococati.repository.dao.impl;

import static java.util.Objects.isNull;

import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.repository.dao.CandlestickDiarioInserirDAO;
import com.ricardococati.repository.dao.GeraSequenciaDAO;
import com.ricardococati.repository.dao.sqlutil.CandlestickDiarioInserirSQLUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CandlestickDiarioInserirDAOImpl implements CandlestickDiarioInserirDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final GeraSequenciaDAO genericDAO;
  private final CandlestickDiarioInserirSQLUtil sqlUtil;

  @Override
  public Boolean incluirCandlestickDiario(final CandlestickDiario diarioDTO) {
    int retorno = 0;
    if (isNull(diarioDTO)
        || isNull(diarioDTO.getDtpreg())
        || isNull(diarioDTO.getCodneg())) {
      throw new DataIntegrityViolationException("Violação de chave na inserção de CANDLESTICK_DIARIO");
    }
    try {
      diarioDTO.setIdCandleDiario(
          genericDAO.getSequence("CANDLESTICK_SEQ").longValue()
      );
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParameters(diarioDTO));
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_DIARIO: " + ex.getMessage());
      throw ex;
    }
    return retorno > 0;
  }

}
