package com.ricardococati.carga.entities.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Segmento {

  EXPLORACAO_REFINO_DISTRIBUICAO(1,  "Exploração, Refino e Distribuição"),
  EQUIPAMENTOS_SERVICOS(2,  "Equipamentos e Serviços"),
  MINERAIS_METALICOS(3,  "Minerais Metálicos"),
  SIDERURGIA(4,  "Siderurgia"),
  ARTEFATOS_FERRO_ACO(5,  "Artefatos de Ferro e Aço"),
  ARTEFATOS_COBRE(6,  "Artefatos de Cobre"),
  PETROQUOMICOS(7,  "Petroquímicos"),
  FERTILIZANTES_DEFENSIVOS(8,  "Fertilizantes e Defensivos"),
  QUIMICOS_DIVERSOS(9,  "Químicos Diversos"),
  MADEIRA(10, "Madeira"),
  PAPEL_CELULOSE(11, "Papel e Celulose"),
  EMBALAGENS(12, "Embalagens"),
  MATERIAIS_DIVERSOS(13, "Materiais Diversos"),
  PRODUTOS_PARA_CONSTRUCAO(14, "Produtos para Construção"),
  CONSTRUCAO_PESADA(15, "Construção Pesada"),
  ENGENHARIA_CONSULTIVA(16, "Engenharia Consultiva"),
  SERVICOS_DIVERSOS(17, "Serviços Diversos"),
  MATERIAL_AERONAUTICO_DEFESA(18, "Material Aeronáutico e de Defesa"),
  MATERIAL_RODOVIARIO(19, "Material Rodoviário"),
  MOTORES_COMPRESSORES_OUTROS(20, "Motores, Compressores e Outros"),
  MAQ_EQUIP_INDUSTRIAIS(21, "Máq. e Equip. Industriais"),
  MAQ_EQUIP_CONSTRUCAO_AGRICOLAS(22, "Máq. e Equip. Construção e Agrícolas"),
  ARMAS_MUNICOES(23, "Armas e Munições"),
  TRANSPORTE_AEREO(24, "Transporte Aéreo"),
  TRANSPORTE_FERROVIARIO(25, "Transporte Ferroviário"),
  TRANSPORTE_HIDROVIARIO(26, "Transporte Hidroviário"),
  TRANSPORTE_RODOVIARIO(27, "Transporte Rodoviário"),
  EXPLORACAO_RODOVIAS(28, "Exploração de Rodovias"),
  SERVICOS_APOIO_ARMAZENAGEM(29, "Serviços de Apoio e Armazenagem"),
  MATERIAL_TRANSPORTE(30, "Material de Transporte"),
  AGRICULTURA(31, "Agricultura"),
  ACUCAR_ALCOOL(32, "Açucar e Alcool"),
  CARNES_DERIVADOS(33, "Carnes e Derivados"),
  ALIMENTOS_DIVERSOS(34, "Alimentos Diversos"),
  CERVEJAS_REFRIGERANTES(35, "Cervejas e Refrigerantes"),
  PRODUTOS_USO_PESSOAL(36, "Produtos de Uso Pessoal"),
  PRODUTOS_LIMPEZA(37, "Produtos de Limpeza"),
  ALIMENTOS(38, "Alimentos"),
  INCORPORACOES(39, "Incorporações"),
  FIOS_TECIDOS(40, "Fios e Tecidos"),
  VESTUARIO(41, "Vestuário"),
  CALCADOS(42, "Calçados"),
  ACESSORIOS(43, "Acessórios"),
  ELETRODOMESTICOS(44, "Eletrodomésticos"),
  MOVEIS(45, "Móveis"),
  UTENSILIOS_DOMESTICOS(46, "Utensílios Domésticos"),
  AUTOMOVEIS_MOTOCICLETAS(47, "Automóveis e Motocicletas"),
  HOTELARIA(48, "Hotelaria"),
  RESTAURANTE_SIMILARES(49, "Restaurante e Similares"),
  BICICLETAS(50, "Bicicletas"),
  BRINQUEDOS_JOGOS(51, "Brinquedos e Jogos"),
  PRODUCAO_EVENTOS_SHOWS(52, "Produção de Eventos e Shows"),
  VIAGENS_TURISMO(53, "Viagens e Turismo"),
  ATIVIDADES_ESPORTIVAS(54, "Atividades Esportivas"),
  SERVICOS_EDUCACIONAIS(55, "Serviços Educacionais"),
  ALUGUEL_CARROS(56, "Aluguel de carros"),
  PROGRAMAS_FIDELIZACAO(57, "Programas de Fidelização"),
  TECIDOS_VESTUARIO_CALCADOS(58, "Tecidos, Vestuário e Calçados"),
  PRODUTOS_DIVERSOS(59, "Produtos Diversos"),
  MEDICAMENTOS_OUTROS_PRODUTOS(60, "Medicamentos e Outros Produtos"),
  SERVICOS_MEDICO_HOSPITALARES(61, "Serviços Médico - Hospitalares,"),
  ANALISES_DIAGNOSTICOS(62, "Análises e Diagnósticos"),
  EQUIPAMENTOS(63, "Equipamentos"),
  COMPUTADORES_EQUIPAMENTOS(64, "Computadores e Equipamentos"),
  PROGRAMAS_SERVICOS(65, "Programas e Serviços"),
  TELECOMUNICACOES(66, "Telecomunicações"),
  PRODUCAO_DIFUSAO_FILMES_PROGRAMAS(67, "Produção e Difusão de Filmes e Programas"),
  PUBLICIDADE_PROPAGANDA(68, "Publicidade e Propaganda"),
  ENERGIA_ELETRICA(69, "Energia Elétrica"),
  AGUA_SANEAMENTO(70, "Agua e Saneamento"),
  GAS(71, "Gás"),
  BANCOS(72, "Bancos"),
  SOC_CREDITO_FINANCIAMENTO(73, "Soc. Crédito e Financiamento"),
  SOC_ARRENDAMENTO_MERCANTIL(74, "Soc. Arrendamento Mercantil"),
  SECURITIZADORAS_RECEBOVEIS(75, "Securitizadoras de Recebíveis"),
  GESTAO_RECURSOS_INVESTIMENTOS(76, "Gestão de Recursos e Investimentos"),
  SERVICOS_FINANCEIROS_DIVERSOS(77, "Serviços Financeiros Diversos"),
  SEGURADORAS(78, "Seguradoras"),
  CORRETORAS_SEGUROS(79, "Corretoras de Seguros"),
  EXPLORACAO_IMOVEIS(80, "Exploração de Imóveis"),
  INTERMEDIACAO_IMOBILIARIA(81, "Intermediação Imobiliária"),
  HOLDINGS_DIVERSIFICADAS(82, "Holdings Diversificadas"),
  OUTROS_TOTULOS(83, "Outros Títulos"),
  OUTROS(84, "Outros");

  @Getter
  private Integer idSegmento;
  @Getter
  private String descricaoSegmento;

  public static Segmento findValueById(final Integer idSegmento) throws RuntimeException {
    return Arrays
        .stream(Segmento.values())
        .filter(v -> v.getIdSegmento().equals(idSegmento))
        .findFirst()
        .orElseThrow(() ->
          new RuntimeException(String.format("Id do Segmento Desconhecido: '%s'", idSegmento))
        );
  }

}
