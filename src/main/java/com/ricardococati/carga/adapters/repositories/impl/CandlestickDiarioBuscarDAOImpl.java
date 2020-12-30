package com.ricardococati.carga.adapters.repositories.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.CandlestickDiarioBuscarDAO;
import com.ricardococati.carga.adapters.repositories.mapper.CandlestickDiarioMapper;
import com.ricardococati.carga.adapters.repositories.sqlutil.CandlestickDiarioBuscarSQLUtil;
import com.ricardococati.carga.entities.domains.CandlestickDiario;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
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
  public List<String> buscaCodNeg() throws Exception {
    try {
      return template.query(
          sqlUtil.getSelectCodNeg(),
          new MapSqlParameterSource(),
          (rs, rowNum) -> mapper.mapperCodNeg(rs)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_DIARIO: {} ", ex.getMessage());
      throw new Exception("Erro na execução do método CANDLESTICK_DIARIO");
    }
  }

  @Override
  public List<CandlestickDiario> buscaCandleDiarioPorCodNegSemanaGerada(
      final String codneg
  ) throws Exception {
    if (isNull(codneg)) {
      throw new DataIntegrityViolationException(
          "Violação de chave na inserção de CANDLESTICK_DIARIO");
    }
    try {
      return template.query(
          sqlUtil.getSelectByCodNegESemana(),
          sqlUtil.toParametersSelectByCodNeg(codneg),
          (rs, rowNum) -> mapper.mapper(rs)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_DIARIO: {} ", ex.getMessage());
      throw new Exception("Erro na execução do método CANDLESTICK_DIARIO");
    }
  }

  @Override
  public List<CandlestickDiario> buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(
      final LocalDate primeiroDiaUtilSemanaCorrente,
      final String codneg
  ) throws Exception {
    if (isNull(primeiroDiaUtilSemanaCorrente)
        || isNull(codneg)) {
      throw new DataIntegrityViolationException(
          "Violação de chave na inserção de CANDLESTICK_DIARIO");
    }
    try {
      return template.query(
          sqlUtil.getSelectCandleDiarioByDtPregCodneg(),
          sqlUtil.toParametersCandleDiarioByDtPregCodneg(
              primeiroDiaUtilSemanaCorrente,
              codneg
          ),
          (rs, rowNum) -> mapper.mapper(rs)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_DIARIO: {} ", ex.getMessage());
      throw new Exception("Erro na execução do método CANDLESTICK_DIARIO");
    }
  }

}
