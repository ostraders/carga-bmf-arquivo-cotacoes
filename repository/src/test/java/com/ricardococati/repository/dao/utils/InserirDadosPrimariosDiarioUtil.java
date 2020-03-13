package com.ricardococati.repository.dao.utils;

import com.ricardococati.model.dto.CotacaoDTO;
import com.ricardococati.model.dto.HeaderDTO;
import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.repository.dao.impl.CandlestickDiarioInserirDAOImpl;
import com.ricardococati.repository.dao.impl.CotacaoInserirDAOImpl;
import com.ricardococati.repository.dao.impl.GeraSequenciaDAOImpl;
import com.ricardococati.repository.dao.impl.HeaderInserirDAOImpl;
import com.ricardococati.repository.dao.sqlutil.CandlestickDiarioInserirSQLUtil;
import com.ricardococati.repository.dao.sqlutil.CotacaoSQLUtil;
import com.ricardococati.repository.dao.sqlutil.HeaderSQLUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class InserirDadosPrimariosDiarioUtil {

    private CotacaoDTO cotacaoDTO;
    private CotacaoSQLUtil cotacaoSQLUtil;
    private HeaderDTO headerDTO;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private CandlestickDiario candlestickDiarioDTO;
    private CandlestickDiarioInserirSQLUtil candlestickSQLUtil;
    private GeraSequenciaDAOImpl genericDAO;
    private HeaderSQLUtil headerSQLUtil;

    public InserirDadosPrimariosDiarioUtil(
            final CotacaoDTO cotacaoDTO,
            final CandlestickDiario candlestickDiario,
            final HeaderDTO headerDTO,
            final CotacaoSQLUtil cotacaoSQLUtil,
            final CandlestickDiarioInserirSQLUtil candlestickSQLUtil,
            final HeaderSQLUtil headerSQLUtil,
            final GeraSequenciaDAOImpl genericDAO,
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.cotacaoDTO = cotacaoDTO;
        this.candlestickDiarioDTO = candlestickDiario;
        this.headerDTO = headerDTO;
        this.cotacaoSQLUtil = cotacaoSQLUtil;
        this.candlestickSQLUtil = candlestickSQLUtil;
        this.headerSQLUtil = headerSQLUtil;
        this.genericDAO = genericDAO;
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

    public void incluiCotacaoAntesDeExecutarTestes() throws Exception {
        CotacaoInserirDAOImpl incluirDAO = new CotacaoInserirDAOImpl(
                namedParameterJdbcTemplate, cotacaoSQLUtil);
        when(cotacaoSQLUtil.getInsert()).thenCallRealMethod();
        when(cotacaoSQLUtil.toParameters(any())).thenCallRealMethod();
        incluirDAO.incluirCotacao(cotacaoDTO);
    }

    public void incluiCandleAntesDeExecutarTestes() throws Exception {
        CandlestickDiarioInserirDAOImpl incluirDAO =
                new CandlestickDiarioInserirDAOImpl(
                namedParameterJdbcTemplate, genericDAO, candlestickSQLUtil);
        when(candlestickSQLUtil.getInsert()).thenCallRealMethod();
        when(candlestickSQLUtil.toParameters(any())).thenCallRealMethod();
        when(genericDAO.getSequence(any())).thenReturn(1);
        incluirDAO.incluirCandlestickDiario(candlestickDiarioDTO);
    }

}
