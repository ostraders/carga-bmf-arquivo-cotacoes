package com.ricardococati.enums;

import lombok.Getter;

public enum DataBaseInfosEnum {

  SCHEMA("DBBMF");

  @Getter
  private String texto;

  private DataBaseInfosEnum(String texto) {
    this.texto = texto;
  }

}