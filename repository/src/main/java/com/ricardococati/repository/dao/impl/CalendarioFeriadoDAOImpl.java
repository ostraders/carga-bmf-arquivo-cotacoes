package com.ricardococati.repository.dao.impl;

import com.ricardococati.repository.dao.CalendarioFeriadoDAO;
import com.ricardococati.repository.dao.sqlutil.CalendarioFeriadoSQLUtil;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CalendarioFeriadoDAOImpl implements CalendarioFeriadoDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final CalendarioFeriadoSQLUtil sqlUtil;

  @Override
  public Boolean buscaCalendarioFeriado(final LocalDate dataAtual) {
    return template.queryForObject(
        sqlUtil.getSelectCountByDataAtual(),
        sqlUtil.toParametersSelectByDataAtual(dataAtual),
        Integer.class
    ) > 0;
  }
}
