package com.ricardococati.dao.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.IBoletoDAO;
import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.DetalheSegmentoGDTO;
import com.ricardococati.enums.DataBaseInfosEnum;
import com.ricardococati.util.SQLAppender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoletoDAO extends GenericDAO implements IBoletoDAO, Serializable{

	private static final long COD_OCORRENCIA_ZERO = 0L;
	private static final long serialVersionUID = 8963892631378621289L;
    private static final char CCOD_TIPO_OPER_BOLETO   = 'I';
    private static final int CCOD_TIPO_OCOR_REMESSA = 1;
    private static final int CCOD_TIPO_OCOR_RETORNO = 2;
    
	@Override
	public Long incluirBoleto(DetalheSegmentoGDTO detalheSegmentoGDTO, ArquivoDTO arquivoDTO) throws Exception {
		log.info("Executando método: incluirBoleto");
		final SQLAppender sql = new SQLAppender(100);
		try {
			jdbcTemplate = new JdbcTemplate(dataSource);
	
			sql.appendSQL("  INSERT INTO " + DataBaseInfosEnum.SCHEMA.getTexto() + ".BOLETO (	");
			sql.appendSQL("		COD_BOLETO, ");
			sql.appendSQL("		NRO_SEGMENTO, ");
			sql.appendSQL("		NRO_BANCO, ");
			sql.appendSQL("		NRO_LOTESERV, ");
			sql.appendSQL("		TPO_REGISTRO, ");
			sql.appendSQL("		NRO_SEQUENCIAL_REG, ");
			sql.appendSQL("		COD_SEGMENTO_REG, ");
			sql.appendSQL("		DSC_FORMA_PGTO, ");
			sql.appendSQL("		COD_OCORRENCIA_REM, ");
			sql.appendSQL("		COD_OCORRENCIA_RET, ");
			sql.appendSQL("		COD_BARRA, ");
			sql.appendSQL("		CEDENTE_TPO_INSCRICAO, ");
			sql.appendSQL("		CEDENTE_NRO_INSCRICAO, ");
			sql.appendSQL("		CEDENTE_NOME, ");
			sql.appendSQL("		DTA_VENCIMENTO_TITULO, ");
			sql.appendSQL("		VLR_TITULO, ");
			sql.appendSQL("		QTD_MOEDA, ");
			sql.appendSQL("		COD_MOEDA, ");
			sql.appendSQL("		NRO_DOCUMENTO, ");
			sql.appendSQL("		NRO_AG_COBRADORA, ");
			sql.appendSQL("		NRO_DV_AGENCIA, ");
			sql.appendSQL("		DSC_PRACA_COBRADORA, ");
			sql.appendSQL("		COD_CARTEIRA, ");
			sql.appendSQL("		NRO_ESPECIE_TITULO, ");
			sql.appendSQL("		DTA_EMISSAO_TITULO, ");
			sql.appendSQL("		VLR_JUROS_MORA, ");
			sql.appendSQL("		COD_DESC1, ");
			sql.appendSQL("		DTA_DESC1, ");
			sql.appendSQL("		VLR_DESC1, ");
			sql.appendSQL("		COD_EMP_FNX, ");
			sql.appendSQL("		DTA_OCORRENCIA_PROC)");
			sql.appendSQL("  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
	
			jdbcTemplate.update(sql.getAppendSQLSemQuebra().toString(), obterCamposObjeto(detalheSegmentoGDTO, arquivoDTO));
		} catch (Exception ex) {
			log.error("Erro na execução do método obterCodOcorrenciaBow: " + ex.getMessage());
			throw ex;
		}

		return detalheSegmentoGDTO.getCodBoleto();
	}

	private Object[] obterCamposObjeto(DetalheSegmentoGDTO detalheSegmentoGDTO, ArquivoDTO arquivoDTO) {
		Object[] objRetorno = null;
		objRetorno = new Object[] {
				detalheSegmentoGDTO.getCodBoleto()
				,Long.parseLong(detalheSegmentoGDTO.getCodSegmento())
				,detalheSegmentoGDTO.getCodCompensacaoBco()
				,detalheSegmentoGDTO.getLoteServico()
				,detalheSegmentoGDTO.getTipoRegistro()
				,detalheSegmentoGDTO.getNroSequencial()
				,"G"
				,detalheSegmentoGDTO.getFormaPgto()
				,obterCodOcorrenciaBow(detalheSegmentoGDTO.getCodOcorrenciaBase(), CCOD_TIPO_OCOR_REMESSA)
				,obterCodOcorrenciaBow(detalheSegmentoGDTO.getOcorProcessamento(), CCOD_TIPO_OCOR_RETORNO)
				,detalheSegmentoGDTO.getCodBarra()
				,detalheSegmentoGDTO.getTpoInscCedente()
				,detalheSegmentoGDTO.getInscCedente()
				,detalheSegmentoGDTO.getNomeCedente()
				,detalheSegmentoGDTO.getDtaVencimento()
				,detalheSegmentoGDTO.getVlrTitulo()
				,detalheSegmentoGDTO.getQtdMoeda()
				,detalheSegmentoGDTO.getCodMoeda()
				,detalheSegmentoGDTO.getNroDocCobranca()
				,detalheSegmentoGDTO.getCodAgCobranca()
				,detalheSegmentoGDTO.getDvAgencia()
				,detalheSegmentoGDTO.getPracaCobranca()
				,detalheSegmentoGDTO.getCodCarteira()
				,detalheSegmentoGDTO.getEspecieTitulo()
				,detalheSegmentoGDTO.getDtaEmissaoTitulo()
				,detalheSegmentoGDTO.getJurosMoraPorDia()
				,detalheSegmentoGDTO.getCodDesconto()
				,detalheSegmentoGDTO.getDtaDesconto()
				,detalheSegmentoGDTO.getVlrDesconto()
				,arquivoDTO.getCodEmpFornax()
				,detalheSegmentoGDTO.getDtaLimitePagtoTitulo()
				
			};
		return objRetorno;
	}
	
	private Long obterCodOcorrenciaBow(Long codOcor, int codTipoOcorrencia) {
		log.info("Executando método: obterCodOcorrenciaBow");
		Long retorno = 0L;
		final SQLAppender sql = new SQLAppender(100);
		if(codOcor > COD_OCORRENCIA_ZERO && codTipoOcorrencia == CCOD_TIPO_OCOR_RETORNO) {
			try {
				jdbcTemplate = new JdbcTemplate(dataSource);
				sql.appendSQL("      SELECT COD_OCORRENCIA ");
				sql.appendSQL("        FROM " + DataBaseInfosEnum.SCHEMA.getTexto() + ".OCORRENCIA ");
				sql.appendSQL("       WHERE COD_TIPO_OCOR = " + codTipoOcorrencia);
				if(codTipoOcorrencia == CCOD_TIPO_OCOR_RETORNO) {
					sql.appendSQL("         AND COD_TIPO_OPER = '" + CCOD_TIPO_OPER_BOLETO + "'");
				}
				sql.appendSQL("         AND COD_LAYOUT    = '" + codOcor + "'");
				
				retorno = jdbcTemplate.query(sql.getAppendSQLSemQuebra().toString() , new ResultSetExtractor<Number>() {
					public Number extractData(ResultSet rs) throws SQLException, DataAccessException {
						rs.next();
						Number number = (Number) rs.getObject(1);
						return number;
					}
				}).longValue();
			} catch (Exception ex) {
				log.error("Erro na execução do método obterCodOcorrenciaBow: " + ex.getMessage());
			}
		}
		return retorno;
	}
	
}
