package com.ricardococati.kafka.sender;

import com.ricardococati.kafka.topic.TopicEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(final String payload, String topicName) {
        try {
            kafkaTemplate.send(topicName, payload).get();
        } catch (Exception e) {
            log.error("Error send message to kafka topic: {}, payload: {}", topicName, payload, e);
            throw new RuntimeException(e);
        }
    }
}
