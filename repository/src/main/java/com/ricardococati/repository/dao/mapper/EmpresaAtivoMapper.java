package com.ricardococati.repository.dao.mapper;

import com.ricardococati.model.dto.EmpresaAtivo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

@Service
public class EmpresaAtivoMapper {

  public EmpresaAtivo mapper(ResultSet rs) {
    try {
      return EmpresaAtivo
          .builder()
          .idEmpresa(rs.getLong("id_empresa"))
          .ativo(rs.getString("ativo"))
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
