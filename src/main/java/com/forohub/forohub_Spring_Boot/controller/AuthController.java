package com.forohub.forohub_Spring_Boot.controller;

import com.forohub.forohub_Spring_Boot.model.dto.AuthRequestDTO;
import com.forohub.forohub_Spring_Boot.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDTO.getUsername(),
                            authRequestDTO.getPassword()
                    )
            );

            String token = tokenService.generarToken(authentication.getName());
            return "Token: " + token;
        } catch (AuthenticationException e) {
            return "Error de autenticación: " + e.getMessage();
        }
    }
}
