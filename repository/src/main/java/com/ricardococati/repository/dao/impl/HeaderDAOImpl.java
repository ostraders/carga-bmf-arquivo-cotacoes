package com.ricardococati.repository.dao.impl;

import static java.util.Objects.isNull;

import com.ricardococati.model.dto.HeaderDTO;
import com.ricardococati.repository.dao.HeaderDAO;
import com.ricardococati.repository.dao.sqlutil.HeaderSQLUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class HeaderDAOImpl implements HeaderDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final HeaderSQLUtil sqlUtil;

  @Override
  public boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception {
    int retorno = 0;
    if (isNull(headerDTO)
        || isNull(headerDTO.getIdentificacaoArquivo())
        || isNull(headerDTO.getDataDaGeracaoDoArquivo())
        || isNull(headerDTO.getNomeDoArquivo())) {
      throw new DataIntegrityViolationException("Violação de chave na inserção de HEADER_ARQ");
    }
    try {
      retorno = template.update(
          sqlUtil.getInsert(),
          sqlUtil.toParameters(headerDTO)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método HEADER_ARQ: {} ", ex.getMessage());
      throw new Exception("Erro na execução do método HEADER_ARQ");
    }
    return retorno > 0;
  }

}
