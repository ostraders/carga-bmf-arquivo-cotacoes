package com.ricardococati.layouts;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;

import com.ricardococati.enums.TiposCamposEnum;

import lombok.Data;

@Data
public class TrailerLayout  implements ILayoutArquivo {

	private static final long serialVersionUID = 6825797649997380250L;
	private String[] campos;
	private Range[] tamanhos;
	private Map<String, TiposCamposEnum> tipos = new HashMap<String, TiposCamposEnum>();

	private static final String CODIGO_COMPENSACAO_BANCO		= "codCompensacaoBco";
	private static final String LOTE_SERVICO					= "loteServico";
	private static final String TIPO_REGISTRO					= "tipoRegistro";
	private static final String USO_EXCLUSIVO_CNAB 				= "dscCnab1";
	private static final String QUANTIDADE_LOTES_ARQUIVO		= "quantidadeLotesArquivo";
	private static final String QUANTIDADE_REGISTROS_ARQUIVO	= "quantidadeRegistrosArquivo";
	private static final String QUANTIDADE_CONTAS_LOTE			= "quantidadeContasLote";
	private static final String USO_EXCLUSIVO_CNAB2				= "dscCnab2";

	private static final Range RANGE_CODIGO_COMPENSACAO_BANCO		= new Range(1,3);
	private static final Range RANGE_LOTE_SERVICO					= new Range(4,7);
	private static final Range RANGE_TIPO_REGISTRO					= new Range(8,8);
	private static final Range RANGE_USO_EXCLUSIVO_CNAB 			= new Range(9,17);
	private static final Range RANGE_QUANTIDADE_LOTES_ARQUIVO		= new Range(18,23);
	private static final Range RANGE_QUANTIDADE_REGISTROS_ARQUIVO	= new Range(24,29);
	private static final Range RANGE_QUANTIDADE_CONTAS_LOTE			= new Range(30,35);
	private static final Range RANGE_USO_EXCLUSIVO_CNAB2			= new Range(36,240);

	public TrailerLayout() {
		super();
		this.campos = new String[]{	CODIGO_COMPENSACAO_BANCO	,
									LOTE_SERVICO				,
									TIPO_REGISTRO				,
									USO_EXCLUSIVO_CNAB 			,
									QUANTIDADE_LOTES_ARQUIVO	,
									QUANTIDADE_REGISTROS_ARQUIVO,
									QUANTIDADE_CONTAS_LOTE		,
									USO_EXCLUSIVO_CNAB2};

		this.tamanhos = new Range[] { RANGE_CODIGO_COMPENSACAO_BANCO		,
										RANGE_LOTE_SERVICO					,
										RANGE_TIPO_REGISTRO					,
										RANGE_USO_EXCLUSIVO_CNAB 			,
										RANGE_QUANTIDADE_LOTES_ARQUIVO		,
										RANGE_QUANTIDADE_REGISTROS_ARQUIVO	,
										RANGE_QUANTIDADE_CONTAS_LOTE		,
										RANGE_USO_EXCLUSIVO_CNAB2};
		configurarTiposCampos();
	}

	private void configurarTiposCampos() {
		tipos.put(CODIGO_COMPENSACAO_BANCO		,TiposCamposEnum.NUMERICO);
		tipos.put(LOTE_SERVICO					,TiposCamposEnum.NUMERICO);
		tipos.put(TIPO_REGISTRO					,TiposCamposEnum.NUMERICO);
		tipos.put(USO_EXCLUSIVO_CNAB 			,TiposCamposEnum.ALFA);
		tipos.put(QUANTIDADE_LOTES_ARQUIVO		,TiposCamposEnum.NUMERICO);
		tipos.put(QUANTIDADE_REGISTROS_ARQUIVO	,TiposCamposEnum.NUMERICO);
		tipos.put(QUANTIDADE_CONTAS_LOTE		,TiposCamposEnum.NUMERICO);
		tipos.put(USO_EXCLUSIVO_CNAB2			,TiposCamposEnum.ALFA);
	}

	@Override
	public LineTokenizer configurarParser() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		tokenizer.setColumns(tamanhos);
		tokenizer.setNames(campos);
		tokenizer.setStrict(true);
		return tokenizer;
	}

}
