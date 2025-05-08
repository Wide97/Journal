package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Asset;
import com.marco.tradingjournal.repositories.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public Optional<Asset> findById(UUID id) {
        return assetRepository.findById(id);
    }

    public Asset save(Asset asset) {
        return assetRepository.save(asset);
    }

    public void deleteById(UUID id) {
        assetRepository.deleteById(id);
    }
}
