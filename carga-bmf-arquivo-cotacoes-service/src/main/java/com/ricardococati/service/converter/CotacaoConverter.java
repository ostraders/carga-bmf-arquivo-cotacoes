package com.ricardococati.service.converter;

import com.ricardococati.dto.Header;
import com.ricardococati.dto.HeaderDTO;
import org.springframework.stereotype.Component;

@Component
public class CotacaoConverter {

  public HeaderDTO convert(Header header){
    return HeaderDTO
        .builder()
        .codigoDaOrigem(header.getCodigoDaOrigem())
        .dataDaGeracaoDoArquivo(header.getDataDaGeracaoDoArquivo())
        .nomeDoArquivo(header.getNomeDoArquivo())
        .tipoRegistro(header.getTipoRegistro())
        .build();
  }

}
