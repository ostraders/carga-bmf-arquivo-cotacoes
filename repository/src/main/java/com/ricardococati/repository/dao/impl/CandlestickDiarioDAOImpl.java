package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.dto.CandlestickDiario;
import com.ricardococati.model.dto.SplitInplit;
import com.ricardococati.repository.dao.GenericDAO;
import com.ricardococati.repository.dao.CandlestickDiarioDAO;
import com.ricardococati.repository.dao.mapper.CandlestickDiarioMapper;
import com.ricardococati.repository.dao.sqlutil.CandlestickDiarioSQLUtil;
import com.ricardococati.repository.util.SQLAppender;
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
public class CandlestickDiarioDAOImpl implements CandlestickDiarioDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final GenericDAO genericDAO;
  private final CandlestickDiarioSQLUtil sqlUtil;
  private final CandlestickDiarioMapper mapper;

  @Override
  public Boolean incluirCandlestickDiario(final CandlestickDiario candlestickDiarioDTO) throws Exception {
    int retorno = 0;
    final SQLAppender sql = new SQLAppender(100);
    try {
      candlestickDiarioDTO.setIdCandleDiario(
          genericDAO.getSequence("CANDLESTICK_SEQ", template).longValue()
      );
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParameters(candlestickDiarioDTO));
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_DIARIO: " + ex.getMessage());
      throw ex;
    }
    return retorno > 0;
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

  @Override
  public List<String> buscaCodNegSemanaGeradaFalse() {
    return template.query(
        sqlUtil.getSelectCodNegPorSemanaGeradaFalse(),
        new MapSqlParameterSource(),
        (rs, rowNum) -> mapper.mapperCodNeg(rs)
    );
  }

  @Override
  public List<CandlestickDiario> buscaCandleDiarioPorCodNeg(String codneg) {
    return template.query(
        sqlUtil.getSelectByCodNeg(),
        sqlUtil.toParametersSelectByCodNeg(codneg),
        (rs, rowNum) -> mapper.mapper(rs)
    );
  }

  @Override
  public Boolean salvaCandlestickDiario(CandlestickDiario candlestickDiarioDTO) {
    int retorno = 0;
    final String operacao = "*";
    try {
      retorno = template.update(sqlUtil.getUpdateSemanaGerada(),
          sqlUtil.toParametersUpdateSemanaGerada(candlestickDiarioDTO));
    } catch (Exception ex) {
      log.error("Erro na execução do método inplit: " + ex.getMessage());
      throw ex;
    }
    return retorno > 0;
  }

  @Override
  public List<CandlestickDiario> buscaCandleDiarioPorCodNegSemanaGerada(String codneg) {
    return template.query(
        sqlUtil.getSelectByCodNegESemanaGeradaFalse(),
        sqlUtil.toParametersSelectByCodNeg(codneg),
        (rs, rowNum) -> mapper.mapper(rs)
    );
  }

}
