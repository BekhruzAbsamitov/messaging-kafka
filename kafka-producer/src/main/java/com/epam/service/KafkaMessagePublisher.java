package com.epam.service;

import com.epam.dto.VehicleSignal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    Logger log = LoggerFactory.getLogger(KafkaMessagePublisher.class.getSimpleName());

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventsToTopic(VehicleSignal signal) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("input", signal);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Sent message=[{}] with offset=[{}]", signal.toString(), result.getRecordMetadata().offset());
                } else {
                    log.info("Unable to send message=[{}] due to : {}", signal.toString(), ex.getMessage());
                }
            });

        } catch (Exception ex) {
            log.error("ERROR : " + ex.getMessage(), ex);
        }
    }
}
