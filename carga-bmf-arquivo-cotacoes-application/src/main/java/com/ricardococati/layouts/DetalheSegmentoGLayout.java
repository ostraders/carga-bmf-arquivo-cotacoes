package com.ricardococati.layouts;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;

import com.ricardococati.enums.TiposCamposEnum;
import com.ricardococati.tokenize.FixedLengthTokenizer;

import lombok.Data;

@Data
public class DetalheSegmentoGLayout implements ILayoutArquivo {

	private static final long serialVersionUID = -1550389035997848703L;
	private String[] campos;
	private Range[] tamanhos;
	private Map<String, TiposCamposEnum> tipos = new HashMap<String, TiposCamposEnum>();

	private static final String COD_COMPENSACAO_BANCO		= "codCompensacaoBco";
	private static final String LOTE_SERVICO				= "loteServico";
	private static final String TIPO_REGISTRO				= "tipoRegistro";
	private static final String NRO_SEQUENCIAL				= "nroSequencial";
	private static final String COD_SEGMENTO				= "codSegmento";
	private static final String FORMA_PAGAMENTO				= "formaPgto";
	private static final String COD_OCORRENCIA_BASE			= "codOcorrenciaBase";
	private static final String COD_BARRA					= "codBarra";
	private static final String TPO_INSC_CEDENTE			= "tpoInscCedente";
	private static final String INSC_CEDENTE				= "inscCedente";
	private static final String NOME_CEDENTE				= "nomeCedente";
	private static final String DTA_VENCIMENTO				= "dtaVencimento";
	private static final String VLR_TITULO					= "vlrTitulo";
	private static final String QTD_MOEDA					= "qtdMoeda";
	private static final String COD_MOEDA					= "codMoeda";
	private static final String NRO_DOC_COBRANCA			= "nroDocCobranca";
	private static final String COD_AG_COBRANCA				= "codAgCobranca";
	private static final String DV_AGENCIA					= "dvAgencia";
	private static final String PRACA_COBRANCA				= "pracaCobranca";
	private static final String COD_CARTEIRA				= "codCarteira";
	private static final String ESPECIE_TITULO				= "especieTitulo";
	private static final String DTA_EMISSAO_TITULO			= "dtaEmissaoTitulo";
	private static final String JUROS_MORA_POR_DIA			= "jurosMoraPorDia";
	private static final String COD_DESCONTO				= "codDesconto";
	private static final String DTA_DESCONTO				= "dtaDesconto";
	private static final String VLR_DESCONTO				= "vlrDesconto";
	private static final String COD_PROTESTO				= "codProtesto";
	private static final String PRAZO_PROTESTO				= "prazoProtesto";
	private static final String DTA_LIMITE_PAGTO_TITULO		= "dtaLimitePagtoTitulo";
	private static final String OCOR_PROCESSAMENTO			= "ocorProcessamento";

	private static final Range RANGE_COD_COMPENSACAO_BANCO		= new Range(1,3);
	private static final Range RANGE_LOTE_SERVICO				= new Range(4,7);
	private static final Range RANGE_TIPO_REGISTRO				= new Range(8,8);
	private static final Range RANGE_NRO_SEQUENCIAL				= new Range(9,13);
	private static final Range RANGE_COD_SEGMENTO				= new Range(14,14);
	private static final Range RANGE_FORMA_PAGAMENTO			= new Range(15,16);
	private static final Range RANGE_COD_OCORRENCIA_BASE		= new Range(17,17);
	private static final Range RANGE_COD_BARRA					= new Range(18,61);
	private static final Range RANGE_TPO_INSC_CEDENTE			= new Range(62,62);
	private static final Range RANGE_INSC_CEDENTE				= new Range(63,77);
	private static final Range RANGE_NOME_CEDENTE				= new Range(78,107);
	private static final Range RANGE_DTA_VENCIMENTO				= new Range(108,115);
	private static final Range RANGE_VLR_TITULO					= new Range(116,130);
	private static final Range RANGE_QTD_MOEDA					= new Range(131,145);
	private static final Range RANGE_COD_MOEDA					= new Range(146,147);
	private static final Range RANGE_NRO_DOC_COBRANCA			= new Range(148,162);
	private static final Range RANGE_COD_AG_COBRANCA			= new Range(163,167);
	private static final Range RANGE_DV_AGENCIA					= new Range(168,168);
	private static final Range RANGE_PRACA_COBRANCA				= new Range(169,178);
	private static final Range RANGE_COD_CARTEIRA				= new Range(179,179);
	private static final Range RANGE_ESPECIE_TITULO				= new Range(180,181);
	private static final Range RANGE_DTA_EMISSAO_TITULO			= new Range(182,189);
	private static final Range RANGE_JUROS_MORA_POR_DIA			= new Range(190,204);
	private static final Range RANGE_COD_DESCONTO				= new Range(205,205);
	private static final Range RANGE_DTA_DESCONTO				= new Range(206,213);
	private static final Range RANGE_VLR_DESCONTO				= new Range(214,228);
	private static final Range RANGE_COD_PROTESTO				= new Range(229,229);
	private static final Range RANGE_PRAZO_PROTESTO				= new Range(230,231);
	private static final Range RANGE_DTA_LIMITE_PAGTO_TITULO	= new Range(232,239);
	private static final Range RANGE_OCOR_PROCESSAMENTO			= new Range(240,240);

	public DetalheSegmentoGLayout() {
		super();
		this.campos = new String[]{ COD_COMPENSACAO_BANCO		,
									LOTE_SERVICO				,
									TIPO_REGISTRO				,
									NRO_SEQUENCIAL				,
									COD_SEGMENTO				,
									FORMA_PAGAMENTO				,
									COD_OCORRENCIA_BASE			,
									COD_BARRA					,
									TPO_INSC_CEDENTE			,
									INSC_CEDENTE				,
									NOME_CEDENTE				,
									DTA_VENCIMENTO				,
									VLR_TITULO					,
									QTD_MOEDA					,
									COD_MOEDA					,
									NRO_DOC_COBRANCA			,
									COD_AG_COBRANCA				,
									DV_AGENCIA					,
									PRACA_COBRANCA				,
									COD_CARTEIRA				,
									ESPECIE_TITULO				,
									DTA_EMISSAO_TITULO			,
									JUROS_MORA_POR_DIA			,
									COD_DESCONTO				,
									DTA_DESCONTO				,
									VLR_DESCONTO				,
									COD_PROTESTO				,
									PRAZO_PROTESTO				,
									DTA_LIMITE_PAGTO_TITULO		,
									OCOR_PROCESSAMENTO          };
		
		this.tamanhos = new Range[] {RANGE_COD_COMPENSACAO_BANCO	,
									 RANGE_LOTE_SERVICO				,
									 RANGE_TIPO_REGISTRO			,
									 RANGE_NRO_SEQUENCIAL			,
									 RANGE_COD_SEGMENTO				,
									 RANGE_FORMA_PAGAMENTO			,
									 RANGE_COD_OCORRENCIA_BASE		,
									 RANGE_COD_BARRA				,
									 RANGE_TPO_INSC_CEDENTE			,
									 RANGE_INSC_CEDENTE				,
									 RANGE_NOME_CEDENTE				,
									 RANGE_DTA_VENCIMENTO			,
									 RANGE_VLR_TITULO				,
									 RANGE_QTD_MOEDA				,
									 RANGE_COD_MOEDA				,
									 RANGE_NRO_DOC_COBRANCA			,
									 RANGE_COD_AG_COBRANCA			,
									 RANGE_DV_AGENCIA				,
									 RANGE_PRACA_COBRANCA			,
									 RANGE_COD_CARTEIRA				,
									 RANGE_ESPECIE_TITULO			,
									 RANGE_DTA_EMISSAO_TITULO		,
									 RANGE_JUROS_MORA_POR_DIA		,
									 RANGE_COD_DESCONTO				,
									 RANGE_DTA_DESCONTO				,
									 RANGE_VLR_DESCONTO				,
									 RANGE_COD_PROTESTO				,
									 RANGE_PRAZO_PROTESTO			,
									 RANGE_DTA_LIMITE_PAGTO_TITULO	,
									 RANGE_OCOR_PROCESSAMENTO		};
		
		configurarTiposCampos();
	}

	private void configurarTiposCampos() {
		tipos.put(COD_COMPENSACAO_BANCO		, TiposCamposEnum.NUMERICO);
		tipos.put(LOTE_SERVICO				, TiposCamposEnum.NUMERICO);
		tipos.put(TIPO_REGISTRO				, TiposCamposEnum.NUMERICO);
		tipos.put(NRO_SEQUENCIAL			, TiposCamposEnum.NUMERICO);
		tipos.put(COD_SEGMENTO				, TiposCamposEnum.ALFA);
		tipos.put(FORMA_PAGAMENTO			, TiposCamposEnum.NUMERICO);
		tipos.put(COD_OCORRENCIA_BASE		, TiposCamposEnum.NUMERICO);
		tipos.put(COD_BARRA					, TiposCamposEnum.ALFA);
		tipos.put(TPO_INSC_CEDENTE			, TiposCamposEnum.NUMERICO);
		tipos.put(INSC_CEDENTE				, TiposCamposEnum.NUMERICO);
		tipos.put(NOME_CEDENTE				, TiposCamposEnum.ALFA);
		tipos.put(DTA_VENCIMENTO			, TiposCamposEnum.NUMERICO);
		tipos.put(VLR_TITULO				, TiposCamposEnum.NUMERICO);
		tipos.put(QTD_MOEDA					, TiposCamposEnum.NUMERICO);
		tipos.put(COD_MOEDA					, TiposCamposEnum.NUMERICO);
		tipos.put(NRO_DOC_COBRANCA			, TiposCamposEnum.ALFA);
		tipos.put(COD_AG_COBRANCA			, TiposCamposEnum.NUMERICO);
		tipos.put(DV_AGENCIA				, TiposCamposEnum.ALFA);
		tipos.put(PRACA_COBRANCA			, TiposCamposEnum.ALFA);
		tipos.put(COD_CARTEIRA				, TiposCamposEnum.ALFA);
		tipos.put(ESPECIE_TITULO			, TiposCamposEnum.NUMERICO);
		tipos.put(DTA_EMISSAO_TITULO		, TiposCamposEnum.DATA);
		tipos.put(JUROS_MORA_POR_DIA		, TiposCamposEnum.NUMERICO);
		tipos.put(COD_DESCONTO				, TiposCamposEnum.NUMERICO);
		tipos.put(DTA_DESCONTO				, TiposCamposEnum.DATA);
		tipos.put(VLR_DESCONTO				, TiposCamposEnum.NUMERICO);
		tipos.put(COD_PROTESTO				, TiposCamposEnum.NUMERICO);
		tipos.put(PRAZO_PROTESTO			, TiposCamposEnum.NUMERICO);
		tipos.put(DTA_LIMITE_PAGTO_TITULO	, TiposCamposEnum.DATA);
		tipos.put(OCOR_PROCESSAMENTO        , TiposCamposEnum.ALFA);
	}

	@Override
	public LineTokenizer configurarParser() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(getTamanhos());
        tokenizer.setNames(getCampos());
        tokenizer.setStrict(true);
        return tokenizer;
	}

}
