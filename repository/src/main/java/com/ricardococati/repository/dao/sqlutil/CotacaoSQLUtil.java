package com.ricardococati.repository.dao.sqlutil;

import com.ricardococati.model.dto.CotacaoDTO;
import com.ricardococati.model.entities.SplitInplit;
import com.ricardococati.repository.util.SQLAppender;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class CotacaoSQLUtil {

  public String getInsert() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL("	insert into cotacao ( ");
    sql.appendSQL("		identificacao_reg, ");
    sql.appendSQL("		tporeg, ");
    sql.appendSQL("		dtpreg, ");
    sql.appendSQL("		codbdi, ");
    sql.appendSQL("		codneg, ");
    sql.appendSQL("		tpmerc, ");
    sql.appendSQL("		nomres, ");
    sql.appendSQL("		especi, ");
    sql.appendSQL("		prazot, ");
    sql.appendSQL("		modref, ");
    sql.appendSQL("		preabe, ");
    sql.appendSQL("		premax, ");
    sql.appendSQL("		premin, ");
    sql.appendSQL("		premed, ");
    sql.appendSQL("		preult, ");
    sql.appendSQL("		preofc, ");
    sql.appendSQL("		preofv, ");
    sql.appendSQL("		totneg, ");
    sql.appendSQL("		quatot, ");
    sql.appendSQL("		voltot, ");
    sql.appendSQL("		preexe, ");
    sql.appendSQL("		indopc, ");
    sql.appendSQL("		datven, ");
    sql.appendSQL("		fatcot, ");
    sql.appendSQL("		ptoexe, ");
    sql.appendSQL("		codisi, ");
    sql.appendSQL("		dismes) ");
    sql.appendSQL("	values( ");
    sql.appendSQL("		:identificacaoArquivo, ");
    sql.appendSQL("		:tipoRegistro, ");
    sql.appendSQL("		:dtpreg, ");
    sql.appendSQL("		:codbdi, ");
    sql.appendSQL("		:codneg, ");
    sql.appendSQL("		:tpmerc, ");
    sql.appendSQL("		:nomres, ");
    sql.appendSQL("		:especi, ");
    sql.appendSQL("		:prazot, ");
    sql.appendSQL("		:modref, ");
    sql.appendSQL("		:preabe, ");
    sql.appendSQL("		:premax, ");
    sql.appendSQL("		:premin, ");
    sql.appendSQL("		:premed, ");
    sql.appendSQL("		:preult, ");
    sql.appendSQL("		:preofc, ");
    sql.appendSQL("		:preofv, ");
    sql.appendSQL("		:totneg, ");
    sql.appendSQL("		:quatot, ");
    sql.appendSQL("		:voltot, ");
    sql.appendSQL("		:preexe, ");
    sql.appendSQL("		:indopc, ");
    sql.appendSQL("		:datven, ");
    sql.appendSQL("		:fatcot, ");
    sql.appendSQL("		:ptoexe, ");
    sql.appendSQL("		:codisi, ");
    sql.appendSQL("		:dismes ");
    sql.appendSQL("   ) ");
    sql.appendSQL(" on conflict (codneg, dtpreg) do update set ");
    sql.appendSQL("     tporeg = excluded.tporeg, ");
    sql.appendSQL("     dtpreg = excluded.dtpreg, ");
    sql.appendSQL("     codbdi = excluded.codbdi, ");
    sql.appendSQL("     codneg = excluded.codneg, ");
    sql.appendSQL("     tpmerc = excluded.tpmerc, ");
    sql.appendSQL("     nomres = excluded.nomres, ");
    sql.appendSQL("     especi = excluded.especi, ");
    sql.appendSQL("     prazot = excluded.prazot, ");
    sql.appendSQL("     modref = excluded.modref, ");
    sql.appendSQL("     preabe = excluded.preabe, ");
    sql.appendSQL("     premax = excluded.premax, ");
    sql.appendSQL("     premin = excluded.premin, ");
    sql.appendSQL("     premed = excluded.premed, ");
    sql.appendSQL("     preult = excluded.preult, ");
    sql.appendSQL("     preofc = excluded.preofc, ");
    sql.appendSQL("     preofv = excluded.preofv, ");
    sql.appendSQL("     totneg = excluded.totneg, ");
    sql.appendSQL("     quatot = excluded.quatot, ");
    sql.appendSQL("     voltot = excluded.voltot, ");
    sql.appendSQL("     preexe = excluded.preexe, ");
    sql.appendSQL("     indopc = excluded.indopc, ");
    sql.appendSQL("     datven = excluded.datven, ");
    sql.appendSQL("     fatcot = excluded.fatcot, ");
    sql.appendSQL("     ptoexe = excluded.ptoexe, ");
    sql.appendSQL("     codisi = excluded.codisi, ");
    sql.appendSQL("     dismes = excluded.dismes  ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersInsert(final CotacaoDTO cotacaoDTO) {
    return new MapSqlParameterSource()
        .addValue("identificacaoArquivo", cotacaoDTO.getIdentificacaoArquivo())
        .addValue("tipoRegistro", cotacaoDTO.getTipoRegistro())
        .addValue("dtpreg", cotacaoDTO.getDtpreg())
        .addValue("codbdi", cotacaoDTO.getCodbdi())
        .addValue("codneg", cotacaoDTO.getCodneg())
        .addValue("tpmerc", cotacaoDTO.getTpmerc())
        .addValue("nomres", cotacaoDTO.getNomres())
        .addValue("especi", cotacaoDTO.getEspeci())
        .addValue("prazot", cotacaoDTO.getPrazot())
        .addValue("modref", cotacaoDTO.getModref())
        .addValue("preabe", cotacaoDTO.getPreabe())
        .addValue("premax", cotacaoDTO.getPremax())
        .addValue("premin", cotacaoDTO.getPremin())
        .addValue("premed", cotacaoDTO.getPremed())
        .addValue("preult", cotacaoDTO.getPreult())
        .addValue("preofc", cotacaoDTO.getPreofc())
        .addValue("preofv", cotacaoDTO.getPreofv())
        .addValue("totneg", cotacaoDTO.getTotneg())
        .addValue("quatot", cotacaoDTO.getQuatot())
        .addValue("voltot", cotacaoDTO.getVoltot())
        .addValue("preexe", cotacaoDTO.getPreexe())
        .addValue("indopc", cotacaoDTO.getIndopc())
        .addValue("datven", cotacaoDTO.getDatven())
        .addValue("fatcot", cotacaoDTO.getFatcot())
        .addValue("ptoexe", cotacaoDTO.getPtoexe())
        .addValue("codisi", cotacaoDTO.getCodisi())
        .addValue("dismes", cotacaoDTO.getDismes());
  }

  public String getUpdate(final String operacao) {
    final SQLAppender sql = new SQLAppender(100);
    sql.appendSQL(" update cotacao set  ");
    sql.appendSQL("   preabe = preabe "+operacao+" :qtddivmult,  ");
    sql.appendSQL("   premax = premax "+operacao+" :qtddivmult,  ");
    sql.appendSQL("   premin = premin "+operacao+" :qtddivmult, ");
    sql.appendSQL("   premed = premed "+operacao+" :qtddivmult, ");
    sql.appendSQL("   preult = preult "+operacao+" :qtddivmult, ");
    sql.appendSQL("   preofc = preofc "+operacao+" :qtddivmult, ");
    sql.appendSQL("   preofv = preofv "+operacao+" :qtddivmult ");
    sql.appendSQL(" where dtpreg < :dtpreg ");
    sql.appendSQL(" and   codneg = :codneg ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParametersUpdate(final SplitInplit splitInplit) {
    return new MapSqlParameterSource()
        .addValue("dtpreg", splitInplit.getDtpreg())
        .addValue("codneg", splitInplit.getCodneg())
        .addValue("qtddivmult", splitInplit.getQtdSplitInplit());
  }

}
