package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Valuta;
import com.marco.tradingjournal.exception.CurrencyNotFoundException;
import com.marco.tradingjournal.repositories.ValutaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ValutaService {

    private final ValutaRepository valutaRepository;

    public ValutaService(ValutaRepository valutaRepository) {
        this.valutaRepository = valutaRepository;
    }

    // ─────────────────────────────
    // CRUD BASE
    // ─────────────────────────────

    public List<Valuta> findAll() {
        return valutaRepository.findAll();
    }

    public Valuta findById(UUID id) {
        return valutaRepository.findById(id)
                .orElseThrow(() -> new CurrencyNotFoundException(id));
    }

    public Valuta save(Valuta valuta) {
        // opzionale: validazione su codice/simbolo se vuoi usare InvalidCurrencyException
        return valutaRepository.save(valuta);
    }

    public void deleteById(UUID id) {
        if (!valutaRepository.existsById(id)) {
            throw new CurrencyNotFoundException(id);
        }
        valutaRepository.deleteById(id);
    }

    // ─────────────────────────────
    // METODI EXTRA
    // ─────────────────────────────

    public Valuta findByCodice(String codice) {
        return valutaRepository.findAll().stream()
                .filter(v -> v.getCodice().equalsIgnoreCase(codice))
                .findFirst()
                .orElseThrow(() -> new CurrencyNotFoundException(UUID.randomUUID()));
    }

    public boolean existsByCodice(String codice) {
        return valutaRepository.findAll().stream()
                .anyMatch(v -> v.getCodice().equalsIgnoreCase(codice));
    }

    public String getSimboloByCodice(String codice) {
        return findByCodice(codice).getSimbolo();
    }

    public List<Valuta> findAllOrdered() {
        return valutaRepository.findAll().stream()
                .sorted(Comparator.comparing(Valuta::getCodice))
                .collect(Collectors.toList());
    }
}
