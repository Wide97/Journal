package com.marco.tradingjournal.repositories;

import com.marco.tradingjournal.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetRepository extends JpaRepository<Asset, UUID> {
    Optional<Asset> findBySimbolo(String simbolo);
}

