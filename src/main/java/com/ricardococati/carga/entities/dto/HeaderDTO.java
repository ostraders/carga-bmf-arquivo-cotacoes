package com.ricardococati.carga.entities.dto;

import com.ricardococati.carga.entities.domains.Arquivo;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeaderDTO extends Arquivo {

  private Long tipoRegistro;
  private String nomeDoArquivo;
  private String codigoDaOrigem;
  private LocalDate dataDaGeracaoDoArquivo;

}
