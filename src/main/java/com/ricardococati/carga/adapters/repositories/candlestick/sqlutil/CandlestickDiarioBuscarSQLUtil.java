package com.ricardococati.carga.adapters.repositories.candlestick.sqlutil;

import com.ricardococati.carga.utils.SQLAppender;
import java.time.LocalDate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class CandlestickDiarioBuscarSQLUtil {

  public String getSelectCodNeg() {
    final SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" select ");
    sql.appendSQL("   codneg  ");
    sql.appendSQL(" from candlestick_diario ");
    sql.appendSQL(" group by codneg ");
    sql.appendSQL(" order by codneg asc ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersSelectByCodNeg(final String codneg) {
    return new MapSqlParameterSource()
        .addValue("codneg", codneg);
  }

  public String getSelectByCodNeg() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" select ");
    sql.appendSQL("		id_candle_diario, ");
    sql.appendSQL("		codneg, ");
    sql.appendSQL("		dtpreg, ");
    sql.appendSQL("		preabe, ");
    sql.appendSQL("		premax, ");
    sql.appendSQL("		premin, ");
    sql.appendSQL("		preult, ");
    sql.appendSQL("		semana, ");
    sql.appendSQL("		semana_gerada, ");
    sql.appendSQL("		voltot ");
    sql.appendSQL(" from candlestick_diario ");
    sql.appendSQL("	where codneg = :codneg ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public String getSelectCandleDiarioByDtPregCodneg() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" select ");
    sql.appendSQL("		id_candle_diario, ");
    sql.appendSQL("		codneg, ");
    sql.appendSQL("		dtpreg, ");
    sql.appendSQL("		preabe, ");
    sql.appendSQL("		premax, ");
    sql.appendSQL("		premin, ");
    sql.appendSQL("		preult, ");
    sql.appendSQL("		semana, ");
    sql.appendSQL("		semana_gerada, ");
    sql.appendSQL("		voltot ");
    sql.appendSQL(" from candlestick_diario ");
    sql.appendSQL("	where codneg = :codneg ");
    sql.appendSQL("	and dtpreg >= :dtpreg ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersCandleDiarioByDtPregCodneg(
      final LocalDate dtpreg,
      final String codneg
  ) {
    return new MapSqlParameterSource()
        .addValue("dtpreg", dtpreg)
        .addValue("codneg", codneg);
  }

}
