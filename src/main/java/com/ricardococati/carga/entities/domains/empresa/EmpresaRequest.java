package com.ricardococati.carga.entities.domains.empresa;

import java.util.List;
import lombok.Data;

@Data
public class EmpresaRequest {

  private List<EmpresaDTO> empresas;

}
