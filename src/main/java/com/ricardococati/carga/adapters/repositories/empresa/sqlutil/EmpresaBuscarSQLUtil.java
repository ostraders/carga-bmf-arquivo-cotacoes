package com.ricardococati.carga.adapters.repositories.empresa.sqlutil;

import com.ricardococati.carga.utils.SQLAppender;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class EmpresaBuscarSQLUtil {

  public String getSelect() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(selectCommom());
    sql.appendSQL("	where nome_empresa = :nomeEmpresa ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParameters(final String nomeEmpresa) {
    return new MapSqlParameterSource()
        .addValue("nomeEmpresa", nomeEmpresa);
  }

  public String getSelectOnly(final Boolean whereNomeEmpresa) {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(selectCommom());
    if (whereNomeEmpresa) {
      sql.appendSQL("	where nome_empresa = :nomeEmpresa ");
    }
    sql.appendSQL("  limit :limit offset :offset ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public String selectCommom() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" select ");
    sql.appendSQL("		id_empresa, ");
    sql.appendSQL("		nome_empresa, ");
    sql.appendSQL("		id_setor, ");
    sql.appendSQL("		id_sub_setor, ");
    sql.appendSQL("		id_segmento ");
    sql.appendSQL(" from empresa ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersOnly(
      final String nomeEmpresa,
      final Integer limit,
      final Long offset
  ) {
    return new MapSqlParameterSource()
        .addValue("nomeEmpresa", nomeEmpresa)
        .addValue("limit", limit)
        .addValue("offset", offset);
  }

  public String getSelectCount() {
    SQLAppender sql = new SQLAppender(30);
    sql.appendSQL(" select ");
    sql.appendSQL("		count(1)  ");
    sql.appendSQL(" from empresa ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public String getSelectAll() {
    SQLAppender sql = new SQLAppender(30);
    sql.appendSQL(selectCommom());
    sql.appendSQL("  limit :limit offset :offset ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersAll(int pageSize, long offset) {
    return new MapSqlParameterSource()
        .addValue("limit", pageSize)
        .addValue("offset", offset);
  }

}
