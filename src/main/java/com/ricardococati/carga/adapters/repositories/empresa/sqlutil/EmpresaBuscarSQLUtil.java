package com.ricardococati.carga.adapters.repositories.empresa.sqlutil;

import com.ricardococati.carga.utils.SQLAppender;
import java.time.LocalDate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class EmpresaBuscarSQLUtil {

  public String getSelect() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" select ");
    sql.appendSQL("		id_empresa, ");
    sql.appendSQL("		nome_empresa, ");
    sql.appendSQL("		id_setor, ");
    sql.appendSQL("		id_sub_setor, ");
    sql.appendSQL("		id_segmento ");
    sql.appendSQL(" from empresa ");
    sql.appendSQL("	where nome_empresa = :nomeEmpresa ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParameters(final String nomeEmpresa) {
    return new MapSqlParameterSource()
        .addValue("nomeEmpresa", nomeEmpresa);
  }

}
