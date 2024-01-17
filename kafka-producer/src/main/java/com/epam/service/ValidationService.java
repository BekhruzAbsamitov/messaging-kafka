package com.epam.service;

import com.epam.dto.Coordinate;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean isValid(String vehicleId) {
        return true;
    }

    public boolean isValidCoordinates(Coordinate coordinate) {
        return true;
    }
}
