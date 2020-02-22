package com.ricardococati.service.impl;

import com.ricardococati.model.dto.Header;
import com.ricardococati.model.dto.HeaderDTO;
import com.ricardococati.repository.dao.HeaderInserirDAO;
import com.ricardococati.service.BMFCargaHeaderService;
import com.ricardococati.service.config.ControleArquivoConfig;
import com.ricardococati.service.converter.HeaderConverter;
import com.ricardococati.service.util.ControlaIdArquivoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BMFCargaHeaderServiceImpl implements BMFCargaHeaderService {

  private final ControleArquivoConfig arquivoConfig;
  private final HeaderInserirDAO headerInserirDAO;
  private final HeaderConverter convertHed;
  private final ControlaIdArquivoUtil idArquivoUtil;

  @Override
  public Boolean insereDados(final Header header) throws Exception {
    Boolean retorno;
    try {
      HeaderDTO headerDTO = convertHed.convert(header);
      headerDTO.setIdentificacaoArquivo(idArquivoUtil.getIdentificadorArquivo());
      retorno = headerInserirDAO.incluirHeaderArquivo(headerDTO);
    } catch (Exception e) {
      arquivoConfig.setArquivoValido(false);
      log.error("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: {}" + e.getMessage());
      throw new Exception("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE");
    }
    return retorno;
  }

}
