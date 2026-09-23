package com.vismayajv.mobile_app_backend.auth.repository;

import com.vismayajv.mobile_app_backend.auth.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<user, Long> {

    Optional<user> findByEmail(String email);

    boolean existsByEmail(String email);
}