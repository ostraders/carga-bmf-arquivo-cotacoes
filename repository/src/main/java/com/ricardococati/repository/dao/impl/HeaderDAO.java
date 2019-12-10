package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.dto.HeaderDTO;
import com.ricardococati.repository.dao.IHeaderDAO;
import com.ricardococati.repository.dao.sqlutil.HeaderSQLUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class HeaderDAO implements IHeaderDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final HeaderSQLUtil sqlUtil;

  @Override
  public boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception {
    int retorno = 0;
    try {
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParameters(headerDTO));
    } catch (Exception ex) {
      log.error("Erro na execução do método incluirHeaderArquivo: " + ex.getMessage());
      throw ex;
    }
    return retorno > 0;
  }

}
