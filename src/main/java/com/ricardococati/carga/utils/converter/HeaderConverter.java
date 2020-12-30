package com.ricardococati.carga.utils.converter;

import com.ricardococati.carga.entities.domains.Header;
import com.ricardococati.carga.entities.dto.HeaderDTO;
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
