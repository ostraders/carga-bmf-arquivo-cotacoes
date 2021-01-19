package com.ricardococati.carga.adapters.message.sender;

public interface KafkaSender {

  void send(String payload, String topicName);

}
