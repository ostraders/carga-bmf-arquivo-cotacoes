package com.ricardococati.carga.usecases.batchprocess.layouts;

import com.ricardococati.carga.entities.enums.TiposCamposEnum;
import com.ricardococati.carga.usecases.batchprocess.tokenize.FixedLengthTokenizer;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;

@Data
public class CotacoesDosPapeisPorDiaLayoutImpl implements LayoutArquivo {

  private static final long serialVersionUID = -1550389035997848703L;

  private String[] campos;
  private Range[] tamanhos;
  private Map<String, TiposCamposEnum> tipos = new HashMap<String, TiposCamposEnum>();

  private static final String TIPREG = "tipoRegistro";
  private static final String DTPREG = "dtpreg";
  private static final String CODBDI = "codbdi";
  private static final String CODNEG = "codneg";
  private static final String TPMERC = "tpmerc";
  private static final String NOMRES = "nomres";
  private static final String ESPECI = "especi";
  private static final String PRAZOT = "prazot";
  private static final String MODREF = "modref";
  private static final String PREABE = "preabe";
  private static final String PREMAX = "premax";
  private static final String PREMIN = "premin";
  private static final String PREMED = "premed";
  private static final String PREULT = "preult";
  private static final String PREOFC = "preofc";
  private static final String PREOFV = "preofv";
  private static final String TOTNEG = "totneg";
  private static final String QUATOT = "quatot";
  private static final String VOLTOT = "voltot";
  private static final String PREEXE = "preexe";
  private static final String INDOPC = "indopc";
  private static final String DATVEN = "datven";
  private static final String FATCOT = "fatcot";
  private static final String PTOEXE = "ptoexe";
  private static final String CODISI = "codisi";
  private static final String DISMES = "dismes";

  private static final Range RANGE_TIPREG = new Range(1, 2);
  private static final Range RANGE_DTPREG = new Range(3, 10);
  private static final Range RANGE_CODBDI = new Range(11, 12);
  private static final Range RANGE_CODNEG = new Range(13, 24);
  private static final Range RANGE_TPMERC = new Range(25, 27);
  private static final Range RANGE_NOMRES = new Range(28, 39);
  private static final Range RANGE_ESPECI = new Range(40, 49);
  private static final Range RANGE_PRAZOT = new Range(50, 52);
  private static final Range RANGE_MODREF = new Range(53, 56);
  private static final Range RANGE_PREABE = new Range(57, 69);
  private static final Range RANGE_PREMAX = new Range(70, 82);
  private static final Range RANGE_PREMIN = new Range(83, 95);
  private static final Range RANGE_PREMED = new Range(96, 108);
  private static final Range RANGE_PREULT = new Range(109, 121);
  private static final Range RANGE_PREOFC = new Range(122, 134);
  private static final Range RANGE_PREOFV = new Range(135, 147);
  private static final Range RANGE_TOTNEG = new Range(148, 152);
  private static final Range RANGE_QUATOT = new Range(153, 170);
  private static final Range RANGE_VOLTOT = new Range(171, 188);
  private static final Range RANGE_PREEXE = new Range(189, 201);
  private static final Range RANGE_INDOPC = new Range(202, 202);
  private static final Range RANGE_DATVEN = new Range(203, 210);
  private static final Range RANGE_FATCOT = new Range(211, 217);
  private static final Range RANGE_PTOEXE = new Range(218, 230);
  private static final Range RANGE_CODISI = new Range(231, 242);
  private static final Range RANGE_DISMES = new Range(243, 245);

  public CotacoesDosPapeisPorDiaLayoutImpl() {
    super();
    this.campos = new String[]{TIPREG,
        DTPREG,
        CODBDI,
        CODNEG,
        TPMERC,
        NOMRES,
        ESPECI,
        PRAZOT,
        MODREF,
        PREABE,
        PREMAX,
        PREMIN,
        PREMED,
        PREULT,
        PREOFC,
        PREOFV,
        TOTNEG,
        QUATOT,
        VOLTOT,
        PREEXE,
        INDOPC,
        DATVEN,
        FATCOT,
        PTOEXE,
        CODISI,
        DISMES};

    this.tamanhos = new Range[]{RANGE_TIPREG,
        RANGE_DTPREG,
        RANGE_CODBDI,
        RANGE_CODNEG,
        RANGE_TPMERC,
        RANGE_NOMRES,
        RANGE_ESPECI,
        RANGE_PRAZOT,
        RANGE_MODREF,
        RANGE_PREABE,
        RANGE_PREMAX,
        RANGE_PREMIN,
        RANGE_PREMED,
        RANGE_PREULT,
        RANGE_PREOFC,
        RANGE_PREOFV,
        RANGE_TOTNEG,
        RANGE_QUATOT,
        RANGE_VOLTOT,
        RANGE_PREEXE,
        RANGE_INDOPC,
        RANGE_DATVEN,
        RANGE_FATCOT,
        RANGE_PTOEXE,
        RANGE_CODISI,
        RANGE_DISMES};

    configurarTiposCampos();
  }

  private void configurarTiposCampos() {
    tipos.put(TIPREG, TiposCamposEnum.NUMERICO);
    tipos.put(DTPREG, TiposCamposEnum.DATA);
    tipos.put(CODBDI, TiposCamposEnum.ALFA);
    tipos.put(CODNEG, TiposCamposEnum.ALFA);
    tipos.put(TPMERC, TiposCamposEnum.NUMERICO);
    tipos.put(NOMRES, TiposCamposEnum.ALFA);
    tipos.put(ESPECI, TiposCamposEnum.ALFA);
    tipos.put(PRAZOT, TiposCamposEnum.ALFA);
    tipos.put(MODREF, TiposCamposEnum.ALFA);
    tipos.put(PREABE, TiposCamposEnum.NUMERICO);
    tipos.put(PREMAX, TiposCamposEnum.NUMERICO);
    tipos.put(PREMIN, TiposCamposEnum.NUMERICO);
    tipos.put(PREMED, TiposCamposEnum.NUMERICO);
    tipos.put(PREULT, TiposCamposEnum.NUMERICO);
    tipos.put(PREOFC, TiposCamposEnum.NUMERICO);
    tipos.put(PREOFV, TiposCamposEnum.NUMERICO);
    tipos.put(TOTNEG, TiposCamposEnum.NUMERICO);
    tipos.put(QUATOT, TiposCamposEnum.NUMERICO);
    tipos.put(VOLTOT, TiposCamposEnum.NUMERICO);
    tipos.put(PREEXE, TiposCamposEnum.NUMERICO);
    tipos.put(INDOPC, TiposCamposEnum.NUMERICO);
    tipos.put(DATVEN, TiposCamposEnum.DATA);
    tipos.put(FATCOT, TiposCamposEnum.NUMERICO);
    tipos.put(PTOEXE, TiposCamposEnum.NUMERICO);
    tipos.put(CODISI, TiposCamposEnum.ALFA);
    tipos.put(DISMES, TiposCamposEnum.NUMERICO);
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
