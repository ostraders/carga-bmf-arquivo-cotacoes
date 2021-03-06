package com.ricardococati.carga.entities.enums;

import lombok.Getter;

public enum OperacaoSplitInplit {

  SPLIT("/"), INPLIT("*");

  @Getter
  private String tipo;

  OperacaoSplitInplit(String tipo) {
    this.tipo = tipo;
  }

}
