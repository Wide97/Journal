package com.marco.tradingjournal.controllers;

import com.marco.tradingjournal.entities.Asset;
import com.marco.tradingjournal.entities.Trade;
import com.marco.tradingjournal.services.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        return ResponseEntity.ok(assetService.save(asset));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.findAll());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable UUID id) {
        return ResponseEntity.ok(assetService.findById(id));
    }

    // READ BY SYMBOL
    @GetMapping("/simbolo/{simbolo}")
    public ResponseEntity<Asset> getBySimbolo(@PathVariable String simbolo) {
        return ResponseEntity.ok(assetService.findBySimbolo(simbolo));
    }

    // READ BY TIPO
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Asset>> getByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(assetService.findByTipo(tipo));
    }

    // GET TRADES ASSOCIATI
    @GetMapping("/{id}/trades")
    public ResponseEntity<List<Trade>> getTradesByAssetId(@PathVariable UUID id) {
        return ResponseEntity.ok(assetService.getTradesByAssetId(id));
    }

    // CLEAR TRADES ASSOCIATI
    @PutMapping("/{id}/clear-trades")
    public ResponseEntity<Void> clearTrades(@PathVariable UUID id) {
        assetService.clearTrades(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable UUID id) {
        assetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
