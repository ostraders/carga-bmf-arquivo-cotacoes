package com.ricardococati.service.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.IBMFCargaDAO;
import com.ricardococati.dao.ICandlestickDiarioDAO;
import com.ricardococati.dao.ICandlestickDiarioDAOCustom;
import com.ricardococati.dao.ICandlestickDiarioPGDAO;
import com.ricardococati.dao.ICandlestickSemanalDAO;
import com.ricardococati.dao.ICotacaoDAO;
import com.ricardococati.dao.IHeaderDAO;
import com.ricardococati.dao.IHeaderPGDAO;
import com.ricardococati.dto.BMFCargaDTO;
import com.ricardococati.dto.CandlestickDiario;
import com.ricardococati.dto.CandlestickDiarioDTO;
import com.ricardococati.dto.CandlestickSemanal;
import com.ricardococati.dto.Cotacao;
import com.ricardococati.dto.CotacaoDTO;
import com.ricardococati.dto.Empresa;
import com.ricardococati.dto.Header;
import com.ricardococati.dto.HeaderDTO;
import com.ricardococati.service.IBMFCargaService;
import com.ricardococati.service.converter.CandlestickConverter;
import com.ricardococati.service.converter.CandlestickDiarioConverter;
import com.ricardococati.service.converter.CotacaoConverter;
import com.ricardococati.service.converter.HeaderConverter;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
public class BMFCargaService implements IBMFCargaService {

  private static final String LOTE_PADRAO = "02";

  @Autowired
  private IBMFCargaDAO cargaDAO;

  @Autowired
  private IHeaderDAO headerDAO;

  @Autowired
  private ICandlestickDiarioDAO candlestickDiarioDAO;

  @Autowired
  private ICandlestickDiarioPGDAO candlestickDiarioPGDAO;

  @Autowired
  private ICandlestickSemanalDAO candlestickSemanalDAO;

  @Autowired
  private ICandlestickDiarioDAOCustom diarioDAOCustom;

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
            Header header = (Header) bmfCargaDTO;
            HeaderDTO headerDTO = convertHed.convert(header);
            headerDTO.setIdentificacaoArquivo(getIdentificadorArquivo());
            headerPGDAO.incluirHeaderArquivo(headerDTO);
          } else if (Cotacao.class.isInstance(bmfCargaDTO)) {
            Cotacao cotacao = (Cotacao) bmfCargaDTO;
            CotacaoDTO cotacaoDTO = convertCot.convert(cotacao);
            if(LOTE_PADRAO.equals(cotacaoDTO.getCodbdi())) {
              cotacaoDTO.setIdentificacaoArquivo(getIdentificadorArquivo());
              cotacaoDAO.incluirCotacao(cotacaoDTO);
              CandlestickDiario candlestickDiario = convertCandle.convert(cotacao);
              CandlestickDiarioDTO candlestickDiarioDTO = convertCandleDiario.convert(cotacao);
              if (nonNull(candlestickDiario)) {
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

  @Override
  public List<CandlestickDiario> listaCandlestickDiarioPorEmpresaSemanaGerada(String codneg,
      Boolean semanaGerada) {
    return candlestickDiarioDAO.findByCodnegAndSemanaGerada(codneg, semanaGerada);
  }

  @Override
  public List<CandlestickDiario> listaCandlestickDiarioPorSemanaGerada(Boolean semanaGerada) {
    return candlestickDiarioDAO.findBySemanaGerada(semanaGerada);
  }

  @Override
  public List<Empresa> listCodNegocio() {
    return diarioDAOCustom.findAllCodneg();
  }

  @Override
  public void salvaCandlestickSemanal(CandlestickSemanal candlestickSemanal) {
    candlestickSemanalDAO.save(candlestickSemanal);
  }

  @Override
  public void salvaCandlestickDiario(CandlestickDiario candlestickDiario) {
    candlestickDiarioDAO.save(candlestickDiario);
  }

}
