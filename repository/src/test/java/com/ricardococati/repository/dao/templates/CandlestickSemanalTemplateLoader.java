package com.ricardococati.repository.dao.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.ricardococati.model.entities.CandlestickSemanal;
import com.ricardococati.repository.util.BigDecimalCustomizado;
import java.time.LocalDate;

public class CandlestickSemanalTemplateLoader implements TemplateLoader {

  public static final String CANDLESTICK_SEMANAL_VALID_001 = "CANDLESTICK_SEMANAL_VALID_001";
  public static final String CANDLESTICK_SEMANAL_VALID_002 = "CANDLESTICK_SEMANAL_VALID_002";
  public static final String CANDLESTICK_SEMANAL_VALID_003 = "CANDLESTICK_SEMANAL_VALID_003";
  public static final String CANDLESTICK_SEMANAL_VALID_004 = "CANDLESTICK_SEMANAL_VALID_004";
  public static final String CANDLESTICK_SEMANAL_VALID_005 = "CANDLESTICK_SEMANAL_VALID_005";
  public static final String CANDLESTICK_SEMANAL_VALID_006 = "CANDLESTICK_SEMANAL_VALID_006";
  public static final String CANDLESTICK_SEMANAL_VALID_007 = "CANDLESTICK_SEMANAL_VALID_007";
  public static final String CANDLESTICK_SEMANAL_VALID_008 = "CANDLESTICK_SEMANAL_VALID_008";
  public static final String CANDLESTICK_SEMANAL_VALID_009 = "CANDLESTICK_SEMANAL_VALID_009";
  public static final String CANDLESTICK_SEMANAL_VALID_010 = "CANDLESTICK_SEMANAL_VALID_010";
  public static final String CANDLESTICK_SEMANAL_VALID_011 = "CANDLESTICK_SEMANAL_VALID_011";
  public static final String CANDLESTICK_SEMANAL_VALID_012 = "CANDLESTICK_SEMANAL_VALID_012";
  public static final String CANDLESTICK_SEMANAL_VALID_013 = "CANDLESTICK_SEMANAL_VALID_013";
  public static final String CANDLESTICK_SEMANAL_VALID_014 = "CANDLESTICK_SEMANAL_VALID_014";
  public static final String CANDLESTICK_SEMANAL_VALID_015 = "CANDLESTICK_SEMANAL_VALID_015";
  public static final String CANDLESTICK_SEMANAL_VALID_016 = "CANDLESTICK_SEMANAL_VALID_016";
  public static final String CANDLESTICK_SEMANAL_VALID_017 = "CANDLESTICK_SEMANAL_VALID_017";
  public static final String CANDLESTICK_SEMANAL_VALID_018 = "CANDLESTICK_SEMANAL_VALID_018";
  public static final String CANDLESTICK_SEMANAL_VALID_019 = "CANDLESTICK_SEMANAL_VALID_019";
  public static final String CANDLESTICK_SEMANAL_VALID_020 = "CANDLESTICK_SEMANAL_VALID_020";
  private LocalDate dtpreg = LocalDate.of(1978, 2, 16);
  private int value = 1;
  private int count = 1;

  @Override
  public void load() {
    Fixture.of(CandlestickSemanal.class)
        .addTemplate(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(9.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(12.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(9.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.1));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(100000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_002)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(2.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(13.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(9.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.1));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(100000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_003)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(3.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(14.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(8.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.2));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(200000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_004)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(4.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(15.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(4.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.3));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(300000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_005)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(6.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(16.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(5.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.4));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(400000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_006)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(8.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(17.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(6.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.5));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(500000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_007)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(9.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(18.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(7.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.6));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(600000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_008)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(19.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(8.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.7));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(700000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_009)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(12.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(12.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(9.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.8));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(800000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_010)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(13.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(2.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.9));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(900000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_011)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(10.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(14.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(3.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.2));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(200000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_012)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(15.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(5.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.3));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(300000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_013)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(6.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(16.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(6.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.4));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(400000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_014)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(17.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(8.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.5));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(500000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_015)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(6.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(17.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(2.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.6));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(600000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_016)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(10.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(18.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(3.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.7));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(700000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_017)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(10.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(19.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(4.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.8));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(800000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_018)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(19.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(5.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.9));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(900000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_019)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(12.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(14.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(6.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.2));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(200000.0));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_020)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(13.1));
          add("premax", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(15.1));
          add("premin", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(7.0));
          add("preult", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(11.3));
          add("voltot", BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas(400000.0));
          add("semana", value++);
        }})
    ;
  }
}