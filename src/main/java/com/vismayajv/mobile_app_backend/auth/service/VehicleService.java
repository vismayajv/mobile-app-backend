package com.vismayajv.mobile_app_backend.auth.service;
import com.vismayajv.mobile_app_backend.auth.dto.VehicleRequest;
import com.vismayajv.mobile_app_backend.auth.dto.VehicleResponse;
import com.vismayajv.mobile_app_backend.auth.entity.Vehicle;
import com.vismayajv.mobile_app_backend.auth.entity.user;
import com.vismayajv.mobile_app_backend.auth.repository.VehicleRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.vismayajv.mobile_app_backend.exception.ResourceNotFoundException;


@Service 
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
     public VehicleResponse createVehicle(VehicleRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        user loggedInUser = (user) authentication.getPrincipal();

        Vehicle vehicle = new Vehicle();

        vehicle.setUser(loggedInUser);
        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setMake(request.getMake());
        vehicle.setModel(request.getModel());
        vehicle.setYear(request.getYear());
        vehicle.setFuelType(request.getFuelType());
  vehicle.setCreatedAt(LocalDateTime.now());
       Vehicle savedVehicle = vehicleRepository.save(vehicle);

return toResponse(savedVehicle);
    }

    public List<VehicleResponse> getMyVehicles() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        user loggedInUser = (user) authentication.getPrincipal();

        List<Vehicle> vehicles = vehicleRepository.findByUser(loggedInUser);

    return vehicles.stream()
            .map(this::toResponse)
            .toList();
    }

    public VehicleResponse  getMyVehicle(Long id) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    user loggedInUser = (user) authentication.getPrincipal();

    Vehicle vehicle = vehicleRepository.findByIdAndUser(id, loggedInUser)
            .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

    return toResponse(vehicle);
}

public VehicleResponse  updateVehicle(Long id, VehicleRequest request) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    user loggedInUser = (user) authentication.getPrincipal();

    Vehicle vehicle = vehicleRepository.findByIdAndUser(id, loggedInUser)
            .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

    vehicle.setVehicleNumber(request.getVehicleNumber());
    vehicle.setMake(request.getMake());
    vehicle.setModel(request.getModel());
    vehicle.setYear(request.getYear());
    vehicle.setFuelType(request.getFuelType());

    Vehicle updatedVehicle = vehicleRepository.save(vehicle);

    return toResponse(updatedVehicle);
}

public void deleteVehicle(Long id) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    user loggedInUser = (user) authentication.getPrincipal();

    Vehicle vehicle = vehicleRepository.findByIdAndUser(id, loggedInUser)
            .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

    vehicleRepository.delete(vehicle);
}

private VehicleResponse toResponse(Vehicle vehicle) {

    return new VehicleResponse(
            vehicle.getId(),
            vehicle.getVehicleNumber(),
            vehicle.getMake(),
            vehicle.getModel(),
            vehicle.getYear(),
            vehicle.getFuelType(),
            vehicle.getCreatedAt()
    );
}
}
