package com.ricardococati.carga.adapters.repositories.empresa.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.ricardococati.carga.adapters.repositories.empresa.EmpresaBuscarDAO;
import com.ricardococati.carga.adapters.repositories.empresa.mapper.EmpresaMapper;
import com.ricardococati.carga.adapters.repositories.empresa.sqlutil.EmpresaBuscarSQLUtil;
import com.ricardococati.carga.entities.domains.empresa.Empresa;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaBuscarDAOImpl implements EmpresaBuscarDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;
  private final EmpresaBuscarSQLUtil sqlUtil;
  private final EmpresaMapper mapper;

  @Override
  public Empresa buscaEmpresaPorNome(final String nomeEmpresa) throws Exception {
    if (isNull(nomeEmpresa)) {
      throw new DataIntegrityViolationException(
          "Constraint incorreta para a busca de EMPRESA ");
    }
    try {
      return template.queryForObject(
          sqlUtil.getSelect(),
          sqlUtil.toParameters(nomeEmpresa),
          (rs, rowNum) -> mapper.mapper(rs)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método BUSCAR EMPRESA: {} ", ex.getMessage());
      throw new RuntimeException("Erro na execução do método BUSCAR EMPRESA");
    }
  }

  @Override
  public List<Empresa> buscaEmpresasPorNome(final String nomeEmpresa, final Integer limit, final Long offset) throws Exception {
    Boolean whereNomeEmpresa = Boolean.FALSE;
    if (nonNull(nomeEmpresa)) {
      whereNomeEmpresa = Boolean.TRUE;
    }
    try {
      return template.query(
          sqlUtil.getSelectOnly(whereNomeEmpresa),
          sqlUtil.toParametersOnly(nomeEmpresa, limit, offset),
          (rs, rowNum) -> mapper.mapper(rs)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método BUSCAR EMPRESA: {} ", ex.getMessage());
      throw new RuntimeException("Erro na execução do método BUSCAR EMPRESA");
    }
  }

  @Override
  public Long quantidadeEmpresa() {
    Long result = 0L;
    try {
      result = template.queryForObject(
          sqlUtil.getSelectCount(),
          new MapSqlParameterSource(),
          Long.class
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método QUANTIDADE_EMPRESA: {} ", ex.getMessage());
      new RuntimeException(ex);
    }
    return result;
  }

  @Override
  public List<Empresa> buscaEmpresasPorNome(int pageSize, long offset) {
    try {
      return template.query(
          sqlUtil.getSelectAll(),
          sqlUtil.toParametersAll(pageSize, offset),
          (rs, rowNum) -> mapper.mapper(rs)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método BUSCAR EMPRESA: {} ", ex.getMessage());
      throw new RuntimeException("Erro na execução do método BUSCAR EMPRESA", ex);
    }
  }

}
