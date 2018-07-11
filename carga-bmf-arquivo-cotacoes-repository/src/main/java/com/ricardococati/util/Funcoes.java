package com.ricardococati.util;

import static java.util.Objects.isNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Slf4j
@SuppressWarnings("finally")
@Component
public class Funcoes {

  public static String insereZerosEsquerda(String pValue, int pLength) {
    for (int i = pValue.length(); i < pLength; i++) {
      pValue = "0" + pValue;
    }
    return pValue;
  }

  public static String removeZerosEsquerda(String linha) {
    return linha.replaceFirst("0*", "");
  }

  /**
   * Identifica se uma determinada string é numérica.
   *
   * @return boolean
   */
  public static boolean isNumber(String valor) {
    try {
      Long.parseLong(valor);
      return true;
    } catch (NumberFormatException | NullPointerException e) {
      return false;
    }
  }

  public static StringBuilder getSqlString(InputStream streamSqlFile) {
    StringBuilder builder = null;

    if (streamSqlFile != null) {
      int pos = 0;
      char[] bytesLidos = new char[2 * 1024];

      try (InputStream _streamSqlFile = streamSqlFile;
          InputStreamReader inpRed = new InputStreamReader(_streamSqlFile);) {
        builder = new StringBuilder();
        while ((pos = inpRed.read(bytesLidos)) != -1) {
          builder.append(bytesLidos, 0, pos);
        }
      } catch (IOException e) {
        log.error("Erro: ", e.getMessage(), e);
      }
    }

    return builder;
  }

  public static Long formataDataRelatorio(Date data) {
    SimpleDateFormat formatDataRel = new SimpleDateFormat("yyyyMMdd");
    String dtFormatada = formatDataRel.format(data);
    return Long.parseLong(dtFormatada);
  }

  public static Date formatarDateUtil(String dataStr) {
    SimpleDateFormat formatDataContinua = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat formatComBarra = new SimpleDateFormat("dd/MM/yy");
    Date data = null;
    Date dataConvertida;
    try {
      dataConvertida = formatDataContinua.parse(dataStr);
      java.sql.Date dataFormatoSQL = new java.sql.Date(dataConvertida.getTime());
      String dataStrRetorno = formatComBarra.format(dataFormatoSQL);
      data = formatComBarra.parse(dataStrRetorno);
    } catch (ParseException e) {
      log.error("Erro: ", e.getMessage(), e);
    } finally {
      return data;
    }
  }

  public static LocalDate convertStringToLocalDate(String source) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate retorno = null;
    if(!isNull(source) && !source.isEmpty()){
      retorno = LocalDate.parse(validFormat(source), dtf);
    }
    return retorno;
  }

  public static String validFormat(String strDate) {
    if (strDate.length() > 8) {
      return strDate.substring(0, 8);
    }
    return strDate;
  }

  public static Date formatarDateReaderDate(String dataStr) {
    SimpleDateFormat formatDataContinua = new SimpleDateFormat("ddMMyyyy");
    SimpleDateFormat formatComTraco = new SimpleDateFormat("yyyy-MM-dd");
    Date data = null;
    Date dataConvertida;
    try {
      dataConvertida = formatDataContinua.parse(dataStr);
      java.sql.Date dataFormatoSQL = new java.sql.Date(dataConvertida.getTime());
      String dataStrRetorno = formatComTraco.format(dataFormatoSQL);
      data = formatComTraco.parse(dataStrRetorno);
    } catch (ParseException e) {
      log.error("Erro: ", e.getMessage(), e);
    } finally {
      return data;
    }
  }

  public static Date formatarDateUtil2(String date) {
    Long valida = new Long(date);
    if (isNullOrZero(valida)) {
      return null;
    }
    SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
    Date data = null;
    Date parsed;
    try {
      parsed = format.parse(date);
      java.sql.Date sql = new java.sql.Date(parsed.getTime());
      String t = f.format(sql);
      data = f.parse(t);
    } catch (ParseException e) {
      log.error("Erro: ", e.getMessage(), e);
    } finally {
      return data;
    }
  }

  /**
   * Recebe a long no formato yyyyMMdd e faz o parse para data
   *
   * @return o parse para data do formato acima
   */
  public static Date formataDataRelatorioStr2(String data) {
    SimpleDateFormat formatDataRel = new SimpleDateFormat("dd/MM/yyyy");
    Date dtFormatada = null;
    try {
      dtFormatada = formatDataRel.parse(String.valueOf(data));
    } catch (ParseException e) {
    } finally {
      return dtFormatada;
    }
  }

  public static java.util.Date formataData(String data) {
    SimpleDateFormat formatDataRel = new SimpleDateFormat("ddMMyy");
    Date dtFormatada = null;
    try {
      dtFormatada = formatDataRel.parse(String.valueOf(data));
    } catch (ParseException e) {
    } finally {
      return dtFormatada;
    }
  }

  /**
   * Trata sinal do valor
   *
   * @param bigDecimal , String
   * @return bigDecimal
   */
  public static BigDecimal tratarSinalValor(BigDecimal bigDecimal, String strSinal) {

    BigDecimal multValor;
    BigDecimal retValor = null;

    if (bigDecimal != null) {
      if (strSinal.equals("-")) {
        multValor = new BigDecimal(-1);
      } else {
        multValor = new BigDecimal(1);
      }

      retValor = bigDecimal.multiply(multValor);
    } else {
      retValor = bigDecimal;
    }

    return retValor;
  }

  /**
   * Divide um bigDecimal por cem.
   *
   * @return bigDecimal
   */
  public static BigDecimal dividePorCem(BigDecimal bigDecimal) {
    if (bigDecimal != null) {
      bigDecimal = bigDecimal.divide(new BigDecimal(100D));
    }
    return bigDecimal;
  }

  public static BigDecimal dividePorCemMil(BigDecimal bigDecimal) {
    if (bigDecimal != null) {
      bigDecimal = bigDecimal.divide(new BigDecimal(100000D));
    }
    return bigDecimal;
  }

  /**
   * Divide um bigDecimal por cem.
   *
   * @return bigDecimal
   */
  public static BigDecimal dividePorCemNegativo(BigDecimal bigDecimal) {
    if (bigDecimal != null) {
      bigDecimal = bigDecimal.divide(new BigDecimal(100D)).multiply(new BigDecimal(-1D));
    }
    return bigDecimal;
  }

  /**
   * Divide um double por cem.
   *
   * @return bigDecimal
   */
  public static Double dividePorCem(Double d) {
    if (d != null) {
      d = d / 100D;
    }
    return d;
  }


  /***
   *  Verifica se a String Ã© null ou vazia ou sÃ³ tem espaÃ§os em branco
   * @param s
   * @return
   */
  public static boolean isNullOrZero(Long s) {
    return (s == null || s == 0);
  }

  /**
   * Recebe a data e retorna um long da data no formato yyyyMMdd
   *
   * @return data em milissegundos
   */
  public static String formataDataPesquisaDataProcessamento(Date data) {
    SimpleDateFormat formatDataRel = new SimpleDateFormat("dd/MM/yyyy");
    String dtFormatada = formatDataRel.format(data);
    return dtFormatada;
  }

  public static Long trataValorNulo(String nro) {
    Long retorno = 0L;
    if (!"".equals(nro)) {
      retorno = Long.parseLong(nro);
    }
    return retorno;
  }

}
