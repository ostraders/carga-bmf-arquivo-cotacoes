package com.ricardococati.carga.adapters.messages.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TopicosDiarioSemanal {

  CANDLESTICK_DIARIO("candlestick-diario"),
  CANDLESTICK_SEMANAL("candlestick-semanal");

  @Getter
  private String topicName;

}
