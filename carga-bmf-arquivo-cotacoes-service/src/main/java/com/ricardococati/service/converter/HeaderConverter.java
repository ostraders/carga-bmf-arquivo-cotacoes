package com.ricardococati.service.converter;

import com.ricardococati.dto.Header;
import com.ricardococati.dto.HeaderDTO;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

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
