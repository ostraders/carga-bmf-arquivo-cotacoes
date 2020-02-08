package com.ricardococati.model.entities;

import java.util.List;
import lombok.Data;

@Data
public class Empresa {

  private Long idEmpresa;
  private String nomeEmpresa;
  private List<Setor> setores;
  private List<EmpresaAtivo> ativos;

}