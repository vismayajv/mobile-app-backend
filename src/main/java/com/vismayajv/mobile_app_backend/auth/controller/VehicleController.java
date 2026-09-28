package com.vismayajv.mobile_app_backend.auth.controller;

import com.vismayajv.mobile_app_backend.auth.entity.Vehicle;
import com.vismayajv.mobile_app_backend.auth.dto.VehicleRequest;
import com.vismayajv.mobile_app_backend.auth.dto.VehicleResponse;
import com.vismayajv.mobile_app_backend.auth.service.VehicleService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse  createVehicle(@RequestBody VehicleRequest request) {
        return vehicleService.createVehicle(request);
    }

    @GetMapping
public List<VehicleResponse> getMyVehicles() {
    return vehicleService.getMyVehicles();
}

@GetMapping("/{id}")
public VehicleResponse getMyVehicle(@PathVariable Long id) {
    return vehicleService.getMyVehicle(id);
}
@PutMapping("/{id}")
public VehicleResponse  updateVehicle(
        @PathVariable Long id,
        @RequestBody VehicleRequest request) {

    return vehicleService.updateVehicle(id, request);
}
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteVehicle(@PathVariable Long id) {
    vehicleService.deleteVehicle(id);
}
}