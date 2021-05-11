package com.ricardococati.carga.adapters.repositories.empresa.impl;

import com.ricardococati.carga.adapters.repositories.empresa.EmpresaInserirDAO;
import com.ricardococati.carga.adapters.repositories.gerasequencia.GeraSequenciaDAO;
import com.ricardococati.carga.entities.domains.empresa.Empresa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaInserirDAOImpl implements EmpresaInserirDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final GeraSequenciaDAO genericDAO;

  @Override
  public Boolean incluirEmpresa(final Empresa empresa) throws Exception {
    return null;
  }

}
