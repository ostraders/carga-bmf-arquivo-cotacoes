package com.ricardococati.layouts;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;

import com.ricardococati.enums.TiposCamposEnum;
import com.ricardococati.tokenize.FixedLengthTokenizer;

import lombok.Data;

@Data
public class TrailerLoteLayout implements ILayoutArquivo {

	private static final long serialVersionUID = -6416411233439915892L;
	private String[] campos;
	private Range[] tamanhos;
	private Map<String, TiposCamposEnum> tipos = new HashMap<String, TiposCamposEnum>();
	
	private static final String CODIGO_COMPENSACAO_BANCO	= "codCompensacaoBco";
	private static final String LOTE_SERVICO				= "loteServico";
	private static final String TIPO_REGISTRO				= "tipoRegistro";
	private static final String USO_EXCLUSIVO_CNAB 			= "dscCnab2";
	private static final String QUANTIDADE_REGISTROS_LOTE	= "quantidadeRegistroLote";
	private static final String SOMATORIA_VALORES			= "somatoriaValore";
	private static final String SOMATORIA_QUANTIDADE_MOEDAS	= "somatoriaQtdMoedas";
	private static final String NUMERO_AVISO_DEBITO			= "nroAvisoDebito";
	private static final String USO_EXCLUSIVO_CNAB2			= "dscCnab22";
	private static final String CODIGO_OCORRENCIA_RETORNO	= "codOcorrenciaRet";
	
	private static final Range RANGE_CODIGO_COMPENSACAO_BANCO	= new Range(1,3);
	private static final Range RANGE_LOTE_SERVICO				= new Range(4,7);
	private static final Range RANGE_TIPO_REGISTRO				= new Range(8,8);
	private static final Range RANGE_USO_EXCLUSIVO_CNAB 		= new Range(9,17);
	private static final Range RANGE_QUANTIDADE_REGISTROS_LOTE	= new Range(18,23);
	private static final Range RANGE_SOMATORIA_VALORES			= new Range(24,41);
	private static final Range RANGE_SOMATORIA_QUANTIDADE_MOEDAS= new Range(42,59);
	private static final Range RANGE_NUMERO_AVISO_DEBITO		= new Range(60,65);
	private static final Range RANGE_USO_EXCLUSIVO_CNAB2		= new Range(66,230);
	private static final Range RANGE_CODIGO_OCORRENCIA_RETORNO	= new Range(231,240);

	public TrailerLoteLayout() {
		super();
		this.campos = new String[]{	CODIGO_COMPENSACAO_BANCO	,
									LOTE_SERVICO				,
									TIPO_REGISTRO				,
									USO_EXCLUSIVO_CNAB 			,
									QUANTIDADE_REGISTROS_LOTE	,
									SOMATORIA_VALORES			,
									SOMATORIA_QUANTIDADE_MOEDAS	,
									NUMERO_AVISO_DEBITO			,
									USO_EXCLUSIVO_CNAB2			,
									CODIGO_OCORRENCIA_RETORNO};

		this.tamanhos = new Range[] { RANGE_CODIGO_COMPENSACAO_BANCO		,
										RANGE_LOTE_SERVICO					,
										RANGE_TIPO_REGISTRO					,
										RANGE_USO_EXCLUSIVO_CNAB 			,
										RANGE_QUANTIDADE_REGISTROS_LOTE		,
										RANGE_SOMATORIA_VALORES				,
										RANGE_SOMATORIA_QUANTIDADE_MOEDAS	,
										RANGE_NUMERO_AVISO_DEBITO			,
										RANGE_USO_EXCLUSIVO_CNAB2			,
										RANGE_CODIGO_OCORRENCIA_RETORNO	};

		configurarTiposCampos();
	}

	private void configurarTiposCampos() {
		tipos.put(CODIGO_COMPENSACAO_BANCO	    ,TiposCamposEnum.NUMERICO);
		tipos.put(LOTE_SERVICO				    ,TiposCamposEnum.NUMERICO);
		tipos.put(TIPO_REGISTRO				    ,TiposCamposEnum.NUMERICO);
		tipos.put(USO_EXCLUSIVO_CNAB 			,TiposCamposEnum.ALFA);
		tipos.put(QUANTIDADE_REGISTROS_LOTE	    ,TiposCamposEnum.NUMERICO);
		tipos.put(SOMATORIA_VALORES			    ,TiposCamposEnum.NUMERICO);
		tipos.put(SOMATORIA_QUANTIDADE_MOEDAS	,TiposCamposEnum.NUMERICO);
		tipos.put(NUMERO_AVISO_DEBITO			,TiposCamposEnum.NUMERICO);
		tipos.put(USO_EXCLUSIVO_CNAB2			,TiposCamposEnum.ALFA);
		tipos.put(CODIGO_OCORRENCIA_RETORNO	    ,TiposCamposEnum.ALFA);
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
