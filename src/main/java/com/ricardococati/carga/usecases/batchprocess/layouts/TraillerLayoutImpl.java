package com.ricardococati.carga.usecases.batchprocess.layouts;

import com.ricardococati.carga.entities.enums.TiposCamposEnum;
import com.ricardococati.carga.usecases.batchprocess.tokenize.FixedLengthTokenizer;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;

@Data
public class TraillerLayoutImpl implements LayoutArquivo {

  private static final long serialVersionUID = -1602197853729017928L;
  private String[] campos;
  private Range[] tamanhos;
  private Map<String, TiposCamposEnum> tipos = new HashMap<String, TiposCamposEnum>();

  private static final String TIPO_REGISTRO = "tipoRegistro";
  private static final String NOME_DO_ARQUIVO = "nomeDoArquivo";
  private static final String CODIGO_DA_ORIGEM = "codigoDaOrigem";
  private static final String DATA_DA_GERACAO_DO_ARQUIVO = "dataDaGeracaoDoArquivo";
  private static final String TOTAL_DE_REGISTROS = "totalDeRegistros";
  private static final String RESERVA = "reserva";

  private static final Range RANGE_TIPO_REGISTRO = new Range(1, 2);
  private static final Range RANGE_NOME_DO_ARQUIVO = new Range(3, 15);
  private static final Range RANGE_CODIGO_DA_ORIGEM = new Range(16, 23);
  private static final Range RANGE_DATA_DA_GERACAO_DO_ARQUIVO = new Range(24, 31);
  private static final Range RANGE_TOTAL_DE_REGISTROS = new Range(32, 42);
  private static final Range RANGE_RESERVA = new Range(43, 245);


  public TraillerLayoutImpl() {
    super();
    this.campos = new String[]{TIPO_REGISTRO,
        NOME_DO_ARQUIVO,
        CODIGO_DA_ORIGEM,
        DATA_DA_GERACAO_DO_ARQUIVO,
        TOTAL_DE_REGISTROS,
        RESERVA};

    this.tamanhos = new Range[]{RANGE_TIPO_REGISTRO,
        RANGE_NOME_DO_ARQUIVO,
        RANGE_CODIGO_DA_ORIGEM,
        RANGE_DATA_DA_GERACAO_DO_ARQUIVO,
        RANGE_TOTAL_DE_REGISTROS,
        RANGE_RESERVA};

    configurarTiposCampos();
  }

  private void configurarTiposCampos() {
    tipos.put(TIPO_REGISTRO, TiposCamposEnum.NUMERICO);
    tipos.put(NOME_DO_ARQUIVO, TiposCamposEnum.ALFA);
    tipos.put(CODIGO_DA_ORIGEM, TiposCamposEnum.ALFA);
    tipos.put(DATA_DA_GERACAO_DO_ARQUIVO, TiposCamposEnum.DATA);
    tipos.put(TOTAL_DE_REGISTROS, TiposCamposEnum.NUMERICO);
    tipos.put(RESERVA, TiposCamposEnum.ALFA);
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
