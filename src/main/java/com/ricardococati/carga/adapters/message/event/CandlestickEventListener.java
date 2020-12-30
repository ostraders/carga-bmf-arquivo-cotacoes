package com.ricardococati.carga.adapters.message.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardococati.carga.adapters.message.sender.KafkaSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandlestickEventListener {

  private final KafkaSender kafkaSender;

  private final ObjectMapper om;

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
