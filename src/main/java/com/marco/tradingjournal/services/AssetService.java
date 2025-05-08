package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Asset;
import com.marco.tradingjournal.entities.Trade;
import com.marco.tradingjournal.exception.AssetNotFoundException;
import com.marco.tradingjournal.exception.InvalidAssetException;
import com.marco.tradingjournal.repositories.AssetRepository;
import com.marco.tradingjournal.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public Asset findById(UUID id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new AssetNotFoundException(id));
    }

    public Asset save(Asset asset) {
        if (StringUtils.isNullOrBlank(asset.getSimbolo()) || StringUtils.isNullOrBlank(asset.getTipo())) {
            throw new InvalidAssetException("Simbolo e tipo sono obbligatori.");
        }

        asset.setSimbolo(StringUtils.capitalize(asset.getSimbolo()));
        asset.setTipo(StringUtils.capitalize(asset.getTipo()));

        return assetRepository.save(asset);
    }

    public void deleteById(UUID id) {
        if (!assetRepository.existsById(id)) {
            throw new AssetNotFoundException(id);
        }
        assetRepository.deleteById(id);
    }

    public Asset findBySimbolo(String simbolo) {
        return assetRepository.findAll().stream()
                .filter(a -> a.getSimbolo().equalsIgnoreCase(simbolo))
                .findFirst()
                .orElseThrow(() -> new AssetNotFoundException(
                        UUID.fromString("00000000-0000-0000-0000-000000000000") // fallback ID simbolico
                ));
    }

    public List<Trade> getTradesByAssetId(UUID assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new AssetNotFoundException(assetId));
        return asset.getTrades();
    }

    public void clearTrades(UUID assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new AssetNotFoundException(assetId));
        asset.getTrades().clear();
        assetRepository.save(asset);
    }

    public boolean existsBySimbolo(String simbolo) {
        return assetRepository.findAll().stream()
                .anyMatch(a -> a.getSimbolo().equalsIgnoreCase(simbolo));
    }

    public List<Asset> findByTipo(String tipo) {
        return assetRepository.findAll().stream()
                .filter(a -> a.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }
}
