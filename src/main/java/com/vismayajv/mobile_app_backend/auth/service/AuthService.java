package com.vismayajv.mobile_app_backend.auth.service;

import com.vismayajv.mobile_app_backend.auth.dto.RegisterRequest;
import org.springframework.stereotype.Service;
import com.vismayajv.mobile_app_backend.auth.entity.user;
import com.vismayajv.mobile_app_backend.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.vismayajv.mobile_app_backend.auth.dto.RegisterResponse;
import com.vismayajv.mobile_app_backend.auth.dto.LoginRequest;
import com.vismayajv.mobile_app_backend.auth.dto.LoginResponse;
import com.vismayajv.mobile_app_backend.auth.service.JwtService;

@Service 
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = new JwtService();
    }
    public RegisterResponse register(RegisterRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Email already registered");
    }

    user user = new user();

    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setMobileNumber(request.getMobileNumber());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole("USER");

    user savedUser = userRepository.save(user);

    return new RegisterResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail(),
            savedUser.getMobileNumber(),
            savedUser.getRole()
    );
}

public user findUserForLogin(LoginRequest request) {

    return userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));
}

public LoginResponse login(LoginRequest request) {

    user user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
    )) {
        throw new RuntimeException("Invalid email or password");
    }

    String token = jwtService.generateToken(user.getEmail());

    return new LoginResponse(token);
}
}
