package com.ricardococati.dao.impl;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.ICandlestickDiarioPGDAO;
import com.ricardococati.dto.CandlestickDiarioDTO;
import com.ricardococati.util.SQLAppender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CandlestickDiarioPGDAO extends GenericDAO implements ICandlestickDiarioPGDAO {

  @Override
  public boolean incluirCandlestickDiario(final CandlestickDiarioDTO candlestickDiarioDTO) throws Exception {
    int retorno = 0;
    final SQLAppender sql = new SQLAppender(100);

    candlestickDiarioDTO.setIdCandleDiario(
        getSequence("CANDLESTICK_SEQ", jdbcTemplate).longValue()
    );

    try {
      jdbcTemplate = new JdbcTemplate(dataSource);

      sql.appendSQL("	insert into candlestick_diario ( ");
      sql.appendSQL("		id_candle_diario, ");
      sql.appendSQL("		codneg, ");
      sql.appendSQL("		dtpreg, ");
      sql.appendSQL("		media_movel_gerada, ");
      sql.appendSQL("		preabe, ");
      sql.appendSQL("		premax, ");
      sql.appendSQL("		premin, ");
      sql.appendSQL("		preult, ");
      sql.appendSQL("		semana, ");
      sql.appendSQL("		semana_gerada, ");
      sql.appendSQL("		voltot) ");
      sql.appendSQL("	values(?,?,?,?,?,?,?,?,?,?,?) ");

      retorno = jdbcTemplate.update(sql.getAppendSQLSemQuebra().toString(),
      new Object[] {
          candlestickDiarioDTO.getIdCandleDiario()
          ,candlestickDiarioDTO.getCodneg()
          ,candlestickDiarioDTO.getDtpreg()
          ,candlestickDiarioDTO.getMediaMovelGerada()
          ,candlestickDiarioDTO.getPreabe()
          ,candlestickDiarioDTO.getPremax()
          ,candlestickDiarioDTO.getPremin()
          ,candlestickDiarioDTO.getPreult()
          ,candlestickDiarioDTO.getSemana()
          ,candlestickDiarioDTO.getSemanaGerada()
          ,candlestickDiarioDTO.getVoltot()
          });
    } catch (Exception ex) {
      log.error("Erro na execução do método CANDLESTICK_DIARIO: " + ex.getMessage());
      throw ex;
    }

    return retorno > 0;
  }
}
