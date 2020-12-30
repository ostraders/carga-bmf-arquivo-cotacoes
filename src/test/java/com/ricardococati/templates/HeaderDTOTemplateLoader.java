package com.ricardococati.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.ricardococati.carga.entities.dto.HeaderDTO;
import java.time.LocalDate;

public class HeaderDTOTemplateLoader implements TemplateLoader {

  public static final String HEADER_DTO_VALID_001 = "HEADER_DTO_VALID_001";
  public static final String HEADER_DTO_VALID_002 = "HEADER_DTO_VALID_002";
  public static final String HEADER_DTO_VALID_003 = "HEADER_DTO_VALID_003";
  public static final String HEADER_DTO_VALID_004 = "HEADER_DTO_VALID_004";
  public static final String HEADER_DTO_VALID_005 = "HEADER_DTO_VALID_005";
  public static final String HEADER_DTO_VALID_006 = "HEADER_DTO_VALID_006";
  public static final String HEADER_DTO_VALID_007 = "HEADER_DTO_VALID_007";
  public static final String HEADER_DTO_VALID_008 = "HEADER_DTO_VALID_008";
  public static final String HEADER_DTO_VALID_009 = "HEADER_DTO_VALID_009";
  public static final String HEADER_DTO_VALID_010 = "HEADER_DTO_VALID_010";
  public static final String HEADER_DTO_VALID_011 = "HEADER_DTO_VALID_011";
  public static final String HEADER_DTO_VALID_012 = "HEADER_DTO_VALID_012";
  public static final String HEADER_DTO_VALID_013 = "HEADER_DTO_VALID_013";
  public static final String HEADER_DTO_VALID_014 = "HEADER_DTO_VALID_014";
  public static final String HEADER_DTO_VALID_015 = "HEADER_DTO_VALID_015";
  public static final String HEADER_DTO_VALID_016 = "HEADER_DTO_VALID_016";
  public static final String HEADER_DTO_VALID_017 = "HEADER_DTO_VALID_017";
  public static final String HEADER_DTO_VALID_018 = "HEADER_DTO_VALID_018";
  public static final String HEADER_DTO_VALID_019 = "HEADER_DTO_VALID_019";
  public static final String HEADER_DTO_VALID_020 = "HEADER_DTO_VALID_020";
  private LocalDate dataDaGeracaoDoArquivo = LocalDate.of(2000, 2, 16);
  private Integer ano = 2000;

  @Override
  public void load() {
    Fixture.of(HeaderDTO.class)
        .addTemplate(HEADER_DTO_VALID_001, new Rule() {{
          add("codigoDaOrigem", "BOVESPA");
          add("nomeDoArquivo", "COTAHIST." + ano++);
          add("tipoRegistro", "0");
          add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
        }})
        .addTemplate(HEADER_DTO_VALID_002).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_003).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_004).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_005).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_006).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_007).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_008).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_009).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_010).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_011).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_012).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_013).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_014).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_015).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_016).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_017).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_018).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_019).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
        .addTemplate(HEADER_DTO_VALID_020).inherits(HEADER_DTO_VALID_001, new Rule() {{
      add("codigoDaOrigem", "BOVESPA");
      add("nomeDoArquivo", "COTAHIST." + ano++);
      add("tipoRegistro", "0");
      add("dataDaGeracaoDoArquivo", dataDaGeracaoDoArquivo.plusDays(1));
    }})
    ;
  }
}