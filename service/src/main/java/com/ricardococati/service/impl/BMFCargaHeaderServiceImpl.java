package com.ricardococati.service.impl;

import com.ricardococati.model.dto.Header;
import com.ricardococati.model.dto.HeaderDTO;
import com.ricardococati.repository.dao.HeaderDAO;
import com.ricardococati.service.BMFCargaHeaderService;
import com.ricardococati.service.config.ControleArquivoConfig;
import com.ricardococati.service.converter.HeaderConverter;
import com.ricardococati.service.util.ControlaIdArquivoUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class BMFCargaHeaderServiceImpl implements BMFCargaHeaderService {

  private final ControleArquivoConfig arquivoConfig;
  private final HeaderDAO headerDAO;
  private final HeaderConverter convertHed;
  private final ControlaIdArquivoUtil idArquivoUtil;

  @Override
  public void insereDados(Header header) {
    try {
      HeaderDTO headerDTO = convertHed.convert(header);
      headerDTO.setIdentificacaoArquivo(idArquivoUtil.getIdentificadorArquivo());
      headerDAO.incluirHeaderArquivo(headerDTO);
    } catch (Exception e) {
      arquivoConfig.setArquivoValido(false);
      log.error("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: {}" + e.getMessage());
    }
  }

}
