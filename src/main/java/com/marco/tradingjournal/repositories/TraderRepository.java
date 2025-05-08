package com.marco.tradingjournal.repositories;

import com.marco.tradingjournal.entities.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TraderRepository extends JpaRepository<Trader, UUID> {
    Optional<Trader> findByEmail(String email);
    Optional<Trader> findByUsername(String username);
}
