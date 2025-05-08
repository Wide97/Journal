package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.entities.Strategia;
import com.marco.tradingjournal.entities.Trade;
import com.marco.tradingjournal.services.StrategiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/strategie")
public class StrategiaController {

    private final StrategiaService strategiaService;

    public StrategiaController(StrategiaService strategiaService) {
        this.strategiaService = strategiaService;
    }

    // ──────── CRUD BASE ────────

    @PostMapping
    public ResponseEntity<Strategia> createStrategia(@RequestBody Strategia strategia) {
        return ResponseEntity.ok(strategiaService.save(strategia));
    }

    @GetMapping
    public ResponseEntity<List<Strategia>> getAllStrategie() {
        return ResponseEntity.ok(strategiaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Strategia> getStrategiaById(@PathVariable UUID id) {
        return ResponseEntity.ok(strategiaService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStrategia(@PathVariable UUID id) {
        strategiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ──────── FUNZIONI CUSTOM ────────

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Strategia> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(strategiaService.findByNome(nome));
    }

    @GetMapping("/{id}/trades")
    public ResponseEntity<List<Trade>> getTradesByStrategia(@PathVariable UUID id) {
        return ResponseEntity.ok(strategiaService.getTradesByStrategiaId(id));
    }

    @DeleteMapping("/{id}/if-empty")
    public ResponseEntity<String> deleteIfEmpty(@PathVariable UUID id) {
        boolean deleted = strategiaService.deleteStrategiaIfEmpty(id);
        if (deleted) {
            return ResponseEntity.ok("Strategia eliminata con successo (nessun trade associato).");
        } else {
            return ResponseEntity.badRequest().body("Non è stato possibile eliminare: ci sono trade associati.");
        }
    }

    @GetMapping("/count-trades")
    public ResponseEntity<Map<String, Long>> countTradesPerStrategia() {
        return ResponseEntity.ok(strategiaService.countTradesPerStrategia());
    }
}
