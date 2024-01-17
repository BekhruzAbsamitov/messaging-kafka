package com.epam.controller;

import com.epam.dto.VehicleSignal;
import com.epam.publisher.KafkaMessagePublisher;
import com.epam.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class VehicleController {

    private final KafkaMessagePublisher publisher;
    private final ValidationService validationService;

    @Autowired
    public VehicleController(KafkaMessagePublisher publisher, ValidationService validationService) {
        this.publisher = publisher;
        this.validationService = validationService;
    }

    @PostMapping("/signal")
    public ResponseEntity<?> publishMessage(@RequestBody VehicleSignal signal) {
        if (validationService.isValid(signal.getVehicleId()) &&
                validationService.isValidCoordinates(signal.getCoordinate())) {
            publisher.sendEventsToTopic(signal);
            return new ResponseEntity<>("Message published successfully ..", HttpStatus.ACCEPTED);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
