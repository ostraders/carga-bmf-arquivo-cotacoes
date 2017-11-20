package com.ricardococati.dao.impl;

import java.io.Serializable;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.ISegmentoDAO;
import com.ricardococati.dto.SegmentoDTO;
import com.ricardococati.enums.DataBaseInfosEnum;
import com.ricardococati.util.SQLAppender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SegmentoDAO extends GenericDAO implements ISegmentoDAO, Serializable{

	private static final long serialVersionUID = 8963892631378621289L;

	@Override
	public Long incluirSegmento() throws Exception {
		log.info("Executando método: incluirSegmento");
		final SQLAppender sql = new SQLAppender(100);
		SegmentoDTO segmento = new SegmentoDTO();
		try {
			jdbcTemplate = new JdbcTemplate(dataSource);
	
			segmento.setNroSegmento(getSequence("SEGMENTO_SEQ", jdbcTemplate).longValue());
			segmento.setTipoRegistro('N');
	
			sql.appendSQL("  INSERT INTO " + DataBaseInfosEnum.SCHEMA.getTexto() + ".SEGMENTO (	");
			sql.appendSQL("    NRO_SEGMENTO ");
			sql.appendSQL("    , TIPO_REGISTRO) ");
			sql.appendSQL("  VALUES (?,?) ");
	
			jdbcTemplate.update(sql.getAppendSQLSemQuebra().toString(), obterCamposObjeto(segmento));
		} catch (Exception ex) {
			log.error("Erro na execução do método incluirHeaderArquivo: " + ex.getMessage());
			throw ex;
		}
		return segmento.getNroSegmento();
	}
	
	private Object[] obterCamposObjeto(SegmentoDTO segmento) {
		return new Object[] {segmento.getNroSegmento() , segmento.getTipoRegistro() };
	}

}