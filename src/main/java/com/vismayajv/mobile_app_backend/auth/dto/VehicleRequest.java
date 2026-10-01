package com.vismayajv.mobile_app_backend.auth.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VehicleRequest {

    @NotBlank (message = "vehicleNumber is required")
    private String vehicleNumber;

    @NotBlank (message = "make is required")
    private String make;

    @NotBlank (message = "model is required")
    private String model;

    @NotNull (message = "year is required")
    @Max (2100)
    @Min (1990)
    private Integer year;

    @NotBlank (message = "fuelType is required")
    private String fuelType;

    public VehicleRequest() {
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}