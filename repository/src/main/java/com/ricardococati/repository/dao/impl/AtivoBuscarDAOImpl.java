package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.entities.Ativo;
import com.ricardococati.repository.dao.AtivoBuscarDAO;
import com.ricardococati.repository.dao.mapper.AtivoMapper;
import com.ricardococati.repository.dao.sqlutil.AtivoSQLUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AtivoBuscarDAOImpl implements AtivoBuscarDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final AtivoSQLUtil sqlUtil;
  private final AtivoMapper mapper;

  @Override
  public List<Ativo> buscaAtivo() throws Exception {
    try {
      return template.query(
          sqlUtil.getSelectAtivos(),
          (rs, rowNum) -> mapper.mapper(rs)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método ATIVO: {} ", ex.getMessage());
      throw new Exception("Erro na execução do método ATIVO");
    }
  }
}
