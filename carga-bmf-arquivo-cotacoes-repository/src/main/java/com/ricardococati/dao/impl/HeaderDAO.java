package com.ricardococati.dao.impl;

import java.io.Serializable;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.IHeaderDAO;
import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.HeaderDTO;
import com.ricardococati.enums.DataBaseInfosEnum;
import com.ricardococati.util.Funcoes;
import com.ricardococati.util.SQLAppender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class HeaderDAO extends GenericDAO implements IHeaderDAO, Serializable{

	private static final long serialVersionUID = 3210731296043904591L;

	@Override
	public boolean incluirHeaderArquivo(HeaderDTO headerDTO, ArquivoDTO arquivoDTO) throws Exception {
		log.info("Executando método: insereHeaderArquivo");
		int retorno = 0;
		final SQLAppender sql = new SQLAppender(100);
		try {
			jdbcTemplate = new JdbcTemplate(dataSource);
			
			headerDTO.setNroArquivo(getSequence("ARQUIVO_SEQ", jdbcTemplate).longValue());
	
			sql.appendSQL("  INSERT INTO " + DataBaseInfosEnum.SCHEMA.getTexto() + ".ARQUIVO ");
			sql.appendSQL("       ( NRO_ARQUIVO ");
			sql.appendSQL("       , COD_EMP_FNX ");
			sql.appendSQL("       , ARQ_NOME ");
			sql.appendSQL("       , VLR_TAMANHO ");
			sql.appendSQL("       , NRO_BANCO ");
			sql.appendSQL("       , NRO_LOTESERV ");
			sql.appendSQL("       , TPO_REGISTRO ");
			sql.appendSQL("       , DESC_CNAB1 ");
			sql.appendSQL("       , TPO_INSCRICAOEMP ");
			sql.appendSQL("       , NRO_INSCRICAOEMP ");
			sql.appendSQL("       , NRO_CONVENIO ");
			sql.appendSQL("       , NRO_AGENCIA ");
			sql.appendSQL("       , DV_AGENCIA ");
			sql.appendSQL("       , NRO_CONTA ");
			sql.appendSQL("       , DV_CONTA ");
			sql.appendSQL("       , DV_AGCONTA ");
			sql.appendSQL("       , NME_EMP ");
			sql.appendSQL("       , NME_BANCO ");
			sql.appendSQL("       , DSC_CNAB2 ");
			sql.appendSQL("       , NRO_REMESSA_RET ");
			sql.appendSQL("       , DTA_GERACAO ");
			sql.appendSQL("       , NRO_SEQUENCIAL ");
			sql.appendSQL("       , NRO_VERSAOLAYOUT ");
			sql.appendSQL("       , DENSIDADE_GRAVA ");
			sql.appendSQL("       , RESERVADO_BCO ");
			sql.appendSQL("       , RESERVADO_EMP ");
			sql.appendSQL("       , DSC_CNAB3 ");
			sql.appendSQL("       , COD_SENDER ");
			sql.appendSQL("       , COD_RECEIVER ");
			sql.appendSQL("       , COD_DOCTYPE ");
			sql.appendSQL("       , COD_TRACKING) ");
			sql.appendSQL("  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
	
			retorno = jdbcTemplate.update(sql.getAppendSQLSemQuebra().toString(),
			new Object[] {
					headerDTO.getNroArquivo()
					,arquivoDTO.getCodEmpFornax()
					,arquivoDTO.getNomeArquivo()
					,arquivoDTO.getTamanhoArquivo()
					,headerDTO.getNroBco()
					,headerDTO.getLoteServico()
					,headerDTO.getTipoRegistro()
					,headerDTO.getExclusivoCnab()
					,headerDTO.getTipoInscricaoEmpresa()
					,headerDTO.getNroInscricaoEmpresa()
					,headerDTO.getNroConvenio()
					,headerDTO.getNroAgencia()
					,headerDTO.getDvAgencia()
					,headerDTO.getNroContaCorrente()
					,headerDTO.getDvConta()
					,headerDTO.getDvAgConta()
					,headerDTO.getNomeEmpresa()
					,headerDTO.getNomeBco()
					,headerDTO.getExclusivoCnab2()
					,headerDTO.getNroRemessaRet()
					,Funcoes.formatarDateUtil(headerDTO.getDataGeracaoArquivo())
					,headerDTO.getNroSequencialArquivo()
					,headerDTO.getNroVersaoLayoutArquivo()
					,headerDTO.getDensidadeGravacaoArquivo()
					,headerDTO.getReservadorBco()
					,headerDTO.getReservadoEmpresa()
					,headerDTO.getExclusivoCnab3()
					,arquivoDTO.getSender()
					,arquivoDTO.getReceiver()
					,arquivoDTO.getDoctype()
					,arquivoDTO.getTrackingID()
				});
		} catch (Exception ex) {
			log.error("Erro na execução do método incluirHeaderArquivo: " + ex.getMessage());
			throw ex;
		}

		return retorno > 0;
	}

}
