package com.ricardococati.carga.adapters.repositories.calendario.sqlutil;

import com.ricardococati.carga.utils.SQLAppender;
import java.time.LocalDate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class CalendarioFeriadoSQLUtil {

  public String getSelectCountByDataAtual() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL("	select  ");
    sql.appendSQL("		count(1) ");
    sql.appendSQL("	from  ");
    sql.appendSQL("		calendario_feriado ");
    sql.appendSQL("	where data_calendario = :dataAtual ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersSelectByDataAtual(final LocalDate dataAtual) {
    return new MapSqlParameterSource()
        .addValue("dataAtual", dataAtual);
  }

}
