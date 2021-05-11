package com.ricardococati.carga.entities.domains.empresa;

import com.ricardococati.carga.entities.enums.Segmento;
import com.ricardococati.carga.entities.enums.Setor;
import com.ricardococati.carga.entities.enums.SubSetor;
import lombok.Data;

@Data
public class EmpresaDTO {

  private String nomeEmpresa;
  private Setor setor;
  private SubSetor subSetor;
  private Segmento segmento;
  private String ativo;

}
