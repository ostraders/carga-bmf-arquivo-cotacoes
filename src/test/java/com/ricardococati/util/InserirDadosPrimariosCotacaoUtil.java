package com.ricardococati.util;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.ricardococati.carga.adapters.repositories.impl.HeaderInserirDAOImpl;
import com.ricardococati.carga.adapters.repositories.sqlutil.HeaderSQLUtil;
import com.ricardococati.carga.entities.dto.HeaderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RequiredArgsConstructor
public class InserirDadosPrimariosCotacaoUtil {

    private final HeaderDTO headerDTO;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final HeaderSQLUtil headerSQLUtil;

    public void incluiHeaderAntesDeExecutarTestes() throws Exception {
        HeaderInserirDAOImpl incluirDAO = new HeaderInserirDAOImpl(
                namedParameterJdbcTemplate, headerSQLUtil);
        when(headerSQLUtil.getInsert()).thenCallRealMethod();
        when(headerSQLUtil.toParameters(any())).thenCallRealMethod();
        headerDTO.setIdentificacaoArquivo(1L);
        incluirDAO.incluirHeaderArquivo(headerDTO);
    }

}
