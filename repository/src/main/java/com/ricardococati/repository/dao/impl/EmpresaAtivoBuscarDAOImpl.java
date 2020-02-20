package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.entities.EmpresaAtivo;
import com.ricardococati.repository.dao.EmpresaAtivoBuscarDAO;
import com.ricardococati.repository.dao.mapper.EmpresaAtivoMapper;
import com.ricardococati.repository.dao.sqlutil.EmpresaAtivoSQLUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaAtivoBuscarDAOImpl implements EmpresaAtivoBuscarDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final EmpresaAtivoSQLUtil sqlUtil;
  private final EmpresaAtivoMapper mapper;

  @Override
  public List<EmpresaAtivo> buscaEmpresaAtivo() throws Exception {
    try {
      return template.query(
          sqlUtil.getSelectAtivos(),
          (rs, rowNum) -> mapper.mapper(rs)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método EMPRESA_ATIVO: {} ", ex.getMessage());
      throw new Exception("Erro na execução do método EMPRESA_ATIVO");
    }
  }
}
