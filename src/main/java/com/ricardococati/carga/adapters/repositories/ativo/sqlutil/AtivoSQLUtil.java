package com.ricardococati.carga.adapters.repositories.ativo.sqlutil;

import com.ricardococati.carga.utils.SQLAppender;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class AtivoSQLUtil {

  public String getSelectAtivos() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" select  ");
    sql.appendSQL(" 	id_ativo, ");
    sql.appendSQL(" 	ativo ");
    sql.appendSQL(" from  ");
    sql.appendSQL(" 	ativo ");
    sql.appendSQL(" order by ativo asc ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public String getSelectAtivosByAtivo() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" select  ");
    sql.appendSQL(" 	id_ativo, ");
    sql.appendSQL(" 	ativo ");
    sql.appendSQL(" from  ");
    sql.appendSQL(" 	ativo ");
    sql.appendSQL("	where ativo LIKE '%:ativo%' ");
    sql.appendSQL(" order by ativo asc ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersSelectByAtivo(final String ativo) {
    return new MapSqlParameterSource()
        .addValue("ativo", ativo);
  }

}
