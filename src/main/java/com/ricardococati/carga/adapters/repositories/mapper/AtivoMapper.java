package com.ricardococati.carga.adapters.repositories.mapper;

import com.ricardococati.carga.entities.domains.Ativo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

@Service
public class AtivoMapper {

  public Ativo mapper(ResultSet rs) {
    try {
      return Ativo
          .builder()
          .idAtivo(rs.getLong("id_ativo"))
          .ativo(rs.getString("ativo"))
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
