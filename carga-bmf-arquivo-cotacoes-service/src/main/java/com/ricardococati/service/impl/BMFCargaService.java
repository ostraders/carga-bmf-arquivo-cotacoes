package com.ricardococati.service.impl;

import static java.util.Objects.isNull;

import com.ricardococati.dao.IBMFCargaDAO;
import com.ricardococati.dao.IHeaderDAO;
import com.ricardococati.dto.BMFCargaDTO;
import com.ricardococati.dto.Cotacao;
import com.ricardococati.dto.Header;
import com.ricardococati.service.IBMFCargaService;
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
  private IntegrationService integrationService;

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
          }
        }
      }
    } catch (Exception e) {
      integrationService.setArquivoValido(false);
      log.info("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: " + e.getMessage());
    }
  }

}
