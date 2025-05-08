package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.dto.TradeDTO;
import com.marco.tradingjournal.services.TradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    public ResponseEntity<TradeDTO> createTrade(@RequestBody TradeDTO tradeDTO) {
        return ResponseEntity.ok(tradeService.createTrade(tradeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TradeDTO> getTradeById(@PathVariable UUID id) {
        return ResponseEntity.ok(tradeService.getTradeById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TradeDTO>> getAllTradesByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(tradeService.getAllTradesByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TradeDTO> updateTrade(@PathVariable UUID id, @RequestBody TradeDTO tradeDTO) {
        return ResponseEntity.ok(tradeService.updateTrade(id, tradeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrade(@PathVariable UUID id) {
        tradeService.deleteTrade(id);
        return ResponseEntity.noContent().build();
    }
}
