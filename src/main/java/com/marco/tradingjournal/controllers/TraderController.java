package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.entities.Trader;
import com.marco.tradingjournal.services.TraderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/trader")
public class TraderController {

    private final TraderService traderService;

    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }

    // ────── CRUD BASE ──────

    @PostMapping
    public ResponseEntity<Trader> createTrader(@RequestBody Trader trader) {
        return ResponseEntity.ok(traderService.save(trader));
    }

    @GetMapping
    public ResponseEntity<List<Trader>> getAllTraders() {
        return ResponseEntity.ok(traderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trader> getTraderById(@PathVariable UUID id) {
        return ResponseEntity.ok(traderService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrader(@PathVariable UUID id) {
        traderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ────── LOGIN / RICERCA ──────

    @GetMapping("/username/{username}")
    public ResponseEntity<Trader> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(traderService.findByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Trader> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(traderService.findByEmail(email));
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(traderService.existsByEmail(email));
    }

    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(traderService.existsByUsername(username));
    }

    // ────── CAPITALE ──────

    @PutMapping("/{id}/aggiorna-capitale")
    public ResponseEntity<Void> aggiornaCapitale(@PathVariable UUID id, @RequestParam double variazione) {
        traderService.aggiornaCapitaleAttuale(id, variazione);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/resetta-capitale")
    public ResponseEntity<Void> resettaCapitale(@PathVariable UUID id) {
        traderService.resettaCapitale(id);
        return ResponseEntity.noContent().build();
    }

    // ────── ANALISI ──────

    @GetMapping("/{id}/conta-trade")
    public ResponseEntity<Integer> contaTrade(@PathVariable UUID id) {
        return ResponseEntity.ok(traderService.contaTrade(id));
    }

    @GetMapping("/{id}/profitto-totale")
    public ResponseEntity<Double> profittoTotale(@PathVariable UUID id) {
        return ResponseEntity.ok(traderService.calcolaProfittoTotale(id));
    }

    @GetMapping("/{id}/winrate")
    public ResponseEntity<Double> winrate(@PathVariable UUID id) {
        return ResponseEntity.ok(traderService.calcolaWinrate(id));
    }

    // ────── RESET COMPLETO ──────

    @PutMapping("/{id}/resetta")
    public ResponseEntity<Void> resettaTutto(@PathVariable UUID id) {
        traderService.resettaTrader(id);
        return ResponseEntity.noContent().build();
    }
}
