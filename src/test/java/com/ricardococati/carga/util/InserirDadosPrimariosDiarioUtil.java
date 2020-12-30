package com.ricardococati.carga.util;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.ricardococati.carga.adapters.repositories.candlestick.impl.CandlestickDiarioInserirDAOImpl;
import com.ricardococati.carga.adapters.repositories.cotacao.impl.CotacaoInserirDAOImpl;
import com.ricardococati.carga.adapters.repositories.gerasequencia.impl.GeraSequenciaDAOImpl;
import com.ricardococati.carga.adapters.repositories.header.impl.HeaderInserirDAOImpl;
import com.ricardococati.carga.adapters.repositories.candlestick.sqlutil.CandlestickDiarioInserirSQLUtil;
import com.ricardococati.carga.adapters.repositories.cotacao.sqlutil.CotacaoSQLUtil;
import com.ricardococati.carga.adapters.repositories.header.sqlutil.HeaderSQLUtil;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.cotacao.dto.CotacaoDTO;
import com.ricardococati.carga.entities.domains.header.dto.HeaderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RequiredArgsConstructor
public class InserirDadosPrimariosDiarioUtil {

    private final CotacaoDTO cotacaoDTO;
    private final CotacaoSQLUtil cotacaoSQLUtil;
    private final HeaderDTO headerDTO;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CandlestickDiario candlestickDiarioDTO;
    private final CandlestickDiarioInserirSQLUtil candlestickSQLUtil;
    private final GeraSequenciaDAOImpl genericDAO;
    private final HeaderSQLUtil headerSQLUtil;

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
