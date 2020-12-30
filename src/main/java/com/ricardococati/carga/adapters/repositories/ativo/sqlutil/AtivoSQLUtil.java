package com.ricardococati.carga.adapters.repositories.ativo.sqlutil;

import com.ricardococati.carga.utils.SQLAppender;
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

}
