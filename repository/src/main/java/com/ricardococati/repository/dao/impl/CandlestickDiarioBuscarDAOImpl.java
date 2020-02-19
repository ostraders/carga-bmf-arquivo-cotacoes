package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.repository.dao.CandlestickDiarioBuscarDAO;
import com.ricardococati.repository.dao.mapper.CandlestickDiarioMapper;
import com.ricardococati.repository.dao.sqlutil.CandlestickDiarioBuscarSQLUtil;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CandlestickDiarioBuscarDAOImpl implements CandlestickDiarioBuscarDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final CandlestickDiarioBuscarSQLUtil sqlUtil;
  private final CandlestickDiarioMapper mapper;

  @Override
  public List<String> buscaCodNeg() {
    return template.query(
        sqlUtil.getSelectCodNeg(),
        new MapSqlParameterSource(),
        (rs, rowNum) -> mapper.mapperCodNeg(rs)
    );
  }

  @Override
  public List<CandlestickDiario> buscaCandleDiarioPorCodNegSemanaGerada(String codneg) {
    return template.query(
        sqlUtil.getSelectByCodNegESemana(),
        sqlUtil.toParametersSelectByCodNeg(codneg),
        (rs, rowNum) -> mapper.mapper(rs)
    );
  }

  @Override
  public List<CandlestickDiario> buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(
      final LocalDate primeiroDiaUtilSemanaCorrente,
      final String codneg
  ) {
    return template.query(
        sqlUtil.getSelectCandleDiarioByDtPregCodneg(),
        sqlUtil.toParametersCandleDiarioByDtPregCodneg(
            primeiroDiaUtilSemanaCorrente,
            codneg
        ),
        (rs, rowNum) -> mapper.mapper(rs)
    );
  }

}
