package com.ricardococati.dao.impl;

import java.io.Serializable;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.IArquivoDAO;
import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.enums.DataBaseInfosEnum;
import com.ricardococati.util.SQLAppender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ArquivoDAO extends GenericDAO implements IArquivoDAO, Serializable{

	private static final long serialVersionUID = -8418078387562756910L;
	
	@Override
	public Long buscaDadosIntercambio(ArquivoDTO arquivoDTO) throws Exception {
		log.info("Executando método: buscaDadosIntercambio");
		final SQLAppender sql = new SQLAppender(100);
		try {
			jdbcTemplate = new JdbcTemplate(dataSource);
			
			sql.appendSQL("  SELECT ");
			sql.appendSQL("  	DISTINCT INTC.COD_EMP_FNX ");
			sql.appendSQL("  FROM ");
			sql.appendSQL("  	" + DataBaseInfosEnum.SCHEMA.getTexto() + ".MAPEAMENTO_INTERCAMBIO MAPI");
			sql.appendSQL("  	INNER JOIN " + DataBaseInfosEnum.SCHEMA.getTexto() + ".INTERCAMBIO INTC ON INTC.NRO_INTERCAMBIO = MAPI.NRO_INTERCAMBIO");
			sql.appendSQL("  WHERE ");
			sql.appendSQL("  	MAPI.COD_SENDER = '" + arquivoDTO.getSender() + "'");
			sql.appendSQL("  	AND MAPI.COD_RECEIVER = '" + arquivoDTO.getReceiver() + "'");
			sql.appendSQL("  	AND MAPI.COD_DOCTYPE = '" + arquivoDTO.getDoctype() + "'");
			
			arquivoDTO.setCodEmpFornax(jdbcTemplate.queryForObject(sql.getAppendSQLSemQuebra().toString(), Long.class));
		} catch (Exception e){
			log.error("Erro na execução do método buscaDadosIntercambio: " + e.getMessage());
		}

		return arquivoDTO.getCodEmpFornax();
	}
}