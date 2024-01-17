package com.epam.listener;

import com.epam.LoggingServiceApplication;
import com.epam.dto.VehicleDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LoggingConsumerService {

    private final Logger log = LoggerFactory.getLogger(LoggingServiceApplication.class.getSimpleName());

    @KafkaListener(topics = "output", groupId = "group1")
    public void consume(VehicleDistance vehicleDistance) {
        log.info("Vehicle ID: [{}], Distance: [{}]", vehicleDistance.getVehicleId(), vehicleDistance.getTotalDistance());
    }

}
