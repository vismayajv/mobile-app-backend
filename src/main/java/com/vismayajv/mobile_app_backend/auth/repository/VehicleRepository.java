package com.vismayajv.mobile_app_backend.auth.repository;

import com.vismayajv.mobile_app_backend.auth.entity.user;
import com.vismayajv.mobile_app_backend.auth.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByUser(user user);

    Optional<Vehicle> findByIdAndUser(Long id, user user);
}