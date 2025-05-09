package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.entities.Trade;
import com.marco.tradingjournal.services.TradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/trades")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    // ──────────────── CRUD BASE ────────────────

    @PostMapping
    public ResponseEntity<Trade> createTrade(@RequestBody Trade trade) {
        return ResponseEntity.ok(tradeService.save(trade));
    }

    @GetMapping
    public ResponseEntity<List<Trade>> getAllTrades() {
        return ResponseEntity.ok(tradeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable UUID id) {
        return ResponseEntity.ok(tradeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trade> updateTrade(@PathVariable UUID id, @RequestBody Trade trade) {
        return ResponseEntity.ok(tradeService.updateTrade(id, trade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrade(@PathVariable UUID id) {
        tradeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ──────────────── FILTRI / ANALISI ────────────────

    // Tutti i trade per un trader
    @GetMapping("/trader/{traderId}")
    public ResponseEntity<List<Trade>> getByTraderId(@PathVariable UUID traderId) {
        return ResponseEntity.ok(tradeService.findByTraderId(traderId));
    }

    // Profitto totale
    @GetMapping("/trader/{traderId}/profitto")
    public ResponseEntity<Double> getProfittoTotale(@PathVariable UUID traderId) {
        return ResponseEntity.ok(tradeService.calcolaProfittoTotale(traderId));
    }

    // Winrate
    @GetMapping("/trader/{traderId}/winrate")
    public ResponseEntity<Double> getWinrate(@PathVariable UUID traderId) {
        return ResponseEntity.ok(tradeService.calcolaWinrate(traderId));
    }

    // ──────────────── UTENTE LOGGATO (via JWT) ────────────────

    @GetMapping("/me")
    public ResponseEntity<List<Trade>> getMyTrades() {
        UUID traderId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(tradeService.findByTraderId(traderId));
    }

    @GetMapping("/me/winrate")
    public ResponseEntity<Double> getMyWinrate() {
        UUID traderId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(tradeService.calcolaWinrate(traderId));
    }

    @GetMapping("/me/profitto")
    public ResponseEntity<Double> getMyProfittoTotale() {
        UUID traderId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(tradeService.calcolaProfittoTotale(traderId));
    }
}
