package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.dto.EmpresaAtivo;
import com.ricardococati.repository.dao.EmpresaAtivoDAO;
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
public class EmpresaAtivoDAOImpl implements EmpresaAtivoDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final EmpresaAtivoSQLUtil sqlUtil;
  private final EmpresaAtivoMapper mapper;

  @Override
  public List<EmpresaAtivo> buscaEmpresaAtivo() {
    return template.query(
        sqlUtil.getSelectAtivos(),
        (rs, rowNum) -> mapper.mapper(rs)
    );
  }
}
