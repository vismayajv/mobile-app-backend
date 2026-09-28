package com.vismayajv.mobile_app_backend.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String test() {
        return "JWT authentication is working!";
    }

     @GetMapping("/api/protected")
    public String protectedTest() {
        return "You accessed a protected API!";
    }
}
