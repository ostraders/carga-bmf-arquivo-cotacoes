package com.ricardococati.repository.dao.mapper;

import static java.util.Objects.nonNull;

import com.ricardococati.model.dto.CandlestickSemanal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CandlestickSemanalMapper {

  public CandlestickSemanal mapper(ResultSet rs) {
    try {
      return CandlestickSemanal
          .builder()
          .idCandleSemanal(rs.getLong("id_candle_semanal"))
          .codneg(rs.getString("codneg"))
          .dtpregini(parseDateWithoutNull(rs , "dtpregini"))
          .dtpregfim(parseDateWithoutNull(rs , "dtpregfim"))
          .preabe(rs.getBigDecimal("preabe"))
          .premax(rs.getBigDecimal("premax"))
          .premin(rs.getBigDecimal("premin"))
          .preult(rs.getBigDecimal("preult"))
          .semana(rs.getInt("semana"))
          .voltot(rs.getBigDecimal("voltot"))
          .build();
    } catch (SQLException e) {
      log.error("Erro ao mapear objeto CandlestickSemanal {} ", e.getMessage());
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
