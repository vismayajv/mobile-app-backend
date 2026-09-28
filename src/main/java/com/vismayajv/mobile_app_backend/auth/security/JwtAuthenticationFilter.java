package com.vismayajv.mobile_app_backend.auth.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import com.vismayajv.mobile_app_backend.auth.security.JwtAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vismayajv.mobile_app_backend.auth.entity.user;
import com.vismayajv.mobile_app_backend.auth.repository.UserRepository;
import com.vismayajv.mobile_app_backend.auth.service.JwtService;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component 
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

 @Override
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    // No token → continue normally
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }

    String token = authHeader.substring(7);

    try {
        String email = jwtService.extractEmail(token);

        if (email != null &&
            SecurityContextHolder.getContext().getAuthentication() == null) {

            user user = userRepository.findByEmail(email)
                    .orElse(null);

            // User exists and token is valid
            if (user != null && jwtService.isTokenValid(token, user)) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                Collections.emptyList()
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

    } catch (Exception e) {
        // Invalid or expired token.
        // Do not authenticate the request.
    }

    filterChain.doFilter(request, response);
}
    

}
