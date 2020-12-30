package com.ricardococati.carga.adapters.repositories.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.GeraSequenciaDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GeraSequenciaDAOImpl implements GeraSequenciaDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  @Override
  public Number getSequence(final String sequencia) {
    if (isNull(sequencia)) {
      throw new IllegalArgumentException("A sequencia enviada não pode ser nula!", new Throwable());
    }
    return template.query("SELECT NEXTVAL('" + sequencia + "')" , rs -> {
      rs.next();
      Number number = (Number) rs.getObject(1);
      return number;
    });
  }

}
