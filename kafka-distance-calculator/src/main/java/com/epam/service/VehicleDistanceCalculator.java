package com.epam.service;


import com.epam.dto.Coordinate;
import com.epam.dto.VehicleSignal;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class VehicleDistanceCalculator {

    private final Map<String, Coordinate> lastCoordinate = new HashMap<>();

    public double calculate(VehicleSignal signal) {
        Coordinate previous = lastCoordinate.get(signal.getVehicleId());
        double distance = Optional.ofNullable(previous)
                .map(coordinate -> calculateDistance(previous, signal.getCoordinate()))
                .orElse(0D);
        lastCoordinate.put(signal.getVehicleId(), signal.getCoordinate());
        return distance;
    }

    private double calculateDistance(Coordinate from, Coordinate to) {
        return Math.sqrt(Math.abs((from.getX() - to.getX() * (from.getX() - to.getX()) -
                (from.getY() - to.getY()) * (from.getY() - to.getY()))));
    }

}
