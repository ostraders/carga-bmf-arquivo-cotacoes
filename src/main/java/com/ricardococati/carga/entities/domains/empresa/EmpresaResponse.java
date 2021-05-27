package com.ricardococati.carga.entities.domains.empresa;

import com.ricardococati.carga.entities.enums.Segmento;
import com.ricardococati.carga.entities.enums.Setor;
import com.ricardococati.carga.entities.enums.SubSetor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpresaResponse {

  private Long idEmpresa;
  private String nomeEmpresa;
  private Setor setor;
  private SubSetor subSetor;
  private Segmento segmento;

}
