package com.ricardococati.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaixarArquivo {

  private String url;
  private String caminhoArquivoLocal;

}
