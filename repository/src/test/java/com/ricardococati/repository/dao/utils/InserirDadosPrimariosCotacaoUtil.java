package com.ricardococati.repository.dao.utils;

import com.ricardococati.model.dto.HeaderDTO;
import com.ricardococati.repository.dao.impl.HeaderInserirDAOImpl;
import com.ricardococati.repository.dao.sqlutil.HeaderSQLUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

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
