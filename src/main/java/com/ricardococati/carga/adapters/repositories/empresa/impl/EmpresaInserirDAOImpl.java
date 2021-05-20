package com.ricardococati.carga.adapters.repositories.empresa.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.empresa.EmpresaInserirDAO;
import com.ricardococati.carga.adapters.repositories.empresa.sqlutil.EmpresaInserirSQLUtil;
import com.ricardococati.carga.entities.domains.empresa.Empresa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaInserirDAOImpl implements EmpresaInserirDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final EmpresaInserirSQLUtil sqlUtil;

  @Override
  public Boolean incluirEmpresa(final Empresa empresa) throws RuntimeException {
    int retorno = 0;
    if (isNull(empresa)
        || isNull(empresa.getIdEmpresa())
        || isNull(empresa.getNomeEmpresa())) {
      throw new DataIntegrityViolationException(
          "Violação de chave na inserção de EMPRESA");
    }
    try {
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParameters(empresa));
    } catch (Exception ex) {
      log.error("Erro na execução do método INSERE EMPRESA: {} ", ex.getMessage());
      throw new RuntimeException("Erro na execução do método INSERE EMPRESA");
    }
    return retorno > 0;
  }

}
