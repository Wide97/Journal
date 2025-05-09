package com.marco.tradingjournal.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class RootController {

    @GetMapping("/")
    public String healthCheck() {
        return "Backend attivo ✅";
    }
}
