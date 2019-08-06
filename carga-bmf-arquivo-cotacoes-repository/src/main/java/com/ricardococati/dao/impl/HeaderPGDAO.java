package com.ricardococati.dao.impl;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.IHeaderPGDAO;
import com.ricardococati.dto.HeaderDTO;
import com.ricardococati.util.SQLAppender;
import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class HeaderPGDAO extends GenericDAO implements IHeaderPGDAO {

  @Override
  public boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception {
    log.info("Executando método: insereHeaderArquivo");
    int retorno = 0;
    final SQLAppender sql = new SQLAppender(100);
    try {
      jdbcTemplate = new JdbcTemplate(dataSource);

      sql.appendSQL("  INSERT INTO HEADER_ARQ ");
      sql.appendSQL("       ( CODIGO_ORIGEM ");
      sql.appendSQL("       , NOME_ARQUIVO ");
      sql.appendSQL("       , DATA_GERACAO_ARQUIVO ");
      sql.appendSQL("       , TIPO_REGISTRO ");
      sql.appendSQL("       , IDENTIFICACAO_REG) ");
      sql.appendSQL("  VALUES (?,?,?,?,?) ");

      retorno = jdbcTemplate.update(sql.getAppendSQLSemQuebra().toString(),
          new Object[] {
              headerDTO.getCodigoDaOrigem()
              ,headerDTO.getNomeDoArquivo()
              ,headerDTO.getDataDaGeracaoDoArquivo()
              ,headerDTO.getTipoRegistro()
              ,headerDTO.getIdentificacaoArquivo()
          });
    } catch (Exception ex) {
      log.error("Erro na execução do método incluirHeaderArquivo: " + ex.getMessage());
      throw ex;
    }

    return retorno > 0;
  }

}
