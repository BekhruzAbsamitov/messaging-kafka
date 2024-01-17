package com.epam.dto;

public class VehicleSignal {

    private String vehicleId;
    private Coordinate coordinate;

    public VehicleSignal() {
    }

    public VehicleSignal(String vehicleId, Coordinate coordinate) {
        this.vehicleId = vehicleId;
        this.coordinate = coordinate;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "VehicleSignal{" +
                "vehicleId='" + vehicleId + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }
}
