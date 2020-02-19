package com.ricardococati.repository.dao.sqlutil;

import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.repository.util.SQLAppender;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class CandlestickDiarioInserirSQLUtil {

  public String getInsert() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL("	insert into candlestick_diario ( ");
    sql.appendSQL("		id_candle_diario, ");
    sql.appendSQL("		codneg, ");
    sql.appendSQL("		dtpreg, ");
    sql.appendSQL("		preabe, ");
    sql.appendSQL("		premax, ");
    sql.appendSQL("		premin, ");
    sql.appendSQL("		preult, ");
    sql.appendSQL("		semana, ");
    sql.appendSQL("		semana_gerada, ");
    sql.appendSQL("		voltot) ");
    sql.appendSQL("	values( ");
    sql.appendSQL("		:idCandleDiario, ");
    sql.appendSQL("		:codneg, ");
    sql.appendSQL("		:dtpreg, ");
    sql.appendSQL("		:preabe, ");
    sql.appendSQL("		:premax, ");
    sql.appendSQL("		:premin, ");
    sql.appendSQL("		:preult, ");
    sql.appendSQL("		:semana, ");
    sql.appendSQL("		:semanaGerada, ");
    sql.appendSQL("		:voltot ");
    sql.appendSQL("   ) ");
    sql.appendSQL(" on conflict (codneg, dtpreg) do update set ");
    sql.appendSQL("		codneg = excluded.codneg, ");
    sql.appendSQL("		dtpreg = excluded.dtpreg, ");
    sql.appendSQL("		preabe = excluded.preabe, ");
    sql.appendSQL("		premax = excluded.premax, ");
    sql.appendSQL("		premin = excluded.premin, ");
    sql.appendSQL("		preult = excluded.preult, ");
    sql.appendSQL("		semana = excluded.semana, ");
    sql.appendSQL("		voltot = excluded.voltot ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParameters(final CandlestickDiario candlestickDiarioDTO) {
    return new MapSqlParameterSource()
        .addValue("idCandleDiario", candlestickDiarioDTO.getIdCandleDiario())
        .addValue("codneg", candlestickDiarioDTO.getCodneg())
        .addValue("dtpreg", candlestickDiarioDTO.getDtpreg())
        .addValue("preabe", candlestickDiarioDTO.getPreabe())
        .addValue("premax", candlestickDiarioDTO.getPremax())
        .addValue("premin", candlestickDiarioDTO.getPremin())
        .addValue("preult", candlestickDiarioDTO.getPreult())
        .addValue("semana", candlestickDiarioDTO.getIdSemanaAno())
        .addValue("semanaGerada", candlestickDiarioDTO.getSemanaGerada())
        .addValue("voltot", candlestickDiarioDTO.getVoltot());
  }

}
