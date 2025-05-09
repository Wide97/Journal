package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.entities.Capitale;
import com.marco.tradingjournal.entities.Trader;
import com.marco.tradingjournal.services.CapitaleService;
import com.marco.tradingjournal.services.TraderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/capitale")
public class CapitaleController {

    private final CapitaleService capitaleService;
    private final TraderService traderService;

    public CapitaleController(CapitaleService capitaleService, TraderService traderService) {
        this.capitaleService = capitaleService;
        this.traderService = traderService;
    }

    // ────────────────
    // CRUD BASE
    // ────────────────

    @PostMapping
    public ResponseEntity<Capitale> createCapitale(@RequestBody Capitale capitale) {
        return ResponseEntity.ok(capitaleService.save(capitale));
    }

    @GetMapping
    public ResponseEntity<List<Capitale>> getAll() {
        return ResponseEntity.ok(capitaleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Capitale> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(capitaleService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        capitaleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ────────────────
    // FUNZIONI AGGIUNTIVE
    // ────────────────

    // Get per trader e data
    @GetMapping("/trader/{traderId}/date/{date}")
    public ResponseEntity<Capitale> getByTraderAndDate(@PathVariable UUID traderId, @PathVariable String date) {
        return ResponseEntity.ok(
                capitaleService.findByTraderAndDate(traderId, LocalDate.parse(date))
        );
    }

    // Get ordinato per trader
    @GetMapping("/trader/{traderId}")
    public ResponseEntity<List<Capitale>> getAllByTrader(@PathVariable UUID traderId) {
        return ResponseEntity.ok(capitaleService.findAllByTraderOrdered(traderId));
    }

    // Inserimento nuovo capitale con logica
    @PostMapping("/add")
    public ResponseEntity<Capitale> addNewCapitale(
            @RequestParam UUID traderId,
            @RequestParam String date,
            @RequestParam double valore
    ) {
        Trader trader = traderService.findById(traderId);
        Capitale nuovo = capitaleService.addNewCapitale(traderId, LocalDate.parse(date), valore, trader);
        return ResponseEntity.ok(nuovo);
    }

    // Cancellazione di tutti i capitali di un trader
    @DeleteMapping("/trader/{traderId}")
    public ResponseEntity<Void> deleteAllByTrader(@PathVariable UUID traderId) {
        capitaleService.deleteAllByTrader(traderId);
        return ResponseEntity.noContent().build();
    }
}
