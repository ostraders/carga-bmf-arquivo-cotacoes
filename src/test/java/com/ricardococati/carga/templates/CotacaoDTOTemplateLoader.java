package com.ricardococati.carga.templates;

import static com.ricardococati.carga.utils.BigDecimalCustomizado.getValueBigDecimalHalfUpArredondado4Casas;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.ricardococati.carga.entities.domains.cotacao.dto.CotacaoDTO;
import java.time.LocalDate;

public class CotacaoDTOTemplateLoader implements TemplateLoader {

  public static final String COTACAO_DTO_VALID_001 = "COTACAO_DTO_VALID_001";
  public static final String COTACAO_DTO_VALID_002 = "COTACAO_DTO_VALID_002";
  public static final String COTACAO_DTO_VALID_003 = "COTACAO_DTO_VALID_003";
  public static final String COTACAO_DTO_VALID_004 = "COTACAO_DTO_VALID_004";
  public static final String COTACAO_DTO_VALID_005 = "COTACAO_DTO_VALID_005";
  public static final String COTACAO_DTO_VALID_006 = "COTACAO_DTO_VALID_006";
  public static final String COTACAO_DTO_VALID_007 = "COTACAO_DTO_VALID_007";
  public static final String COTACAO_DTO_VALID_008 = "COTACAO_DTO_VALID_008";
  public static final String COTACAO_DTO_VALID_009 = "COTACAO_DTO_VALID_009";
  public static final String COTACAO_DTO_VALID_010 = "COTACAO_DTO_VALID_010";
  public static final String COTACAO_DTO_VALID_011 = "COTACAO_DTO_VALID_011";
  public static final String COTACAO_DTO_VALID_012 = "COTACAO_DTO_VALID_012";
  public static final String COTACAO_DTO_VALID_013 = "COTACAO_DTO_VALID_013";
  public static final String COTACAO_DTO_VALID_014 = "COTACAO_DTO_VALID_014";
  public static final String COTACAO_DTO_VALID_015 = "COTACAO_DTO_VALID_015";
  public static final String COTACAO_DTO_VALID_016 = "COTACAO_DTO_VALID_016";
  public static final String COTACAO_DTO_VALID_017 = "COTACAO_DTO_VALID_017";
  public static final String COTACAO_DTO_VALID_018 = "COTACAO_DTO_VALID_018";
  public static final String COTACAO_DTO_VALID_019 = "COTACAO_DTO_VALID_019";
  public static final String COTACAO_DTO_VALID_020 = "COTACAO_DTO_VALID_020";
  public static final String COTACAO_DTO_VALID_021 = "COTACAO_DTO_VALID_021";
  private Double precoVar = 10.1;
  private LocalDate dtpreg = LocalDate.of(1978, 2, 16);

  @Override
  public void load() {
    Fixture.of(CotacaoDTO.class)
        .addTemplate(COTACAO_DTO_VALID_001, new Rule() {{
          add("codbdi", "02");
          add("codneg", "MGLU3");
          add("tipoRegistro", "1");
          add("dtpreg", dtpreg);
          add("tpmerc", 10L);
          add("nomres", "MAGAZ LUIZA");
          add("especi", "ON NM");
          add("prazot", "");
          add("modref", "R$");
          add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
          add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
          add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
          add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
          add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
          add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
          add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
          add("totneg", 1L);
          add("quatot", 10L);
          add("voltot", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
          add("preexe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
          add("indopc", 0L);
          add("datven", LocalDate.of(9999, 12, 31));
          add("fatcot", 1L);
          add("ptoexe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
          add("codisi", "BRMGLUACNOR2");
          add("dismes", 114L);
        }})
        .addTemplate(COTACAO_DTO_VALID_002).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_003).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_004).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_005).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_006).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_007).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_008).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_009).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_010).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_011).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_012).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_013).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_014).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_015).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_016).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_017).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_018).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_019).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
        .addTemplate(COTACAO_DTO_VALID_020).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("preabe", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premax", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premin", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("premed", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preult", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofc", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
      add("preofv", getValueBigDecimalHalfUpArredondado4Casas(precoVar++));
    }})
            .addTemplate(COTACAO_DTO_VALID_021).inherits(COTACAO_DTO_VALID_001, new Rule() {{
      add("dtpreg", dtpreg.plusDays(1));
      add("identificacaoArquivo", 1L);
    }})
    ;
  }
}