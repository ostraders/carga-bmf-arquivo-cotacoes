package com.ricardococati.carga.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;
import com.ricardococati.carga.entities.enums.Segmento;
import com.ricardococati.carga.entities.enums.Setor;
import com.ricardococati.carga.entities.enums.SubSetor;

public class EmpresaDTOTemplateLoader implements TemplateLoader {

  public static final String EMPRESA_DTO_VALID_001 = "EMPRESA_DTO_VALID_001";
  public static final String EMPRESA_DTO_VALID_002 = "EMPRESA_DTO_VALID_002";
  public static final String EMPRESA_DTO_VALID_003 = "EMPRESA_DTO_VALID_003";
  public static final String EMPRESA_DTO_VALID_004 = "EMPRESA_DTO_VALID_004";
  public static final String EMPRESA_DTO_VALID_005 = "EMPRESA_DTO_VALID_005";
  public static final String EMPRESA_DTO_VALID_006 = "EMPRESA_DTO_VALID_006";
  public static final String EMPRESA_DTO_VALID_007 = "EMPRESA_DTO_VALID_007";

  @Override
  public void load() {
    Fixture.of(EmpresaDTO.class)
        .addTemplate(EMPRESA_DTO_VALID_001, new Rule() {{
          add("nomeEmpresa", "MAGAZINE LUIZA");
          add("setor", Setor.CONSUMO_CICLICO);
          add("subSetor", SubSetor.COMERCIO);
          add("segmento", Segmento.ELETRODOMESTICOS);
          add("ativo", "MGLU");
        }})
        .addTemplate(EMPRESA_DTO_VALID_002, new Rule() {{
          add("nomeEmpresa", "PETROBRAS");
          add("setor", Setor.PETROLEO_GAS_BIOCOMBUSTIVEIS);
          add("subSetor", SubSetor.PETROLEO_GAS_BIOCOMBUSTIVEIS);
          add("segmento", Segmento.EXPLORACAO_REFINO_DISTRIBUICAO);
          add("ativo", "PETR");
        }})
        .addTemplate(EMPRESA_DTO_VALID_003, new Rule() {{
          add("nomeEmpresa", "VALE");
          add("setor", Setor.MATERIAIS_BASICOS);
          add("subSetor", SubSetor.MINERACAO);
          add("segmento", Segmento.MINERAIS_METALICOS);
          add("ativo", "VALE");
        }})
        .addTemplate(EMPRESA_DTO_VALID_004, new Rule() {{
          add("nomeEmpresa", "EMBRAER");
          add("setor", Setor.BENS_INDUSTRIAIS);
          add("subSetor", SubSetor.MATERIAL_TRANSPORTE);
          add("segmento", Segmento.MATERIAL_AERONAUTICO_DEFESA);
          add("ativo", "EMBR");
        }})
        .addTemplate(EMPRESA_DTO_VALID_005, new Rule() {{
          add("nomeEmpresa", "NEOGRID");
          add("setor", Setor.TECNOLOGIA_DA_INFORMACAO);
          add("subSetor", SubSetor.PROGRAMAS_SERVICOS);
          add("segmento", Segmento.PROGRAMAS_SERVICOS);
          add("ativo", "NGRD");
        }})
        .addTemplate(EMPRESA_DTO_VALID_006, new Rule() {{
          add("nomeEmpresa", "ITAUUNIBANCO");
          add("setor", Setor.FINANCEIRO);
          add("subSetor", SubSetor.INTERMEDIARIOS_FINANCEIROS);
          add("segmento", Segmento.BANCOS);
          add("ativo", "ITUB");
        }})
        .addTemplate(EMPRESA_DTO_VALID_007, new Rule() {{
          add("nomeEmpresa", "SABESP");
          add("setor", Setor.UTILIDADE_PUBLICA);
          add("subSetor", SubSetor.AGUA_SANEAMENTO);
          add("segmento", Segmento.AGUA_SANEAMENTO);
          add("ativo", "SBSP");
        }})
    ;
  }
}