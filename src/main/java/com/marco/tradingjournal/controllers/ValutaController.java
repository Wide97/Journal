package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.entities.Valuta;
import com.marco.tradingjournal.services.ValutaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/valute")
public class ValutaController {

    private final ValutaService valutaService;

    public ValutaController(ValutaService valutaService) {
        this.valutaService = valutaService;
    }

    @PostMapping
    public ResponseEntity<Valuta> createValuta(@RequestBody Valuta valuta) {
        return ResponseEntity.ok(valutaService.createValuta(valuta));
    }

    @GetMapping
    public ResponseEntity<List<Valuta>> getAllValute() {
        return ResponseEntity.ok(valutaService.getAllValute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Valuta> getValutaById(@PathVariable UUID id) {
        return ResponseEntity.ok(valutaService.getValutaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Valuta> updateValuta(@PathVariable UUID id, @RequestBody Valuta valuta) {
        return ResponseEntity.ok(valutaService.updateValuta(id, valuta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValuta(@PathVariable UUID id) {
        valutaService.deleteValuta(id);
        return ResponseEntity.noContent().build();
    }
}
