package com.vismayajv.mobile_app_backend.auth.controller;

import com.vismayajv.mobile_app_backend.auth.dto.RegisterRequest;
import com.vismayajv.mobile_app_backend.auth.entity.user;
import com.vismayajv.mobile_app_backend.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.vismayajv.mobile_app_backend.auth.dto.RegisterResponse;
import com.vismayajv.mobile_app_backend.auth.dto.LoginRequest;
import com.vismayajv.mobile_app_backend.auth.dto.LoginResponse;

@RestController 
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
   public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
public LoginResponse login(@Valid @RequestBody LoginRequest request) {
    return authService.login(request);
}
    
}
