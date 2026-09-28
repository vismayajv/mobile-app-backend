package com.vismayajv.mobile_app_backend.auth.dto;

import java.time.LocalDateTime;

public class VehicleResponse {

    private Long id;
    private String vehicleNumber;
    private String make;
    private String model;
    private Integer year;
    private String fuelType;
    private LocalDateTime createdAt;

    public VehicleResponse() {
    }

    public VehicleResponse(
            Long id,
            String vehicleNumber,
            String make,
            String model,
            Integer year,
            String fuelType,
            LocalDateTime createdAt) {

        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}