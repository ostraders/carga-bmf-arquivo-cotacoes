package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.repository.dao.CandlestickDiarioDAO;
import com.ricardococati.repository.dao.GeraSequenciaDAO;
import com.ricardococati.repository.dao.mapper.CandlestickDiarioMapper;
import com.ricardococati.repository.dao.sqlutil.CandlestickDiarioSQLUtil;
import com.ricardococati.repository.util.SQLAppender;
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
public class CandlestickDiarioDAOImpl implements CandlestickDiarioDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final GeraSequenciaDAO genericDAO;
  private final CandlestickDiarioSQLUtil sqlUtil;
  private final CandlestickDiarioMapper mapper;

  @Override
  public Boolean incluirCandlestickDiario(final CandlestickDiario candlestickDiarioDTO) throws Exception {
    int retorno = 0;
    final SQLAppender sql = new SQLAppender(100);
    try {
      candlestickDiarioDTO.setIdCandleDiario(
          genericDAO.getSequence("CANDLESTICK_SEQ").longValue()
      );
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParameters(candlestickDiarioDTO));
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_DIARIO: " + ex.getMessage());
      throw ex;
    }
    return retorno > 0;
  }

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
