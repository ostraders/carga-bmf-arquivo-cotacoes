package com.ricardococati.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HeaderDTO extends BMFCargaDTO {

  private Integer tipoRegistro;
  private String nomeDoArquivo;
  private String codigoDaOrigem;
  private Integer dataDaGeracaoDoArquivo;

}
