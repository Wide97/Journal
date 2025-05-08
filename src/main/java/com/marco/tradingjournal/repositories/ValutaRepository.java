package com.marco.tradingjournal.repositories;

import com.marco.tradingjournal.entities.Valuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ValutaRepository extends JpaRepository<Valuta, UUID> {
    Optional<Valuta> findByCodice(String codice);
}
