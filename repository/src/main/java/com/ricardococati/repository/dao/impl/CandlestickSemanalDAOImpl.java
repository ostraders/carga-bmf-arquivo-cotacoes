package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.dto.CandlestickSemanal;
import com.ricardococati.model.dto.SplitInplit;
import com.ricardococati.repository.dao.CandlestickSemanalDAO;
import com.ricardococati.repository.dao.mapper.CandlestickSemanalMapper;
import com.ricardococati.repository.dao.sqlutil.CandlestickSemanalSQLUtil;
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
public class CandlestickSemanalDAOImpl implements CandlestickSemanalDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final CandlestickSemanalSQLUtil sqlUtil;
  private final CandlestickSemanalMapper mapper;

  @Override
  public Integer contaCandleDiarioSemCandleSemanalGerado() {
    return template.queryForObject(
        sqlUtil.getSelectCount(),
        new MapSqlParameterSource(),
        Integer.class
    );
  }

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

  @Override
  public Boolean split(final SplitInplit splitInplit) {
    int retorno = 0;
    final String operacao = "/";
    try {
      retorno = template.update(sqlUtil.getUpdate(operacao),
          sqlUtil.toParametersUpdate(splitInplit));
    } catch (Exception ex) {
      log.error("Erro na execução do método split: " + ex.getMessage());
      throw ex;
    }
    return retorno > 0;
  }

  @Override
  public Boolean inplit(final SplitInplit splitInplit) {
    int retorno = 0;
    final String operacao = "*";
    try {
      retorno = template.update(sqlUtil.getUpdate(operacao),
          sqlUtil.toParametersUpdate(splitInplit));
    } catch (Exception ex) {
      log.error("Erro na execução do método inplit: " + ex.getMessage());
      throw ex;
    }
    return retorno > 0;
  }
}
