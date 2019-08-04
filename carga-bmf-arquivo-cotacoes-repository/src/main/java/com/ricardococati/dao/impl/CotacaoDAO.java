package com.ricardococati.dao.impl;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.ICotacaoDAO;
import com.ricardococati.dao.IHeaderPGDAO;
import com.ricardococati.dto.CotacaoDTO;
import com.ricardococati.dto.HeaderDTO;
import com.ricardococati.util.SQLAppender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CotacaoDAO extends GenericDAO implements ICotacaoDAO {

  @Override
  public boolean incluirCotacao(CotacaoDTO cotacaoDTO) throws Exception {
    log.info("Executando método: incluirCotacao");
    int retorno = 0;
    final SQLAppender sql = new SQLAppender(100);
    try {
      jdbcTemplate = new JdbcTemplate(dataSource);

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
      sql.appendSQL("	values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

      retorno = jdbcTemplate.update(sql.getAppendSQLSemQuebra().toString(),
      new Object[] {
          cotacaoDTO.getIdentificacaoArquivo()
          ,cotacaoDTO.getTipoRegistro()
          ,cotacaoDTO.getDtpreg()
          ,cotacaoDTO.getCodbdi()
          ,cotacaoDTO.getCodneg()
          ,cotacaoDTO.getTpmerc()
          ,cotacaoDTO.getNomres()
          ,cotacaoDTO.getEspeci()
          ,cotacaoDTO.getPrazot()
          ,cotacaoDTO.getModref()
          ,cotacaoDTO.getPreabe()
          ,cotacaoDTO.getPremax()
          ,cotacaoDTO.getPremin()
          ,cotacaoDTO.getPremed()
          ,cotacaoDTO.getPreult()
          ,cotacaoDTO.getPreofc()
          ,cotacaoDTO.getPreofv()
          ,cotacaoDTO.getTotneg()
          ,cotacaoDTO.getQuatot()
          ,cotacaoDTO.getVoltot()
          ,cotacaoDTO.getPreexe()
          ,cotacaoDTO.getIndopc()
          ,cotacaoDTO.getDatven()
          ,cotacaoDTO.getFatcot()
          ,cotacaoDTO.getPtoexe()
          ,cotacaoDTO.getCodisi()
          ,cotacaoDTO.getDismes()
          });
    } catch (Exception ex) {
      log.error("Erro na execução do método incluirCotacao: " + ex.getMessage());
      throw ex;
    }

    return retorno > 0;
  }
}
