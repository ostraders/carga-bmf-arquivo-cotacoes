package com.ricardococati.carga.adapters.repositories.sqlutil;

import com.ricardococati.carga.utils.geral.SQLAppender;
import com.ricardococati.carga.entities.domains.SplitInplit;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class CandlestickDiarioAtualizarSQLUtil {

  public String getUpdateSplitInplit(final String operacao) {
    final SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" update candlestick_diario set  ");
    sql.appendSQL("   preabe = preabe "+operacao+" :qtddivmult,  ");
    sql.appendSQL("   premax = premax "+operacao+" :qtddivmult,  ");
    sql.appendSQL("   premin = premin "+operacao+" :qtddivmult, ");
    sql.appendSQL("   preult = preult "+operacao+" :qtddivmult ");
    sql.appendSQL(" where dtpreg < :dtpreg ");
    sql.appendSQL(" and   codneg = :codneg ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersUpdateSplitInplit(final SplitInplit splitInplit) {
    return new MapSqlParameterSource()
        .addValue("dtpreg", splitInplit.getDtpreg())
        .addValue("codneg", splitInplit.getCodneg())
        .addValue("qtddivmult", splitInplit.getQtdSplitInplit());
  }

}