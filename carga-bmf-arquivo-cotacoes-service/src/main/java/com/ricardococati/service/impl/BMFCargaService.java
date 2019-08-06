package com.ricardococati.service.impl;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.ICandlestickDiarioPGDAO;
import com.ricardococati.dao.IHeaderPGDAO;
import com.ricardococati.dto.*;
import com.ricardococati.dao.ICotacaoDAO;
import com.ricardococati.service.IBMFCargaService;
import com.ricardococati.service.converter.CandlestickConverter;
import com.ricardococati.service.converter.CandlestickDiarioConverter;
import com.ricardococati.service.converter.CotacaoConverter;
import com.ricardococati.service.converter.HeaderConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@Data
@Service
public class BMFCargaService implements IBMFCargaService {

  private static final String LOTE_PADRAO = "02";

  @Autowired
  private ICandlestickDiarioPGDAO candlestickDiarioPGDAO;

  @Autowired
  private IntegrationService integrationService;

  @Autowired
  private CandlestickConverter convertCandle;

  @Autowired
  private CandlestickDiarioConverter convertCandleDiario;

  @Autowired
  private IHeaderPGDAO headerPGDAO;

  @Autowired
  private ICotacaoDAO cotacaoDAO;

  @Autowired
  private HeaderConverter convertHed;

  @Autowired
  private CotacaoConverter convertCot;

  @Autowired
  private GenericDAO genericDAO;

  private Long identificadorArquivo;

  @Override
  public void insereDados(List<? extends BMFCargaDTO> listCargaDTO) {
    try {
      if (nonNull(listCargaDTO) && !listCargaDTO.isEmpty()) {
        setIdentificadorArquivo(genericDAO.obterSequenceLong("ARQUIVO_SEQ"));
        for (BMFCargaDTO bmfCargaDTO : listCargaDTO) {
          if (Header.class.isInstance(bmfCargaDTO)) {
            HeaderDTO headerDTO = convertHed.convert((Header) bmfCargaDTO);
            headerDTO.setIdentificacaoArquivo(getIdentificadorArquivo());
            headerPGDAO.incluirHeaderArquivo(headerDTO);
          } else if (Cotacao.class.isInstance(bmfCargaDTO)) {
            Cotacao cotacao = (Cotacao) bmfCargaDTO;
            CotacaoDTO cotacaoDTO = convertCot.convert(cotacao);
            if(LOTE_PADRAO.equals(cotacaoDTO.getCodbdi())) {
              cotacaoDTO.setIdentificacaoArquivo(getIdentificadorArquivo());
              cotacaoDAO.incluirCotacao(cotacaoDTO);
              CandlestickDiarioDTO candlestickDiarioDTO = convertCandleDiario.convert(cotacao);
              if (nonNull(candlestickDiarioDTO)) {
                candlestickDiarioPGDAO.incluirCandlestickDiario(candlestickDiarioDTO);
              }
            }
          }
        }
      }
    } catch (Exception e) {
      integrationService.setArquivoValido(false);
      log.info("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: " + e.getMessage());
    }
  }

}
