package com.ricardococati.repository.dao.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.ricardococati.model.entities.CandlestickSemanal;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
          add("preabe", new BigDecimal(9.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(12.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(9.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.1).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(100000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_002)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(2.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(13.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(9.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.1).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(100000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_003)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(3.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(14.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(8.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.2).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(200000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_004)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(4.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(15.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(4.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.3).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(300000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_005)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(6.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(16.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(5.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.4).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(400000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_006)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(8.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(17.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(6.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.5).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(500000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_007)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(9.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(18.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(7.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.6).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(600000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_008)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(11.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(19.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(8.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.7).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(700000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_009)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(12.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(12.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(9.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.8).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(800000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_010)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(11.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(13.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(2.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.9).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(900000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_011)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(10.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(14.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(3.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.2).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(200000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_012)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(11.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(15.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(5.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.3).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(300000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_013)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(6.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(16.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(6.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.4).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(400000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_014)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(11.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(17.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(8.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.5).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(500000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_015)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(6.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(17.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(2.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.6).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(600000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_016)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(10.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(18.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(3.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.7).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(700000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_017)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(10.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(19.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(4.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.8).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(800000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_018)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(11.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(19.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(5.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.9).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(900000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_019)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(12.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(14.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(6.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.2).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(200000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
        .addTemplate(CANDLESTICK_SEMANAL_VALID_020)
        .inherits(CANDLESTICK_SEMANAL_VALID_001, new Rule() {{
          add("idCandleSemanal", random(Long.class, range(1L, 200L)));
          add("dtpregini", dtpreg.plusDays(count++));
          add("dtpregfim", dtpreg.plusDays(count++));
          add("codneg", "MGLU3");
          add("preabe", new BigDecimal(13.1).setScale(4, RoundingMode.HALF_UP));
          add("premax", new BigDecimal(15.1).setScale(4, RoundingMode.HALF_UP));
          add("premin", new BigDecimal(7.0).setScale(4, RoundingMode.HALF_UP));
          add("preult", new BigDecimal(11.3).setScale(4, RoundingMode.HALF_UP));
          add("voltot", new BigDecimal(400000.0).setScale(4, RoundingMode.HALF_UP));
          add("semana", value++);
        }})
    ;
  }
}