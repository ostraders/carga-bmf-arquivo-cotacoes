package com.ricardococati.carga.entities.domains.empresaativo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpresaAtivo {

  private Long idEmpresa;
  private Long idAtivo;

}
