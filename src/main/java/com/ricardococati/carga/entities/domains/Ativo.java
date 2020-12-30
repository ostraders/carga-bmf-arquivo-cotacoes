package com.ricardococati.carga.entities.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ativo {

  private Long idAtivo;
  private String ativo;

}
