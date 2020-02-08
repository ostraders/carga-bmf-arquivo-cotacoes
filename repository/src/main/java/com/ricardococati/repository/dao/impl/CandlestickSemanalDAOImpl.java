package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.entities.CandlestickSemanal;
import com.ricardococati.repository.dao.CandlestickSemanalDAO;
import com.ricardococati.repository.dao.mapper.CandlestickSemanalMapper;
import com.ricardococati.repository.dao.sqlutil.CandlestickSemanalSQLUtil;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CandlestickSemanalDAOImpl implements CandlestickSemanalDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final CandlestickSemanalSQLUtil sqlUtil;
  private final CandlestickSemanalMapper mapper;

  @Override
  public List<CandlestickSemanal> buscarCandleSemanalPorPrimeiroDiaSemana(
      final LocalDate primeiroDiaUtilSemanaCorrente
  ) {
    return template.query(
        sqlUtil.getSelectCandleSemanalByDtPregIni(),
        sqlUtil.toParametersCandleSemanalByDtPregIni(primeiroDiaUtilSemanaCorrente),
        (rs, rowNum) -> mapper.mapper(rs)
    );
  }

}
