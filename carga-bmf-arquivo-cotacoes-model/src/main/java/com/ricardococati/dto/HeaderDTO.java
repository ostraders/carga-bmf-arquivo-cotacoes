package com.ricardococati.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeaderDTO extends BMFCargaDTO {

  private Long tipoRegistro;
  private String nomeDoArquivo;
  private String codigoDaOrigem;
  private LocalDate dataDaGeracaoDoArquivo;

}
