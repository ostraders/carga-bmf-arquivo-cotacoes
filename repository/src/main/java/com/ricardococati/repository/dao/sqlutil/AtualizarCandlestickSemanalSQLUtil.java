package com.ricardococati.repository.dao.sqlutil;

import com.ricardococati.model.entities.SplitInplit;
import com.ricardococati.repository.util.SQLAppender;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class AtualizarCandlestickSemanalSQLUtil {

  public String getUpdateSplitInplit(final String operacao) {
    final SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" update candlestick_semanal set  ");
    sql.appendSQL("   preabe = preabe "+operacao+" :qtddivmult,  ");
    sql.appendSQL("   premax = premax "+operacao+" :qtddivmult,  ");
    sql.appendSQL("   premin = premin "+operacao+" :qtddivmult, ");
    sql.appendSQL("   preult = preult "+operacao+" :qtddivmult ");
    sql.appendSQL(" where dtpregini < :dtpreg ");
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
