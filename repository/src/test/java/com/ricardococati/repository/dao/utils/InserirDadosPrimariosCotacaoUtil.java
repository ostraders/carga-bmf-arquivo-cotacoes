package com.ricardococati.repository.dao.utils;

import com.ricardococati.model.dto.HeaderDTO;
import com.ricardococati.repository.dao.impl.HeaderInserirDAOImpl;
import com.ricardococati.repository.dao.sqlutil.HeaderSQLUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class InserirDadosPrimariosCotacaoUtil {

    private HeaderDTO headerDTO;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private HeaderSQLUtil headerSQLUtil;

    public InserirDadosPrimariosCotacaoUtil(
            final HeaderDTO headerDTO,
            final HeaderSQLUtil headerSQLUtil,
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.headerDTO = headerDTO;
        this.headerSQLUtil = headerSQLUtil;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void incluiHeaderAntesDeExecutarTestes() throws Exception {
        HeaderInserirDAOImpl incluirDAO = new HeaderInserirDAOImpl(
                namedParameterJdbcTemplate, headerSQLUtil);
        when(headerSQLUtil.getInsert()).thenCallRealMethod();
        when(headerSQLUtil.toParameters(any())).thenCallRealMethod();
        headerDTO.setIdentificacaoArquivo(1L);
        incluirDAO.incluirHeaderArquivo(headerDTO);
    }

}
