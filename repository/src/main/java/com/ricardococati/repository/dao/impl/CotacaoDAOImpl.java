package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.dto.CotacaoDTO;
import com.ricardococati.model.dto.SplitInplit;
import com.ricardococati.repository.dao.CotacaoDAO;
import com.ricardococati.repository.dao.sqlutil.CotacaoSQLUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CotacaoDAOImpl implements CotacaoDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final CotacaoSQLUtil sqlUtil;

  @Override
  public boolean incluirCotacao(CotacaoDTO cotacaoDTO) throws Exception {
    int retorno = 0;
    try {
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParametersInsert(cotacaoDTO));
    } catch (Exception ex) {
      log.error("Erro na execução do método incluirCotacao: " + ex.getMessage());
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
}
