package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Capitale;
import com.marco.tradingjournal.entities.Trader;
import com.marco.tradingjournal.exception.CapitaleNotFoundException;
import com.marco.tradingjournal.exception.InitialCapitalAlreadySetException;
import com.marco.tradingjournal.exception.InvalidCapitaleException;
import com.marco.tradingjournal.repositories.CapitaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CapitaleService {

    private final CapitaleRepository capitaleRepository;

    public CapitaleService(CapitaleRepository capitaleRepository) {
        this.capitaleRepository = capitaleRepository;
    }

    // ────────────────
    // CRUD BASE
    // ────────────────

    public Capitale save(Capitale capitale) {
        if (capitale.getValore() <= 0 || capitale.getData() == null || capitale.getTrader() == null) {
            throw new InvalidCapitaleException("Valore, data o trader non validi.");
        }
        return capitaleRepository.save(capitale);
    }

    public Capitale findById(UUID id) {
        return capitaleRepository.findById(id)
                .orElseThrow(() -> new CapitaleNotFoundException(id));
    }

    public List<Capitale> findAll() {
        return capitaleRepository.findAll();
    }

    public void deleteById(UUID id) {
        if (!capitaleRepository.existsById(id)) {
            throw new CapitaleNotFoundException(id);
        }
        capitaleRepository.deleteById(id);
    }

    // ────────────────
    // FUNZIONI AGGIUNTIVE
    // ────────────────

    public Capitale findByTraderAndDate(UUID traderId, LocalDate date) {
        return capitaleRepository.findAll().stream()
                .filter(c -> c.getTrader().getId().equals(traderId) && c.getData().equals(date))
                .findFirst()
                .orElseThrow(() -> new CapitaleNotFoundException(UUID.randomUUID())); // messaggio simbolico
    }

    public List<Capitale> findAllByTraderOrdered(UUID traderId) {
        return capitaleRepository.findAll().stream()
                .filter(c -> c.getTrader().getId().equals(traderId))
                .sorted(Comparator.comparing(Capitale::getData))
                .collect(Collectors.toList());
    }

    public Capitale addNewCapitale(UUID traderId, LocalDate date, double valore, Trader trader) {
        if (valore <= 0 || date == null || trader == null) {
            throw new InvalidCapitaleException("Parametri non validi.");
        }

        List<Capitale> precedenti = findAllByTraderOrdered(traderId);

        // Capitale iniziale già impostato
        if (precedenti.stream().anyMatch(c -> c.getData().equals(date))) {
            throw new InitialCapitalAlreadySetException();
        }

        double variazione = 0;
        if (!precedenti.isEmpty()) {
            Capitale ultimo = precedenti.get(precedenti.size() - 1);
            variazione = valore - ultimo.getValore();
        }

        Capitale capitale = new Capitale();
        capitale.setTrader(trader);
        capitale.setData(date);
        capitale.setValore(valore);
        capitale.setVariazione(variazione);

        return capitaleRepository.save(capitale);
    }

    public void deleteAllByTrader(UUID traderId) {
        List<Capitale> daCancellare = findAllByTraderOrdered(traderId);
        capitaleRepository.deleteAll(daCancellare);
    }
}
