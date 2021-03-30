package com.ricardococati.carga.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Setor {

  PETROLEO_GAS_BIOCOMBUSTIVEIS(1,	"Petróleo, Gás e Biocombustíveis"),
  MATERIAIS_BASICOS(2,	"Materiais Básicos"),
  BENS_INDUSTRIAIS(3,	"Bens Industriais"),
  CONSUMO_NAO_CICLICO(4,	"Consumo não Cíclico"),
  CONSUMO_CICLICO(5,	"Consumo Cíclico"),
  SAUDE(6,	"Saúde"),
  TECNOLOGIA_DA_INFORMACAO(7,	"Tecnologia da Informação"),
  COMUNICACOES(8,	"Comunicações"),
  UTILIDADE_PUBLICA(9,	"Utilidade Pública"),
  FINANCEIRO(10,	"Financeiro"),
  OUTROS(11,	"Outros");

  @Getter
  private Integer idSetor;
  @Getter
  private String descricaoSetor;

}
