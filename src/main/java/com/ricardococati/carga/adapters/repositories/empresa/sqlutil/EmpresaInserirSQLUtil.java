package com.ricardococati.carga.adapters.repositories.empresa.sqlutil;

import com.ricardococati.carga.entities.domains.empresa.Empresa;
import com.ricardococati.carga.utils.SQLAppender;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class EmpresaInserirSQLUtil {

  public String getInsert(){
    final SQLAppender sql = new SQLAppender(100);
    sql.appendSQL("	insert into	empresa ( ");
    sql.appendSQL("		id_empresa, ");
    sql.appendSQL("		nome_empresa, ");
    sql.appendSQL("		id_setor, ");
    sql.appendSQL("		id_sub_setor, ");
    sql.appendSQL("		id_segmento) ");
    sql.appendSQL("	values( ");
    sql.appendSQL("		:idEmpresa, ");
    sql.appendSQL("		:nomeEmpresa, ");
    sql.appendSQL("		:idSetor, ");
    sql.appendSQL("		:idSubSetor, ");
    sql.appendSQL("		:idSegmento ");
    sql.appendSQL("	) ");
    sql.appendSQL(" on conflict (nome_empresa) do update set ");
    sql.appendSQL("		id_setor = excluded.id_setor, ");
    sql.appendSQL("		id_sub_setor = excluded.id_sub_setor, ");
    sql.appendSQL("		id_segmento = excluded.id_segmento ");
    return sql.getAppendSQLSemQuebra().toString();
  }

  public MapSqlParameterSource toParameters(final Empresa empresa) {
    return new MapSqlParameterSource()
        .addValue("idEmpresa", empresa.getIdEmpresa())
        .addValue("nomeEmpresa", empresa.getNomeEmpresa())
        .addValue("idSetor", empresa.getSetor().getIdSetor())
        .addValue("idSubSetor", empresa.getSubSetor().getIdSubSetor())
        .addValue("idSegmento", empresa.getSegmento().getIdSegmento());
  }

}
