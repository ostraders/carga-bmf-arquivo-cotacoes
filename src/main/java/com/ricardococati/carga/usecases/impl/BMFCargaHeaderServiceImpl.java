package com.ricardococati.carga.usecases.impl;

import com.ricardococati.carga.adapters.repositories.HeaderInserirDAO;
import com.ricardococati.carga.config.ControleArquivoConfig;
import com.ricardococati.carga.entities.domains.Header;
import com.ricardococati.carga.entities.dto.HeaderDTO;
import com.ricardococati.carga.usecases.BMFCargaHeaderService;
import com.ricardococati.carga.utils.converter.HeaderConverter;
import com.ricardococati.carga.utils.geral.ControlaIdArquivoUtil;
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
    Boolean retorno = Boolean.FALSE;
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
