package com.ricardococati.service.converter;

import com.ricardococati.model.dto.Header;
import com.ricardococati.model.dto.HeaderDTO;
import org.springframework.stereotype.Component;

@Component
public class HeaderConverter {

    public HeaderDTO convert(final Header header) {
        return HeaderDTO
                .builder()
                .codigoDaOrigem(header.getCodigoDaOrigem())
                .dataDaGeracaoDoArquivo(header.getDataDaGeracaoDoArquivo())
                .nomeDoArquivo(header.getNomeDoArquivo())
                .tipoRegistro(header.getTipoRegistro())
                .build();
    }

}
