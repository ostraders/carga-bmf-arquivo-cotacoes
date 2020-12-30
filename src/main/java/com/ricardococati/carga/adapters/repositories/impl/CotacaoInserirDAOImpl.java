package com.ricardococati.carga.adapters.repositories.impl;

import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.CotacaoInserirDAO;
import com.ricardococati.carga.adapters.repositories.sqlutil.CotacaoSQLUtil;
import com.ricardococati.carga.entities.dto.CotacaoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CotacaoInserirDAOImpl implements CotacaoInserirDAO {

  @Qualifier("namedParameterJdbcTemplate")
  private final NamedParameterJdbcTemplate template;

  private final CotacaoSQLUtil sqlUtil;

  @Override
  public Boolean incluirCotacao(final CotacaoDTO cotacaoDTO) throws Exception {
    int retorno = 0;
    if (isNull(cotacaoDTO)
        || isNull(cotacaoDTO.getIdentificacaoArquivo())
        || isNull(cotacaoDTO.getDtpreg())
        || isNull(cotacaoDTO.getCodneg())) {
      throw new DataIntegrityViolationException("Violação de chave na inserção de COTACAO");
    }
    try {
      retorno = template.update(
          sqlUtil.getInsert(),
          sqlUtil.toParameters(cotacaoDTO)
      );
    } catch (Exception ex) {
      log.error("Erro na execução do método COTACAO: {} ",  ex.getMessage());
      throw new Exception("Erro na execução do método COTACAO");
    }
    return retorno > 0;
  }

}
