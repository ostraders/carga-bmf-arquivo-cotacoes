package com.ricardococati.carga.entities.enums;

import lombok.Getter;

public enum TipoMercadoEnum {

  AVISTA(10L);

  private TipoMercadoEnum(Long cod) {
    this.cod = cod;
  }

  @Getter
  private Long cod;


}
