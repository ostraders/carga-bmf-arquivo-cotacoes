package com.ricardococati.carga.adapters.repositories.empresaativo.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.empresaativo.EmpresaAtivoInsere;
import com.ricardococati.carga.adapters.repositories.empresaativo.sqlutil.EmpresaAtivoInserirSQLUtil;
import com.ricardococati.carga.entities.domains.empresaativo.EmpresaAtivo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaAtivoInsereImpl implements EmpresaAtivoInsere {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;
  private final EmpresaAtivoInserirSQLUtil sqlUtil;

  @Override
  public Boolean insereAtivoEmpresa(final EmpresaAtivo empresaAtivo) {
    int retorno = 0;
    if (isNull(empresaAtivo)
        || isNull(empresaAtivo.getIdAtivo())
        || isNull(empresaAtivo.getIdEmpresa())) {
      throw new DataIntegrityViolationException("Violação de chave na inserção de EMPRESA_ATIVO");
    }
    try {
      retorno = template.update(sqlUtil.getInsert(), sqlUtil.toParameters(empresaAtivo));
    } catch (Exception ex) {
      log.error("Erro na execução do método EMPRESA_ATIVO: " + ex.getMessage());
      throw ex;
    }
    return retorno > 0;
  }

}
