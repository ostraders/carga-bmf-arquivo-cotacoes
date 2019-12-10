package com.ricardococati.kafka.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TopicEnum {

  CANDLESTICK_DIARIO("candlestick-diario"),
  CANDLESTICK_SEMANAL("candlestick-semanal");

  @Getter
  private String topicName;

}
