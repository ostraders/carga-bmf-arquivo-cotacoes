package com.ricardococati.dao.impl;

import java.io.Serializable;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.IValidacaoDAO;
import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.BMFCargaDTO;
import com.ricardococati.enums.DataBaseInfosEnum;
import com.ricardococati.util.SQLAppender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ValidacaoDAO extends GenericDAO implements IValidacaoDAO, Serializable{

	private static final long serialVersionUID = -8418078387562756910L;
	
	@Override
	public Long verificaExisteBoleto(BMFCargaDTO BMFCargaDTO, ArquivoDTO arquivoDTO) throws Exception {
		Long retorno = 0L;
		log.info("Executando método: verificaExisteBoleto");
		final SQLAppender sql = new SQLAppender(100);
		try {
			jdbcTemplate = new JdbcTemplate(dataSource);
			
			sql.appendSQL("  SELECT ");
			sql.appendSQL("  	COUNT(1) ");
			sql.appendSQL("  FROM  ");
			sql.appendSQL("  	" + DataBaseInfosEnum.SCHEMA.getTexto() + ".BOLETO BOL ");
			sql.appendSQL("  	INNER JOIN " + DataBaseInfosEnum.SCHEMA.getTexto() + ".CLIENTE CLI ON CLI.COD_EMP_FNX = BOL.COD_EMP_FNX ");
			sql.appendSQL("  WHERE ");
			sql.appendSQL("  	BOL.DTA_VENCIMENTO_TITULO = TO_DATE('2017-12-18', 'YYYY-MM-DD') ");
			sql.appendSQL("  	AND BOL.VLR_TITULO = 877.33  ");
			sql.appendSQL("  	AND BOL.NRO_DOCUMENTO = '9562 2' ");
			sql.appendSQL("  	AND BOL.CEDENTE_NRO_INSCRICAO = '13380685000190'");
			sql.appendSQL("  	AND CLI.COD_EMP_FNX =  ? ");
			
			retorno = jdbcTemplate.queryForObject(sql.getAppendSQLSemQuebra().toString(), Long.class);
		} catch (Exception e){
			log.error("Erro na execução do método verificaExisteBoleto: " + e.getMessage());
		}

		return retorno;
	}
}