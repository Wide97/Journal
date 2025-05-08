package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.services.TraderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/traders")
public class TraderController {

    private final TraderService traderService;

    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }

    @PostMapping
    public ResponseEntity<TraderDTO> createTrader(@RequestBody TraderDTO traderDTO) {
        return ResponseEntity.ok(traderService.createTrader(traderDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TraderDTO> getTraderById(@PathVariable UUID id) {
        return ResponseEntity.ok(traderService.getTraderById(id));
    }

    @GetMapping
    public ResponseEntity<List<TraderDTO>> getAllTraders() {
        return ResponseEntity.ok(traderService.getAllTraders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TraderDTO> updateTrader(@PathVariable UUID id, @RequestBody TraderDTO traderDTO) {
        return ResponseEntity.ok(traderService.updateTrader(id, traderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrader(@PathVariable UUID id) {
        traderService.deleteTrader(id);
        return ResponseEntity.noContent().build();
    }
}
