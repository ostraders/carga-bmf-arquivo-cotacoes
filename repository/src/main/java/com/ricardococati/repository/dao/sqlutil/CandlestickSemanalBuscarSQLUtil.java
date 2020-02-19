package com.ricardococati.repository.dao.sqlutil;

import com.ricardococati.repository.util.SQLAppender;
import java.time.LocalDate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class CandlestickSemanalBuscarSQLUtil {

  public String getSelectCandleSemanalByDtPregIni() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" select ");
    sql.appendSQL("		id_candle_semanal, ");
    sql.appendSQL("		codneg, ");
    sql.appendSQL("		dtpregini, ");
    sql.appendSQL("		dtpregfim, ");
    sql.appendSQL("		preabe, ");
    sql.appendSQL("		premax, ");
    sql.appendSQL("		premin, ");
    sql.appendSQL("		preult, ");
    sql.appendSQL("		semana, ");
    sql.appendSQL("		voltot ");
    sql.appendSQL(" from candlestick_semanal ");
    sql.appendSQL("	where dtpregini = :dtpregini ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersCandleSemanalByDtPregIni(final LocalDate dtpregini) {
    return new MapSqlParameterSource()
        .addValue("dtpregini", dtpregini);
  }

}
