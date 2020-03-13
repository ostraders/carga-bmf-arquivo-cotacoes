package com.ricardococati.repository.dao.sqlutil;

import com.ricardococati.model.dto.HeaderDTO;
import com.ricardococati.repository.util.SQLAppender;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class HeaderSQLUtil {

  public String getInsert() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL("   insert into header_arq ( ");
    sql.appendSQL("      codigo_origem ");
    sql.appendSQL("     ,nome_arquivo ");
    sql.appendSQL("     ,data_geracao_arquivo ");
    sql.appendSQL("     ,tipo_registro ");
    sql.appendSQL("     ,identificacao_reg ");
    sql.appendSQL("   ) values ( ");
    sql.appendSQL("     :codigoDaOrigem,  ");
    sql.appendSQL("     :nomeDoArquivo,  ");
    sql.appendSQL("     :dataDaGeracaoDoArquivo, ");
    sql.appendSQL("     :tipoRegistro, ");
    sql.appendSQL("     :identificacaoArquivo ");
    sql.appendSQL("   ) ");
    sql.appendSQL(" on conflict (identificacao_reg) do update set ");
    sql.appendSQL("      codigo_origem = excluded.codigo_origem ");
    sql.appendSQL("     ,nome_arquivo = excluded.nome_arquivo ");
    sql.appendSQL("     ,data_geracao_arquivo = excluded.data_geracao_arquivo ");
    sql.appendSQL("     ,tipo_registro = excluded.tipo_registro ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParameters(final HeaderDTO headerDTO) {
    return new MapSqlParameterSource()
        .addValue("codigoDaOrigem", headerDTO.getCodigoDaOrigem())
        .addValue("nomeDoArquivo", headerDTO.getNomeDoArquivo())
        .addValue("dataDaGeracaoDoArquivo", headerDTO.getDataDaGeracaoDoArquivo())
        .addValue("tipoRegistro", headerDTO.getTipoRegistro())
        .addValue("identificacaoArquivo", headerDTO.getIdentificacaoArquivo());
  }

}
