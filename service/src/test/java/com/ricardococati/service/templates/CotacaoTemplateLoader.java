package com.ricardococati.service.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.ricardococati.model.dto.Cotacao;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CotacaoTemplateLoader implements TemplateLoader {

  public static final String COTACAO_VALID_001 = "COTACAO_VALID_001";
  public static final String COTACAO_VALID_002 = "COTACAO_VALID_002";
  public static final String COTACAO_VALID_003 = "COTACAO_VALID_003";
  public static final String COTACAO_VALID_004 = "COTACAO_VALID_004";
  public static final String COTACAO_VALID_005 = "COTACAO_VALID_005";
  public static final String COTACAO_VALID_006 = "COTACAO_VALID_006";
  public static final String COTACAO_VALID_007 = "COTACAO_VALID_007";
  public static final String COTACAO_VALID_008 = "COTACAO_VALID_008";
  public static final String COTACAO_VALID_009 = "COTACAO_VALID_009";
  public static final String COTACAO_VALID_010 = "COTACAO_VALID_010";
  public static final String COTACAO_VALID_011 = "COTACAO_VALID_011";
  public static final String COTACAO_VALID_012 = "COTACAO_VALID_012";
  public static final String COTACAO_VALID_013 = "COTACAO_VALID_013";
  public static final String COTACAO_VALID_014 = "COTACAO_VALID_014";
  public static final String COTACAO_VALID_015 = "COTACAO_VALID_015";
  public static final String COTACAO_VALID_016 = "COTACAO_VALID_016";
  public static final String COTACAO_VALID_017 = "COTACAO_VALID_017";
  public static final String COTACAO_VALID_018 = "COTACAO_VALID_018";
  public static final String COTACAO_VALID_019 = "COTACAO_VALID_019";
  public static final String COTACAO_VALID_020 = "COTACAO_VALID_020";
  private Double precoVar = 10.1;
  private LocalDate dtpreg = LocalDate.of(1978, 2, 16);

  @Override
  public void load() {
    Fixture.of(Cotacao.class)
        .addTemplate(COTACAO_VALID_001, new Rule() {{
          add("identificacaoArquivo", 2L);
          add("id", "02");
          add("codbdi", "02");
          add("codneg", "MGLU3");
          add("tipoRegistro", 1L);
          add("dtpreg", dtpreg);
          add("tpmerc", 10L);
          add("nomres", "MAGAZ LUIZA");
          add("especi", "ON NM");
          add("prazot", "");
          add("modref", "R$");
          add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
          add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
          add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
          add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
          add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
          add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
          add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
          add("totneg", 1L);
          add("quatot", 10L);
          add("voltot", new BigDecimal(precoVar++));
          add("preexe", new BigDecimal(precoVar++));
          add("indopc", 0L);
          add("datven", LocalDate.of(9999, 12, 31));
          add("fatcot", 1L);
          add("ptoexe", new BigDecimal(precoVar++));
          add("codisi", "BRMGLUACNOR2");
          add("dismes", 114L);
        }})
        .addTemplate(COTACAO_VALID_002).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_003).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_004).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_005).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_006).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_007).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_008).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_009).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_010).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_011).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_012).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_013).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_014).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_015).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_016).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_017).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_018).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_019).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
        .addTemplate(COTACAO_VALID_020).inherits(COTACAO_VALID_001, new Rule() {{
      add("preabe", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premax", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premin", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("premed", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preult", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofc", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
      add("preofv", new BigDecimal(precoVar++).setScale(4, BigDecimal.ROUND_HALF_UP));
    }})
    ;
  }
}