package com.ricardococati.carga.usecases.header.converter;

import com.ricardococati.carga.entities.domains.header.Header;
import com.ricardococati.carga.entities.domains.header.dto.HeaderDTO;
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
