package com.ricardococati.carga.adapters.repositories.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.sqlutil.CalendarioFeriadoSQLUtil;
import com.ricardococati.carga.adapters.repositories.CalendarioFeriadoDAO;
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
  public Boolean buscaCalendarioFeriado(final LocalDate dataAtual) throws Exception {
    Integer result = 0;
    if (isNull(dataAtual)) {
      throw new NullPointerException(
          "Data atual está nula para pesquisar CALENDARIO_FERIADO");
    }
    try {
      result = template.queryForObject(
          sqlUtil.getSelectCountByDataAtual(),
          sqlUtil.toParametersSelectByDataAtual(dataAtual),
          Integer.class
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método CALENDARIO_FERIADO: {} ", ex.getMessage());
      throw new Exception("Erro na execução do método CALENDARIO_FERIADO");
    }
    return result > 0;
  }

}
