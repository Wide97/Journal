package com.marco.tradingjournal.repositories;

import com.marco.tradingjournal.entities.Strategia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StrategiaRepository extends JpaRepository<Strategia, UUID> {
    Optional<Strategia> findByNome(String nome);
}
