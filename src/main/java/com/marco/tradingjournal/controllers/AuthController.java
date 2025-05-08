package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtils jwtUtils;

    public AuthController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    // Simula un login. Passa un UUID esistente come parametro per ottenere un JWT valido
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam UUID userId) {
        String token = jwtUtils.generateJwtToken(userId);
        return ResponseEntity.ok(token);
    }
}
