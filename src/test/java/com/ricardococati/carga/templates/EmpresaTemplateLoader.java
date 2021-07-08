package com.ricardococati.carga.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.ricardococati.carga.entities.domains.empresa.Empresa;
import com.ricardococati.carga.entities.enums.Segmento;
import com.ricardococati.carga.entities.enums.Setor;
import com.ricardococati.carga.entities.enums.SubSetor;

public class EmpresaTemplateLoader implements TemplateLoader {

  public static final String EMPRESA_VALID_001 = "EMPRESA_VALID_001";
  public static final String EMPRESA_VALID_002 = "EMPRESA_VALID_002";
  public static final String EMPRESA_VALID_003 = "EMPRESA_VALID_003";
  public static final String EMPRESA_VALID_004 = "EMPRESA_VALID_004";
  public static final String EMPRESA_VALID_005 = "EMPRESA_VALID_005";
  public static final String EMPRESA_VALID_006 = "EMPRESA_VALID_006";
  public static final String EMPRESA_VALID_007 = "EMPRESA_VALID_007";

  @Override
  public void load() {
    Fixture.of(Empresa.class)
        .addTemplate(EMPRESA_VALID_001, new Rule() {{
          add("nomeEmpresa", "MAGAZINE LUIZA");
          add("idEmpresa", 1L);
          add("setor", Setor.CONSUMO_CICLICO);
          add("subSetor", SubSetor.COMERCIO);
          add("segmento", Segmento.ELETRODOMESTICOS);
        }})
        .addTemplate(EMPRESA_VALID_002, new Rule() {{
          add("nomeEmpresa", "PETROBRAS");
          add("idEmpresa", 2L);
          add("setor", Setor.PETROLEO_GAS_BIOCOMBUSTIVEIS);
          add("subSetor", SubSetor.PETROLEO_GAS_BIOCOMBUSTIVEIS);
          add("segmento", Segmento.EXPLORACAO_REFINO_DISTRIBUICAO);
        }})
        .addTemplate(EMPRESA_VALID_003, new Rule() {{
          add("nomeEmpresa", "VALE");
          add("idEmpresa", 3L);
          add("setor", Setor.MATERIAIS_BASICOS);
          add("subSetor", SubSetor.MINERACAO);
          add("segmento", Segmento.MINERAIS_METALICOS);
        }})
        .addTemplate(EMPRESA_VALID_004, new Rule() {{
          add("nomeEmpresa", "EMBRAER");
          add("idEmpresa", 4L);
          add("setor", Setor.BENS_INDUSTRIAIS);
          add("subSetor", SubSetor.MATERIAL_TRANSPORTE);
          add("segmento", Segmento.MATERIAL_AERONAUTICO_DEFESA);
        }})
        .addTemplate(EMPRESA_VALID_005, new Rule() {{
          add("nomeEmpresa", "NEOGRID");
          add("idEmpresa", 5L);
          add("setor", Setor.TECNOLOGIA_DA_INFORMACAO);
          add("subSetor", SubSetor.PROGRAMAS_SERVICOS);
          add("segmento", Segmento.PROGRAMAS_SERVICOS);
        }})
        .addTemplate(EMPRESA_VALID_006, new Rule() {{
          add("nomeEmpresa", "ITAUUNIBANCO");
          add("idEmpresa", 6L);
          add("setor", Setor.FINANCEIRO);
          add("subSetor", SubSetor.INTERMEDIARIOS_FINANCEIROS);
          add("segmento", Segmento.BANCOS);
        }})
        .addTemplate(EMPRESA_VALID_007, new Rule() {{
          add("nomeEmpresa", "SABESP");
          add("idEmpresa", 7L);
          add("setor", Setor.UTILIDADE_PUBLICA);
          add("subSetor", SubSetor.AGUA_SANEAMENTO);
          add("segmento", Segmento.AGUA_SANEAMENTO);
        }})
    ;
  }
}