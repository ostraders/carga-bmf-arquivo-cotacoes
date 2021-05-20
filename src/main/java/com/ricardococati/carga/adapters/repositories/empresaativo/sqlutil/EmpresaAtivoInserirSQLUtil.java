package com.ricardococati.carga.adapters.repositories.empresaativo.sqlutil;

import com.ricardococati.carga.entities.domains.empresaativo.EmpresaAtivo;
import com.ricardococati.carga.utils.SQLAppender;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class EmpresaAtivoInserirSQLUtil {

  public String getInsert() {
    SQLAppender sql = new SQLAppender(100);
    sql.appendSQL("	insert into empresa_ativo ( ");
    sql.appendSQL("		id_empresa, ");
    sql.appendSQL("		id_ativo) ");
    sql.appendSQL("	values( ");
    sql.appendSQL("		:idEmpresa, ");
    sql.appendSQL("		:idAtivo) ");
    sql.appendSQL(" on conflict do nothing ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParameters(final EmpresaAtivo candlestickDiarioDTO) {
    return new MapSqlParameterSource()
        .addValue("idEmpresa", candlestickDiarioDTO.getIdEmpresa())
        .addValue("idAtivo", candlestickDiarioDTO.getIdAtivo());
  }

}
