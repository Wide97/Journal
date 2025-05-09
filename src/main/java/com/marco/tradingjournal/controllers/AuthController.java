package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.entities.Trader;
import com.marco.tradingjournal.jwt.JwtUtils;
import com.marco.tradingjournal.services.TraderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtils jwtUtils;
    private final TraderService traderService;

    public AuthController(JwtUtils jwtUtils, TraderService traderService) {
        this.jwtUtils = jwtUtils;
        this.traderService = traderService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Trader trader = traderService.findByUsername(username);

        if (!trader.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Credenziali non valide");
        }

        String token = jwtUtils.generateJwtToken(trader.getId());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
