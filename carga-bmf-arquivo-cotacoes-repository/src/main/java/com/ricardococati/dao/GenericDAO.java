package com.ricardococati.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ricardococati.enums.DataBaseInfosEnum;

@Repository
public class GenericDAO  {

  @Autowired
  protected DataSource dataSource;

  @Autowired
  protected JdbcTemplate jdbcTemplate;

  public Number getSequence(String sequenceName, JdbcTemplate template) {
    if (sequenceName == null) {
      throw new IllegalArgumentException("The passed sequence name was null", new Throwable());
    }
    return template.query("SELECT NEXTVAL('" + DataBaseInfosEnum.SCHEMA.getTexto() + ".\"" + sequenceName + "\"')" , new ResultSetExtractor<Number>() {
      public Number extractData(ResultSet rs) throws SQLException, DataAccessException {
        rs.next();
        Number number = (Number) rs.getObject(1);
        return number;
      }
    });
  }

  public Long obterSequenceLong(String nomeSequence) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    return getSequence(nomeSequence, jdbcTemplate).longValue();
  }
}
