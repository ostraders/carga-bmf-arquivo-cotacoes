package com.ricardococati.carga.adapters.repositories.empresa.mapper;

import com.ricardococati.carga.entities.domains.empresa.Empresa;
import com.ricardococati.carga.entities.enums.Segmento;
import com.ricardococati.carga.entities.enums.Setor;
import com.ricardococati.carga.entities.enums.SubSetor;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

@Service
public class EmpresaMapper {

  public Empresa mapper(ResultSet rs) throws RuntimeException{
    try {
      return Empresa
          .builder()
          .idEmpresa(rs.getLong("id_empresa"))
          .nomeEmpresa(rs.getString("nome_empresa"))
          .segmento(Segmento.findValueById(rs.getInt("id_segmento")))
          .setor(Setor.findValueById(rs.getInt("id_setor")))
          .subSetor(SubSetor.findValueById(rs.getInt("id_sub_setor")))
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
