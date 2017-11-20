package com.ricardococati.layouts;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;

import com.ricardococati.enums.TiposCamposEnum;
import com.ricardococati.tokenize.FixedLengthTokenizer;

import lombok.Data;

@Data
public class HeaderLoteLayout implements ILayoutArquivo {

	private static final long serialVersionUID = -5870867261694192858L;
	private String[] campos;
	private Range[] tamanhos;
	private Map<String, TiposCamposEnum> tipos = new HashMap<String, TiposCamposEnum>();

	private static final String CODIGO_BANCO_COMPENSACAO	= "codBcoCompensacao";
	private static final String LOTE_SERVICO				= "loteServico";
	private static final String TIPO_REGISTRO				= "tipoRegistro";
	private static final String TIPO_OPERACAO				= "tipoOperacao";
	private static final String TIPO_SERVICO				= "tipoServico";
	private static final String ESCLUSIVO_CNAB			    = "exclusivoCnab";
	private static final String NUMERO_VERSAO_LAYOUT_LOTE	= "nroVersaoLayoutLote";
	private static final String ESCLUSIVO_CNAB2			    = "exclusivoCnab2";
	private static final String TIPO_INSCRICAO_EMPRESA 		= "tipoInscricaoEmpresa";
	private static final String NUMERO_INSCRICAO_EMPRESA	= "nroInscricaoEmpresa";
	private static final String CODIGO_CONVENIO_BANCO		= "codConvenioBco";
	private static final String AGENCIA_MANTENEDORA_CONTA	= "agenciaMantenedoraConta";
	private static final String DIGITO_VERIFICADOR_AGENCIA	= "dvAgencia";
	private static final String NUMERO_CONTA_CORRENTE		= "nroContaCorrente";
	private static final String DIGITO_VERIFICADOR_CONTA	= "dvConta";
	private static final String DIGITO_VERIFICADOR_AG_CONTA	= "dvAgConta";
	private static final String NOME_EMPRESA				= "nomeEmpresa";
	private static final String ESCLUSIVO_CNAB3			    = "exclusivoCnab3";

	private static final Range RANGE_CODIGO_BANCO_COMPENSACAO	= new Range(1,3);
	private static final Range RANGE_LOTE_SERVICO				= new Range(4,7);
	private static final Range RANGE_TIPO_REGISTRO				= new Range(8,8);
	private static final Range RANGE_TIPO_OPERACAO				= new Range(9,9);
	private static final Range RANGE_TIPO_SERVICO				= new Range(10,11);
	private static final Range RANGE_ESCLUSIVO_CNAB			    = new Range(12,13);
	private static final Range RANGE_NUMERO_VERSAO_LAYOUT_LOTE	= new Range(14,16);
	private static final Range RANGE_ESCLUSIVO_CNAB2		    = new Range(17,17);
	private static final Range RANGE_TIPO_INSCRICAO_EMPRESA 	= new Range(18,18);
	private static final Range RANGE_NUMERO_INSCRICAO_EMPRESA	= new Range(19,33);
	private static final Range RANGE_CODIGO_CONVENIO_BANCO		= new Range(34,53);
	private static final Range RANGE_AGENCIA_MANTENEDORA_CONTA	= new Range(54,58);
	private static final Range RANGE_DIGITO_VERIFICADOR_AGENCIA	= new Range(59,59);
	private static final Range RANGE_NUMERO_CONTA_CORRENTE		= new Range(60,71);
	private static final Range RANGE_DIGITO_VERIFICADOR_CONTA	= new Range(72,72);
	private static final Range RANGE_DIGITO_VERIFICADOR_AG_CONTA= new Range(73,73);
	private static final Range RANGE_NOME_EMPRESA				= new Range(74,103);
	private static final Range RANGE_ESCLUSIVO_CNAB3			= new Range(104,240);


	public HeaderLoteLayout() {
		super();
		this.campos = new String[]{	CODIGO_BANCO_COMPENSACAO	,
									LOTE_SERVICO				,
									TIPO_REGISTRO				,
									TIPO_OPERACAO				,
									TIPO_SERVICO				,
									ESCLUSIVO_CNAB			    ,
									NUMERO_VERSAO_LAYOUT_LOTE	,
									ESCLUSIVO_CNAB2			    ,
									TIPO_INSCRICAO_EMPRESA 		,
									NUMERO_INSCRICAO_EMPRESA	,
									CODIGO_CONVENIO_BANCO		,
									AGENCIA_MANTENEDORA_CONTA	,
									DIGITO_VERIFICADOR_AGENCIA	,
									NUMERO_CONTA_CORRENTE		,
									DIGITO_VERIFICADOR_CONTA	,
									DIGITO_VERIFICADOR_AG_CONTA	,
									NOME_EMPRESA				,
									ESCLUSIVO_CNAB3			    };

		this.tamanhos = new Range[] {RANGE_CODIGO_BANCO_COMPENSACAO		,
									 RANGE_LOTE_SERVICO					,
									 RANGE_TIPO_REGISTRO				,
									 RANGE_TIPO_OPERACAO				,
									 RANGE_TIPO_SERVICO					,
									 RANGE_ESCLUSIVO_CNAB   			,
									 RANGE_NUMERO_VERSAO_LAYOUT_LOTE  	,
									 RANGE_ESCLUSIVO_CNAB2  			,
									 RANGE_TIPO_INSCRICAO_EMPRESA  		,
									 RANGE_NUMERO_INSCRICAO_EMPRESA		,
									 RANGE_CODIGO_CONVENIO_BANCO		,
									 RANGE_AGENCIA_MANTENEDORA_CONTA	,
									 RANGE_DIGITO_VERIFICADOR_AGENCIA	,
									 RANGE_NUMERO_CONTA_CORRENTE		,
									 RANGE_DIGITO_VERIFICADOR_CONTA     ,
									 RANGE_DIGITO_VERIFICADOR_AG_CONTA  ,
									 RANGE_NOME_EMPRESA     			,
									 RANGE_ESCLUSIVO_CNAB3  			};


		configurarTiposCampos();
	}

	private void configurarTiposCampos() {
		tipos.put(CODIGO_BANCO_COMPENSACAO			, TiposCamposEnum.NUMERICO);
		tipos.put(LOTE_SERVICO						, TiposCamposEnum.NUMERICO);
		tipos.put(TIPO_REGISTRO						, TiposCamposEnum.NUMERICO);
		tipos.put(TIPO_OPERACAO						, TiposCamposEnum.ALFA);
		tipos.put(TIPO_SERVICO						, TiposCamposEnum.NUMERICO);
		tipos.put(ESCLUSIVO_CNAB					, TiposCamposEnum.ALFA);
		tipos.put(NUMERO_VERSAO_LAYOUT_LOTE			, TiposCamposEnum.NUMERICO);
		tipos.put(ESCLUSIVO_CNAB2					, TiposCamposEnum.ALFA);
		tipos.put(TIPO_INSCRICAO_EMPRESA			, TiposCamposEnum.NUMERICO);
		tipos.put(NUMERO_INSCRICAO_EMPRESA			, TiposCamposEnum.NUMERICO);
		tipos.put(CODIGO_CONVENIO_BANCO				, TiposCamposEnum.ALFA);
		tipos.put(AGENCIA_MANTENEDORA_CONTA			, TiposCamposEnum.NUMERICO);
		tipos.put(DIGITO_VERIFICADOR_AGENCIA		, TiposCamposEnum.ALFA);
		tipos.put(NUMERO_CONTA_CORRENTE				, TiposCamposEnum.NUMERICO);
		tipos.put(DIGITO_VERIFICADOR_CONTA			, TiposCamposEnum.ALFA);
		tipos.put(DIGITO_VERIFICADOR_AG_CONTA		, TiposCamposEnum.ALFA);
		tipos.put(NOME_EMPRESA						, TiposCamposEnum.ALFA);
		tipos.put(ESCLUSIVO_CNAB3					, TiposCamposEnum.ALFA);
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
