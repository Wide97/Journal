package com.marco.tradingjournal.repositories;

import com.marco.tradingjournal.entities.Capitale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CapitaleRepository extends JpaRepository<Capitale, UUID> {
    List<Capitale> findByTraderIdOrderByDataAsc(UUID traderId);
}
