package com.epam.dto;

public class VehicleDistance {

    private String vehicleId;
    private double totalDistance;

    public VehicleDistance(String vehicleId, double totalDistance) {
        this.vehicleId = vehicleId;
        this.totalDistance = totalDistance;
    }

    public VehicleDistance() {
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    @Override
    public String toString() {
        return "VehicleDistance{" +
                "vehicleId='" + vehicleId + '\'' +
                ", totalDistance=" + totalDistance +
                '}';
    }
}
