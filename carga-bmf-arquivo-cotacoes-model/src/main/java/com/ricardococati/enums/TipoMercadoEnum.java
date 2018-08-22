package com.ricardococati.enums;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

public enum TipoMercadoEnum {

  AVISTA(10L);

  private TipoMercadoEnum(Long cod) {
    this.cod = cod;
  }

  @Getter
  private Long cod;


}
