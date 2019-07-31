package com.ricardococati.dao.impl;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.IHeaderPGDAO;
import com.ricardococati.dto.HeaderDTO;
import com.ricardococati.enums.DataBaseInfosEnum;
import com.ricardococati.util.SQLAppender;
import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class HeaderDAO extends GenericDAO implements IHeaderPGDAO, Serializable{

  private static final long serialVersionUID = 3210731296043904591L;

  @Override
  public boolean incluirHeaderArquivo(HeaderDTO headerDTO) throws Exception {
    log.info("Executando método: insereHeaderArquivo");
    int retorno = 0;
    final SQLAppender sql = new SQLAppender(100);
    try {
      jdbcTemplate = new JdbcTemplate(dataSource);

      headerDTO.setIdentificacaoArquivo(getSequence("ARQUIVO_SEQ", jdbcTemplate).longValue());

      sql.appendSQL("  INSERT INTO " + DataBaseInfosEnum.SCHEMA.getTexto() + ".HEADER ");
      sql.appendSQL("       ( CODIGO_ORIGEM ");
      sql.appendSQL("       , NOME_ARQUIVO ");
      sql.appendSQL("       , DATA_GERACAO_ARQUIVO ");
      sql.appendSQL("       , TIPO_REGISTRO ");
      sql.appendSQL("       , IDENTIFICACAO_REG");
      sql.appendSQL("  VALUES (?,?,?,?,?,?,?) ");

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
