package com.ricardococati.model.dto;

import java.time.LocalDate;

import com.ricardococati.model.entities.Arquivo;
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
