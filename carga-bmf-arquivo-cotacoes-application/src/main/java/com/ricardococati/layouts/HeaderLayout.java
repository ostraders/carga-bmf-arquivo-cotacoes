package com.ricardococati.layouts;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;

import com.ricardococati.enums.TiposCamposEnum;
import com.ricardococati.tokenize.FixedLengthTokenizer;

import lombok.Data;

@Data
public class HeaderLayout implements ILayoutArquivo {

	private static final long serialVersionUID = -5970289253729017928L;
	private String[] campos;
	private Range[] tamanhos;
	private Map<String, TiposCamposEnum> tipos = new HashMap<String, TiposCamposEnum>();

	private static final String CODIGO_BANCO_COMPENSACAO= "nroBco";
	private static final String LOTE_SERVICO = "loteServico";
	private static final String TIPO_REGISTRO = "tipoRegistro";
	private static final String EXCLUSIVO_CNAB = "exclusivoCnab";
	private static final String TIPO_INSCRICAO_EMPRESA = "tipoInscricaoEmpresa";
	private static final String NUMERO_INSCRICAO_EMPRESA= "nroInscricaoEmpresa";
	private static final String CODIGO_CONVENIO_BANCO = "nroConvenio";
	private static final String AGENCIA_MANTENEDORA_CONTA= "nroAgencia";
	private static final String DIGITO_VERIFICADOR_AGENCIA = "dvAgencia";
	private static final String NUMERO_CONTA_CORRENTE = "nroContaCorrente";
	private static final String DIGITO_VERIFICADOR_CONTA = "dvConta";
	private static final String DIGITO_VERIFICADOR_AG_CONTA = "dvAgConta";
	private static final String NOME_EMPRESA = "nomeEmpresa";
	private static final String NOME_BANCO = "nomeBco";
	private static final String EXCLUSIVO_CNAB_2 = "exclusivoCnab2";
	private static final String CODIGO_REMESSA_RETORNO = "nroRemessaRet";
	private static final String DATA_GERACAO_ARQUIVO = "dataGeracaoArquivo";
	private static final String HORA_GERACAO_ARQUIVO = "horaGeracaoArquivo";
	private static final String NUMERO_SEQUENCIAL_ARQUIVO = "nroSequencialArquivo";
	private static final String NUMERO_VERSAO_LAYOUT_ARQUIVO = "nroVersaoLayoutArquivo";
	private static final String DENSIDADE_GRAVACAO_ARQUIVO = "densidadeGravacaoArquivo";
	private static final String RESERVADO_BANCO = "reservadorBco";
	private static final String RESERVADO_EMPRESA = "reservadoEmpresa";
	private static final String EXCLUSIVO_CNAB_3 = "exclusivoCnab3";

	private static final Range RANGE_CODIGO_BANCO_COMPENSACAO = new Range(1,3);
	private static final Range RANGE_LOTE_SERVICO = new Range(4,7);
	private static final Range RANGE_TIPO_REGISTRO = new Range(8,8);
	private static final Range RANGE_EXCLUSIVO_CNAB = new Range(9,17);
	private static final Range RANGE_TIPO_INSCRICAO_EMPRESA = new Range(18,18);
	private static final Range RANGE_NUMERO_INSCRICAO_EMPRESA = new Range(19,32);
	private static final Range RANGE_CODIGO_CONVENIO_BANCO = new Range(33,52);
	private static final Range RANGE_AGENCIA_MANTENEDORA_CONTA = new Range(53,57);
	private static final Range RANGE_DIGITO_VERIFICADOR_AGENCIA = new Range(58,58);
	private static final Range RANGE_NUMERO_CONTA_CORRENTE = new Range(59,70);
	private static final Range RANGE_DIGITO_VERIFICADOR_CONTA = new Range(71,71);
	private static final Range RANGE_DIGITO_VERIFICADOR_AG_CONTA = new Range(72,72);
	private static final Range RANGE_NOME_EMPRESA = new Range(73,102);
	private static final Range RANGE_NOME_BANCO = new Range(103,132);
	private static final Range RANGE_EXCLUSIVO_CNAB_2 = new Range(133,142);
	private static final Range RANGE_CODIGO_REMESSA_RETORNO = new Range(143,143);
	private static final Range RANGE_DATA_GERACAO_ARQUIVO = new Range(144,151);
	private static final Range RANGE_HORA_GERACAO_ARQUIVO = new Range(152,157);
	private static final Range RANGE_NUMERO_SEQUENCIAL_ARQUIVO = new Range(158,163);
	private static final Range RANGE_NUMERO_VERSAO_LAYOUT_ARQUIVO = new Range(164,166);
	private static final Range RANGE_DENSIDADE_GRAVACAO_ARQUIVO = new Range(167,171);
	private static final Range RANGE_RESERVADO_BANCO = new Range(172,191);
	private static final Range RANGE_RESERVADO_EMPRESA = new Range(192, 211);
	private static final Range RANGE_EXCLUSIVO_CNAB_3 = new Range(212,240);


	public HeaderLayout() {
		super();
		this.campos = new String[]{CODIGO_BANCO_COMPENSACAO 		,
									LOTE_SERVICO 					,
									TIPO_REGISTRO 					,
									EXCLUSIVO_CNAB 					,
									TIPO_INSCRICAO_EMPRESA 			,
									NUMERO_INSCRICAO_EMPRESA 		,
									CODIGO_CONVENIO_BANCO 			,
									AGENCIA_MANTENEDORA_CONTA 		,
									DIGITO_VERIFICADOR_AGENCIA 		,
									NUMERO_CONTA_CORRENTE 			,
									DIGITO_VERIFICADOR_CONTA 		,
									DIGITO_VERIFICADOR_AG_CONTA 	,
									NOME_EMPRESA 					,
									NOME_BANCO 						,
									EXCLUSIVO_CNAB_2 				,
									CODIGO_REMESSA_RETORNO 			,
									DATA_GERACAO_ARQUIVO 			,
									HORA_GERACAO_ARQUIVO 			,
									NUMERO_SEQUENCIAL_ARQUIVO 		,
									NUMERO_VERSAO_LAYOUT_ARQUIVO 	,
									DENSIDADE_GRAVACAO_ARQUIVO 		,
									RESERVADO_BANCO 				,
									RESERVADO_EMPRESA 				,
									EXCLUSIVO_CNAB_3};

		this.tamanhos = new Range[] {RANGE_CODIGO_BANCO_COMPENSACAO 		,
										RANGE_LOTE_SERVICO 					,
										RANGE_TIPO_REGISTRO 				,
										RANGE_EXCLUSIVO_CNAB 				,
										RANGE_TIPO_INSCRICAO_EMPRESA 		,
										RANGE_NUMERO_INSCRICAO_EMPRESA 		,
										RANGE_CODIGO_CONVENIO_BANCO 		,
										RANGE_AGENCIA_MANTENEDORA_CONTA 	,
										RANGE_DIGITO_VERIFICADOR_AGENCIA 	,
										RANGE_NUMERO_CONTA_CORRENTE 		,
										RANGE_DIGITO_VERIFICADOR_CONTA 		,
										RANGE_DIGITO_VERIFICADOR_AG_CONTA 	,
										RANGE_NOME_EMPRESA 					,
										RANGE_NOME_BANCO 					,
										RANGE_EXCLUSIVO_CNAB_2 				,
										RANGE_CODIGO_REMESSA_RETORNO 		,
										RANGE_DATA_GERACAO_ARQUIVO 			,
										RANGE_HORA_GERACAO_ARQUIVO 			,
										RANGE_NUMERO_SEQUENCIAL_ARQUIVO 	,
										RANGE_NUMERO_VERSAO_LAYOUT_ARQUIVO 	,
										RANGE_DENSIDADE_GRAVACAO_ARQUIVO 	,
										RANGE_RESERVADO_BANCO 				,
										RANGE_RESERVADO_EMPRESA 			,
										RANGE_EXCLUSIVO_CNAB_3};

		configurarTiposCampos();
	}

	private void configurarTiposCampos() {
		tipos.put(CODIGO_BANCO_COMPENSACAO 		, TiposCamposEnum.NUMERICO);
		tipos.put(LOTE_SERVICO 					, TiposCamposEnum.NUMERICO);
		tipos.put(TIPO_REGISTRO 				, TiposCamposEnum.NUMERICO);
		tipos.put(EXCLUSIVO_CNAB 				, TiposCamposEnum.ALFA);
		tipos.put(TIPO_INSCRICAO_EMPRESA 		, TiposCamposEnum.NUMERICO);
		tipos.put(NUMERO_INSCRICAO_EMPRESA 		, TiposCamposEnum.NUMERICO);
		tipos.put(CODIGO_CONVENIO_BANCO 		, TiposCamposEnum.ALFA);
		tipos.put(AGENCIA_MANTENEDORA_CONTA 	, TiposCamposEnum.NUMERICO);
		tipos.put(DIGITO_VERIFICADOR_AGENCIA 	, TiposCamposEnum.ALFA);
		tipos.put(NUMERO_CONTA_CORRENTE 		, TiposCamposEnum.NUMERICO);
		tipos.put(DIGITO_VERIFICADOR_CONTA 		, TiposCamposEnum.ALFA);
		tipos.put(DIGITO_VERIFICADOR_AG_CONTA 	, TiposCamposEnum.ALFA);
		tipos.put(NOME_EMPRESA 					, TiposCamposEnum.ALFA);
		tipos.put(NOME_BANCO 					, TiposCamposEnum.ALFA);
		tipos.put(EXCLUSIVO_CNAB_2 				, TiposCamposEnum.ALFA);
		tipos.put(CODIGO_REMESSA_RETORNO 		, TiposCamposEnum.NUMERICO);
		tipos.put(DATA_GERACAO_ARQUIVO 			, TiposCamposEnum.NUMERICO);
		tipos.put(HORA_GERACAO_ARQUIVO 			, TiposCamposEnum.NUMERICO);
		tipos.put(NUMERO_SEQUENCIAL_ARQUIVO 	, TiposCamposEnum.NUMERICO);
		tipos.put(NUMERO_VERSAO_LAYOUT_ARQUIVO 	, TiposCamposEnum.NUMERICO);
		tipos.put(DENSIDADE_GRAVACAO_ARQUIVO 	, TiposCamposEnum.NUMERICO);
		tipos.put(RESERVADO_BANCO 				, TiposCamposEnum.ALFA);
		tipos.put(RESERVADO_EMPRESA 			, TiposCamposEnum.ALFA);
		tipos.put(EXCLUSIVO_CNAB_3				, TiposCamposEnum.ALFA);
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
