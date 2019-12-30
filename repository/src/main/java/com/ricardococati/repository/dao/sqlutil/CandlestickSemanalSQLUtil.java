package com.ricardococati.repository.dao.sqlutil;

import com.ricardococati.model.dto.CandlestickSemanal;
import com.ricardococati.model.dto.SplitInplit;
import com.ricardococati.repository.util.SQLAppender;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class CandlestickSemanalSQLUtil {

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
    sql.appendSQL(" on conflict (codneg, dtpregini, dtpregfim) do update set ");
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

  public MapSqlParameterSource toParameters(final CandlestickSemanal candlestickDiarioDTO) {
    return new MapSqlParameterSource()
        .addValue("idCandleSemanal", candlestickDiarioDTO.getIdCandleSemanal())
        .addValue("codneg", candlestickDiarioDTO.getCodneg())
        .addValue("dtpregini", candlestickDiarioDTO.getDtpregini())
        .addValue("dtpregfim", candlestickDiarioDTO.getDtpregfim())
        .addValue("preabe", candlestickDiarioDTO.getPreabe())
        .addValue("premax", candlestickDiarioDTO.getPremax())
        .addValue("premin", candlestickDiarioDTO.getPremin())
        .addValue("preult", candlestickDiarioDTO.getPreult())
        .addValue("semana", candlestickDiarioDTO.getSemana())
        .addValue("voltot", candlestickDiarioDTO.getVoltot());
  }

  public String getUpdate(final String operacao) {
    final SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" update candlestick_semanal set  ");
    sql.appendSQL("   preabe = preabe "+operacao+" :qtddivmult,  ");
    sql.appendSQL("   premax = premax "+operacao+" :qtddivmult,  ");
    sql.appendSQL("   premin = premin "+operacao+" :qtddivmult, ");
    sql.appendSQL("   preult = preult "+operacao+" :qtddivmult ");
    sql.appendSQL(" where dtpregini < :dtpregini ");
    sql.appendSQL(" and   codneg = :codneg ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersUpdate(final SplitInplit splitInplit) {
    return new MapSqlParameterSource()
        .addValue("dtpregini", splitInplit.getDtpreg())
        .addValue("codneg", splitInplit.getCodneg())
        .addValue("qtddivmult", splitInplit.getQtdSplitInplit());
  }

  public String getSelectCount() {
    final SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" select  ");
    sql.appendSQL("   count(1)  ");
    sql.appendSQL(" from candlestick_diario ");
    sql.appendSQL(" where semana_gerada = false ");
    return sql.getAppendSQLSemQuebra().toString();
  }

}
