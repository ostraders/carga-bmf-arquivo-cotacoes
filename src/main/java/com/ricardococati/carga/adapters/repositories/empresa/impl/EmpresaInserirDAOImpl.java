package com.ricardococati.carga.adapters.repositories.empresa.impl;

import com.ricardococati.carga.adapters.repositories.empresa.EmpresaInserirDAO;
import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaInserirDAOImpl implements EmpresaInserirDAO {

  @Override
  public Boolean incluirEmpresa(final EmpresaDTO empresaDTO) throws Exception {
    return null;
  }

}
