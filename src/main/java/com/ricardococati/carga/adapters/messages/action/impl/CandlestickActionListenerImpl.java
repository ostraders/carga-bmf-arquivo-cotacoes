package com.ricardococati.carga.adapters.messages.action.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardococati.carga.adapters.messages.action.CandlestickActionListener;
import com.ricardococati.carga.adapters.messages.sender.KafkaSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CandlestickActionListenerImpl implements CandlestickActionListener {

  private final KafkaSender kafkaSender;

  private final ObjectMapper om;

  @Override
  public void onAfterSave(final Object event, String topicName) {
      final String payload = serialize(event);
      kafkaSender.send(payload, topicName);
  }

  private String serialize(final Object source) {
    try {
      return om.writeValueAsString(source);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

}
