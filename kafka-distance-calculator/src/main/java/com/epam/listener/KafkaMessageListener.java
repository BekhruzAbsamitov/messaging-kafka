package com.epam.listener;

import com.epam.dto.VehicleDistance;
import com.epam.dto.VehicleSignal;
import com.epam.service.VehicleDistanceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessageListener {

    private final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class.getSimpleName());

    private final KafkaTemplate<String, VehicleDistance> kafkaTemplate;
    private final VehicleDistanceCalculator vehicleDistanceCalculator;

    public KafkaMessageListener(
            KafkaTemplate<String, VehicleDistance> kafkaTemplate,
            VehicleDistanceCalculator vehicleDistanceCalculator
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.vehicleDistanceCalculator = vehicleDistanceCalculator;
    }

    @KafkaListener(topics = "input", groupId = "group1")
    public void consumeEvents(VehicleSignal signal) {
        log.info("Received signal [{}] from topic [input] ", signal);
        double distance = vehicleDistanceCalculator.calculate(signal);
        VehicleDistance vehicleDistance = new VehicleDistance(signal.getVehicleId(), distance);
        send(signal, vehicleDistance);
    }

    private void send(VehicleSignal signal, VehicleDistance vehicleDistance) {
        CompletableFuture<SendResult<String, VehicleDistance>> completableFuture =
                kafkaTemplate.send("output", vehicleDistance);
        completableFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message [{}] to topic [output] with offset [{}]", signal, result.getRecordMetadata().offset());
            } else {
                log.error("Error while sending", ex);
            }
        });
    }

}
