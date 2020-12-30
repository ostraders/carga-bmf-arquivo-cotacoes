package com.ricardococati.carga.adapters.repositories.candlestick.mapper;

import static java.util.Objects.nonNull;

import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class CandlestickDiarioMapper {

  public String mapperCodNeg(ResultSet rs) {
    try {
      return rs.getString("codneg");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public CandlestickDiario mapper(ResultSet rs) {
    try {
      return CandlestickDiario
          .builder()
          .idCandleDiario(rs.getLong("id_candle_diario"))
          .codneg(rs.getString("codneg"))
          .dtpreg(parseDateWithoutNull(rs , "dtpreg"))
          .preabe(rs.getBigDecimal("preabe"))
          .premax(rs.getBigDecimal("premax"))
          .premin(rs.getBigDecimal("premin"))
          .preult(rs.getBigDecimal("preult"))
          .idSemanaAno(rs.getInt("semana"))
          .semanaGerada(rs.getBoolean("semana_gerada"))
          .voltot(rs.getBigDecimal("voltot"))
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public LocalDate parseDateWithoutNull(ResultSet rs, String stringData)
      throws SQLException {
    LocalDate date = null;
    if (nonNull(rs.getDate(stringData))) {
      date = rs.getDate(stringData).toLocalDate();
    }
    return date;
  }

}
