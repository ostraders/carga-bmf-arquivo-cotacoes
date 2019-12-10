package com.ricardococati.model.enums;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

public enum CodbdiEnum {

  LOTE_PADRAO("02"),
  SANCIONADAS("05"),
  CONCORDATARIAS("06"),
  EXTRAJUDICIAL("07"),
  JUDICIAL("08"),
  RAET("09"),
  DIREITOS_E_RECIBOS("10");

  private CodbdiEnum(String cod) {
    this.cod = cod;
  }

  @Getter
  private String cod;

  public static List<String> getListCodbdi() {
    return Arrays.asList(
        LOTE_PADRAO.getCod(),
        SANCIONADAS.getCod(),
        CONCORDATARIAS.getCod(),
        EXTRAJUDICIAL.getCod(),
        JUDICIAL.getCod(),
        RAET.getCod(),
        DIREITOS_E_RECIBOS.getCod()
    );
  }

}
