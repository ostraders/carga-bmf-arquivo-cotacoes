package com.ricardococati.carga.adapters.repositories.ativo.impl;

import com.ricardococati.carga.adapters.repositories.ativo.AtivoBuscarDAO;
import com.ricardococati.carga.adapters.repositories.ativo.mapper.AtivoMapper;
import com.ricardococati.carga.adapters.repositories.ativo.sqlutil.AtivoSQLUtil;
import com.ricardococati.carga.entities.domains.ativo.Ativo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
