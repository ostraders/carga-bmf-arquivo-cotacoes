package com.ricardococati.service.impl;

import static java.util.Objects.isNull;

import com.ricardococati.dao.IBMFCargaDAO;
import com.ricardococati.dao.ICandlestickDiarioDAO;
import com.ricardococati.dao.ICandlestickDiarioDAOCustom;
import com.ricardococati.dao.ICandlestickSemanalDAO;
import com.ricardococati.dao.IHeaderDAO;
import com.ricardococati.dto.BMFCargaDTO;
import com.ricardococati.dto.CandlestickDiario;
import com.ricardococati.dto.CandlestickSemanal;
import com.ricardococati.dto.Cotacao;
import com.ricardococati.dto.Empresa;
import com.ricardococati.dto.Header;
import com.ricardococati.service.IBMFCargaService;
import com.ricardococati.service.converter.ConverteCotacao;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
public class BMFCargaService implements IBMFCargaService {

  @Autowired
  private IBMFCargaDAO cargaDAO;

  @Autowired
  private IHeaderDAO headerDAO;

  @Autowired
  private ICandlestickDiarioDAO candlestickDiarioDAO;

  @Autowired
  private ICandlestickSemanalDAO candlestickSemanalDAO;

  @Autowired
  private ICandlestickDiarioDAOCustom diarioDAOCustom;

  @Autowired
  private IntegrationService integrationService;

  @Autowired
  private ConverteCotacao converteCotacao;

  @Override
  public void insereDados(List<? extends BMFCargaDTO> listCargaDTO) {
    try {
      if (!isNull(listCargaDTO) && !listCargaDTO.isEmpty()) {
        for (BMFCargaDTO bmfCargaDTO : listCargaDTO) {
          if (Header.class.isInstance(bmfCargaDTO)) {
            Header header = (Header) bmfCargaDTO;
            headerDAO.save(header);
          } else if (Cotacao.class.isInstance(bmfCargaDTO)) {
            Cotacao cotacao = (Cotacao) bmfCargaDTO;
            cargaDAO.save(cotacao);
            salvaCandlestickDiario(converteCotacao.converterCotacaoParaCandlestick(cotacao));
          }
        }
      }
    } catch (Exception e) {
      integrationService.setArquivoValido(false);
      log.info("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: " + e.getMessage());
    }
  }

  @Override
  public List<CandlestickDiario> listaCandlestickDiarioPorEmpresaSemanaGerada(String nomres,
      Boolean semanaGerada) {
    return candlestickDiarioDAO.findByNomresAndSemanaGerada(nomres, semanaGerada);
  }

  @Override
  public List<CandlestickDiario> listaCandlestickDiarioPorSemanaGerada(Boolean semanaGerada) {
    return candlestickDiarioDAO.findBySemanaGerada(semanaGerada);
  }

  @Override
  public List<Empresa> listEmpresas() {
    return diarioDAOCustom.findAllNomres();
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
