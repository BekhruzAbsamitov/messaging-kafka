package com.epam.consumer;

import com.epam.dto.VehicleDistance;
import com.epam.dto.VehicleSignal;
import com.epam.service.VehicleDistanceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class.getSimpleName());

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
        log.info("Distance calculator consumer get the signal {} ", signal);
        double distance = vehicleDistanceCalculator.calculate(signal);
        VehicleDistance vehicleDistance = new VehicleDistance(signal.getVehicleId(), distance);
        kafkaTemplate.send("output", vehicleDistance);
        log.info("Distance calculator send to output topic {} ", signal);
    }

}
