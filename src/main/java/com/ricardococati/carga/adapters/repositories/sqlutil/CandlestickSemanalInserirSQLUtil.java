package com.ricardococati.carga.adapters.repositories.sqlutil;

import com.ricardococati.carga.utils.geral.SQLAppender;
import com.ricardococati.carga.entities.domains.CandlestickSemanal;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class CandlestickSemanalInserirSQLUtil {

  public String getInsert() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL("	insert into candlestick_semanal ( ");
    sql.appendSQL("		id_candle_semanal, ");
    sql.appendSQL("		codneg, ");
    sql.appendSQL("		dtpregini, ");
    sql.appendSQL("		dtpregfim, ");
    sql.appendSQL("		preabe, ");
    sql.appendSQL("		premax, ");
    sql.appendSQL("		premin, ");
    sql.appendSQL("		preult, ");
    sql.appendSQL("		semana, ");
    sql.appendSQL("		voltot) ");
    sql.appendSQL("	values( ");
    sql.appendSQL("		:idCandleSemanal, ");
    sql.appendSQL("		:codneg, ");
    sql.appendSQL("		:dtpregini, ");
    sql.appendSQL("		:dtpregfim, ");
    sql.appendSQL("		:preabe, ");
    sql.appendSQL("		:premax, ");
    sql.appendSQL("		:premin, ");
    sql.appendSQL("		:preult, ");
    sql.appendSQL("		:semana, ");
    sql.appendSQL("		:voltot ");
    sql.appendSQL("   ) ");
    sql.appendSQL(" on conflict (codneg, dtpregini) do update set ");
    sql.appendSQL("		codneg = excluded.codneg, ");
    sql.appendSQL("		dtpregini = excluded.dtpregini, ");
    sql.appendSQL("		dtpregfim = excluded.dtpregfim, ");
    sql.appendSQL("		preabe = excluded.preabe, ");
    sql.appendSQL("		premax = excluded.premax, ");
    sql.appendSQL("		premin = excluded.premin, ");
    sql.appendSQL("		preult = excluded.preult, ");
    sql.appendSQL("		semana = excluded.semana, ");
    sql.appendSQL("		voltot = excluded.voltot ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParameters(final CandlestickSemanal semanal) {
    return new MapSqlParameterSource()
        .addValue("idCandleSemanal", semanal.getIdCandleSemanal())
        .addValue("codneg", semanal.getCodneg())
        .addValue("dtpregini", semanal.getDtpregini())
        .addValue("dtpregfim", semanal.getDtpregfim())
        .addValue("preabe", semanal.getPreabe())
        .addValue("premax", semanal.getPremax())
        .addValue("premin", semanal.getPremin())
        .addValue("preult", semanal.getPreult())
        .addValue("semana", semanal.getSemana())
        .addValue("voltot", semanal.getVoltot());
  }

}
