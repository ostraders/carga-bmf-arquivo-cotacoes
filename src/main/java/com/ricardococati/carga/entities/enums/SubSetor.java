package com.ricardococati.carga.entities.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SubSetor {

  PETROLEO_GAS_BIOCOMBUSTIVEIS(1, "Petróleo, Gás e Biocombustíveis"),
  MINERACAO(2, "Mineração"),
  SIDERURGIA_METALURGIA(3, "Siderurgia e Metalurgia"),
  QUIMICOS(4, "Químicos"),
  MADEIRA_PAPEL(5, "Madeira e Papel"),
  EMBALAGENS(6, "Embalagens"),
  MATERIAIS_DIVERSOS(7, "Materiais Diversos"),
  CONSTRUCAO_ENGENHARIA(8, "Construção e Engenharia"),
  MATERIAL_TRANSPORTE(9, "Material de Transporte"),
  MAQUINAS_EQUIPAMENTOS(10, "Máquinas e Equipamentos"),
  TRANSPORTE(11, "Transporte"),
  SERVICOS_DIVERSOS(12, "Serviços Diversos"),
  COMERCIO(13, "Comércio"),
  AGROPECUARIA(14, "Agropecuária"),
  ALIMENTOS_PROCESSADOS(15, "Alimentos Processados"),
  BEBIDAS(16, "Bebidas"),
  PRODUTOS_USO_PESSOAL_DE_LIMPEZA(17, "Produtos de Uso Pessoal e de Limpeza"),
  COMERCIO_DISTRIBUICAO(18, "Comércio e Distribuição"),
  CONSTRUCAO_CIVIL(19, "Construção Civil"),
  TECIDOS_VESTUARIO_CALCADOS(20, "Tecidos, Vestuário e Calçados"),
  UTILIDADES_DOMESTICAS(21, "Utilidades Domésticas"),
  AUTOMOVEIS_MOTOCICLETAS(22, "Automóveis e Motocicletas"),
  HOTEIS_RESTAURANTES(23, "Hoteis e Restaurantes"),
  VIAGENS_LAZER(24, "Viagens e Lazer"),
  DIVERSOS(25, "Diversos"),
  MEDICAMENTOS_OUTROS_PRODUTOS(26, "Medicamentos e Outros Produtos"),
  SERVICOS_MEDICO_HOSPITALARES(27, "Serviços Médico - Hospitalares"),
  ANALISES_DIAGNOSTICOS(28, "Análises e Diagnósticos"),
  EQUIPAMENTOS(29, "Equipamentos"),
  COMPUTADORES_EQUIPAMENTOS(30, "Computadores e Equipamentos"),
  PROGRAMAS_SERVICOS(31, "Programas e Serviços"),
  TELECOMUNICACOES(32, "Telecomunicações"),
  MIDIA(33, "Mídia"),
  ENERGIA_ELETRICA(34, "Energia Elétrica"),
  AGUA_SANEAMENTO(35, "Água e Saneamento"),
  GAS(36, "Gás"),
  INTERMEDIARIOS_FINANCEIROS(37, "Intermediários Financeiros"),
  SECURITIZADORAS_RECEBIVEIS(38, "Securitizadoras de Recebíveis"),
  SERVICOS_FINANCEIROS_DIVERSOS(39, "Serviços Financeiros Diversos"),
  PREVIDENCIA_SEGUROS(40, "Previdência e Seguros"),
  EXPLORACAO_IMOVEIS(41, "Exploração de Imóveis"),
  HOLDINGS_DIVERSIFICADAS(42, "Holdings Diversificadas"),
  OUTROS_TITULOS(43, "Outros Títulos"),
  OUTROS(44, "Outros");

  @Getter
  private Integer idSubSetor;
  @Getter
  private String descricaoSubSetor;

  public static SubSetor findValueById(final Integer idSubSetor) throws RuntimeException {
    return Arrays
        .stream(SubSetor.values())
        .filter(v -> v.getIdSubSetor().equals(idSubSetor))
        .findFirst()
        .orElseThrow(() ->
            new RuntimeException(String.format("Id do SubSetor Desconhecido: '%s'", idSubSetor))
        );
  }

}
