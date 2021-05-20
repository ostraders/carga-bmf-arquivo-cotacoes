package com.ricardococati.carga.adapters.repositories.empresa.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.candlestick.CandlestickDiarioBuscarDAO;
import com.ricardococati.carga.adapters.repositories.candlestick.mapper.CandlestickDiarioMapper;
import com.ricardococati.carga.adapters.repositories.candlestick.sqlutil.CandlestickDiarioBuscarSQLUtil;
import com.ricardococati.carga.adapters.repositories.empresa.EmpresaBuscarDAO;
import com.ricardococati.carga.adapters.repositories.empresa.mapper.EmpresaMapper;
import com.ricardococati.carga.adapters.repositories.empresa.sqlutil.EmpresaBuscarSQLUtil;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.empresa.Empresa;
import java.time.LocalDate;
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

}
