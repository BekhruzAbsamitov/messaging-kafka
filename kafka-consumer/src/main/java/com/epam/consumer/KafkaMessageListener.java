package com.epam.consumer;

import com.epam.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class.getSimpleName());

    @KafkaListener(topics = "topic1", groupId = "group1")
    public void consumeEvents(Customer consumer) {
        log.info("Consumer consume the events {} ", consumer.toString());
    }

    @KafkaListener(topics = "topic1", groupId = "group1")
    public void consumeEvents(String msg) {
        log.info("Consumer consume the events {} ", msg);
    }

}
