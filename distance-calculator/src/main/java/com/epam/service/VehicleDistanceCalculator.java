package com.epam.service;


import com.epam.dto.Coordinate;
import com.epam.dto.VehicleSignal;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VehicleDistanceCalculator {

    private final Map<String, Coordinate> lastCoordinate = new HashMap<>();

    public double calculate(VehicleSignal signal) {
        double distance = 0;
        Coordinate previous = lastCoordinate.get(signal.getVehicleId());
        if (previous != null) {
            distance = calculateDistance(previous, signal.getCoordinate());
        }
        lastCoordinate.put(signal.getVehicleId(), signal.getCoordinate());
        return distance;
    }

    private double calculateDistance(Coordinate from, Coordinate to) {
        return Math.sqrt(Math.abs((from.getX() - to.getX() * (from.getX() - to.getX()) -
                (from.getY() - to.getY()) * (from.getY() - to.getY()))));
    }

}
