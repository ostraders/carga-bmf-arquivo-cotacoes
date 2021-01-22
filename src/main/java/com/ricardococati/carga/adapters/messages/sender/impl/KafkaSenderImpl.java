package com.ricardococati.carga.adapters.messages.sender.impl;

import com.ricardococati.carga.adapters.messages.sender.KafkaSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaSenderImpl implements KafkaSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(final String payload, String topicName) {
        try {
            kafkaTemplate.send(topicName, payload).get();
        } catch (Exception e) {
            log.error("Error send message to kafka topic: {}, payload: {}", topicName, payload, e);
            throw new RuntimeException(e);
        }
    }
}
