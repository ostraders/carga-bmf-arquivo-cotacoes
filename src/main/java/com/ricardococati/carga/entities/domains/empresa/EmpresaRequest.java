package com.ricardococati.carga.entities.domains.empresa;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaRequest {

  private List<EmpresaDTO> empresas;

}
