package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.entities.Valuta;
import com.marco.tradingjournal.services.ValutaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/valute")
public class ValutaController {

    private final ValutaService valutaService;

    public ValutaController(ValutaService valutaService) {
        this.valutaService = valutaService;
    }

    // ────── CRUD BASE ──────

    @PostMapping
    public ResponseEntity<Valuta> createValuta(@RequestBody Valuta valuta) {
        return ResponseEntity.ok(valutaService.save(valuta));
    }

    @GetMapping
    public ResponseEntity<List<Valuta>> getAllValute() {
        return ResponseEntity.ok(valutaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Valuta> getValutaById(@PathVariable UUID id) {
        return ResponseEntity.ok(valutaService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValuta(@PathVariable UUID id) {
        valutaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ────── EXTRA ──────

    @GetMapping("/codice/{codice}")
    public ResponseEntity<Valuta> getByCodice(@PathVariable String codice) {
        return ResponseEntity.ok(valutaService.findByCodice(codice));
    }

    @GetMapping("/codice/{codice}/exists")
    public ResponseEntity<Boolean> existsByCodice(@PathVariable String codice) {
        return ResponseEntity.ok(valutaService.existsByCodice(codice));
    }

    @GetMapping("/codice/{codice}/simbolo")
    public ResponseEntity<String> getSimboloByCodice(@PathVariable String codice) {
        return ResponseEntity.ok(valutaService.getSimboloByCodice(codice));
    }

    @GetMapping("/ordered")
    public ResponseEntity<List<Valuta>> getAllOrdered() {
        return ResponseEntity.ok(valutaService.findAllOrdered());
    }
}
