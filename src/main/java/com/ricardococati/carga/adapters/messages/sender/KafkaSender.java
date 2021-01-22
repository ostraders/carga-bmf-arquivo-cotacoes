package com.ricardococati.carga.adapters.messages.sender;

public interface KafkaSender {

  void send(String payload, String topicName);

}
