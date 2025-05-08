package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.entities.Strategia;
import com.marco.tradingjournal.services.StrategiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/strategie")
public class StrategiaController {

    private final StrategiaService strategiaService;

    public StrategiaController(StrategiaService strategiaService) {
        this.strategiaService = strategiaService;
    }

    @PostMapping
    public ResponseEntity<Strategia> createStrategia(@RequestBody Strategia strategia) {
        return ResponseEntity.ok(strategiaService.createStrategia(strategia));
    }

    @GetMapping
    public ResponseEntity<List<Strategia>> getAllStrategie() {
        return ResponseEntity.ok(strategiaService.getAllStrategie());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Strategia> getStrategiaById(@PathVariable UUID id) {
        return ResponseEntity.ok(strategiaService.getStrategiaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Strategia> updateStrategia(@PathVariable UUID id, @RequestBody Strategia strategia) {
        return ResponseEntity.ok(strategiaService.updateStrategia(id, strategia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStrategia(@PathVariable UUID id) {
        strategiaService.deleteStrategia(id);
        return ResponseEntity.noContent().build();
    }
}
