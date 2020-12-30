package com.ricardococati.carga.adapters.repositories.candlestick.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.candlestick.CandlestickDiarioInserirDAO;
import com.ricardococati.carga.adapters.repositories.gerasequencia.GeraSequenciaDAO;
import com.ricardococati.carga.adapters.repositories.candlestick.sqlutil.CandlestickDiarioInserirSQLUtil;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
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
  public Boolean incluirCandlestickDiario(final CandlestickDiario diarioDTO) throws Exception {
    int retorno = 0;
    if (isNull(diarioDTO)
        || isNull(diarioDTO.getDtpreg())
        || isNull(diarioDTO.getCodneg())) {
      throw new DataIntegrityViolationException(
          "Violação de chave na inserção de CANDLESTICK_DIARIO");
    }
    try {
      diarioDTO.setIdCandleDiario(
          genericDAO.getSequence("CANDLESTICK_SEQ").longValue()
      );
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParameters(diarioDTO));
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_DIARIO: {} ", ex.getMessage());
      throw new Exception("Erro na execução do método CANDLESTICK_DIARIO");
    }
    return retorno > 0;
  }

}
