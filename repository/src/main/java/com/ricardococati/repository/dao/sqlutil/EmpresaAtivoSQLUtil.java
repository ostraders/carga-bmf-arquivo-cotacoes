package com.ricardococati.repository.dao.sqlutil;

import com.ricardococati.repository.util.SQLAppender;
import org.springframework.stereotype.Component;

@Component
public class EmpresaAtivoSQLUtil {

  public String getSelectAtivos() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" select  ");
    sql.appendSQL(" 	id_empresa, ");
    sql.appendSQL(" 	ativo ");
    sql.appendSQL(" from  ");
    sql.appendSQL(" 	empresa_ativo ");
    sql.appendSQL(" order by ativo asc ");
    return sql.getAppendSQLSemQuebra().toString();
  }

}
