package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Trader;
import com.marco.tradingjournal.entities.Trade;
import com.marco.tradingjournal.exception.TraderNotFoundException;
import com.marco.tradingjournal.repositories.TraderRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TraderService {

    private final TraderRepository traderRepository;

    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    // ─────────────────────────────────────────────
    // CRUD BASE
    // ─────────────────────────────────────────────

    public Trader save(Trader trader) {
        return traderRepository.save(trader); // puoi aggiungere controlli se vuoi (email, username, etc.)
    }

    public Trader findById(UUID id) {
        return traderRepository.findById(id)
                .orElseThrow(() -> new TraderNotFoundException(id));
    }

    public List<Trader> findAll() {
        return traderRepository.findAll();
    }

    public void deleteById(UUID id) {
        if (!traderRepository.existsById(id)) {
            throw new TraderNotFoundException(id);
        }
        traderRepository.deleteById(id);
    }

    // ─────────────────────────────────────────────
    // LOGIN / RICERCA
    // ─────────────────────────────────────────────

    public Trader findByUsername(String username) {
        return traderRepository.findAll().stream()
                .filter(t -> t.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new TraderNotFoundException(UUID.randomUUID())); // fallback simbolico
    }

    public Trader findByEmail(String email) {
        return traderRepository.findAll().stream()
                .filter(t -> t.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new TraderNotFoundException(UUID.randomUUID()));
    }

    public boolean existsByEmail(String email) {
        return traderRepository.findAll().stream()
                .anyMatch(t -> t.getEmail().equalsIgnoreCase(email));
    }

    public boolean existsByUsername(String username) {
        return traderRepository.findAll().stream()
                .anyMatch(t -> t.getUsername().equalsIgnoreCase(username));
    }

    // ─────────────────────────────────────────────
    // AGGIORNAMENTO CAPITALE
    // ─────────────────────────────────────────────

    public void aggiornaCapitaleAttuale(UUID traderId, double variazione) {
        Trader trader = traderRepository.findById(traderId)
                .orElseThrow(() -> new TraderNotFoundException(traderId));

        double nuovoCapitale = trader.getCapitaleAttuale() + variazione;
        trader.setCapitaleAttuale(Math.round(nuovoCapitale * 100.0) / 100.0);
        traderRepository.save(trader);
    }

    public void resettaCapitale(UUID traderId) {
        Trader trader = traderRepository.findById(traderId)
                .orElseThrow(() -> new TraderNotFoundException(traderId));

        trader.setCapitaleAttuale(trader.getCapitaleIniziale());
        traderRepository.save(trader);
    }

    // ─────────────────────────────────────────────
    // ANALISI
    // ─────────────────────────────────────────────

    public int contaTrade(UUID traderId) {
        Trader trader = traderRepository.findById(traderId)
                .orElseThrow(() -> new TraderNotFoundException(traderId));
        return trader.getTrades().size();
    }

    public double calcolaProfittoTotale(UUID traderId) {
        Trader trader = traderRepository.findById(traderId)
                .orElseThrow(() -> new TraderNotFoundException(traderId));

        return trader.getTrades().stream()
                .mapToDouble(Trade::getProfittoNetto)
                .sum();
    }

    public double calcolaWinrate(UUID traderId) {
        Trader trader = traderRepository.findById(traderId)
                .orElseThrow(() -> new TraderNotFoundException(traderId));

        List<Trade> trades = trader.getTrades();
        if (trades.isEmpty()) return 0.0;

        long vincenti = trades.stream()
                .filter(t -> t.getEsito().name().equals("PROFITTO"))
                .count();

        return Math.round((double) vincenti / trades.size() * 10000.0) / 100.0;
    }

    // ─────────────────────────────────────────────
    // RESET COMPLETO
    // ─────────────────────────────────────────────

    public void resettaTrader(UUID traderId) {
        Trader trader = traderRepository.findById(traderId)
                .orElseThrow(() -> new TraderNotFoundException(traderId));

        trader.getTrades().clear();
        trader.getAndamentoCapitale().clear();
        trader.setCapitaleAttuale(trader.getCapitaleIniziale());
        traderRepository.save(trader);
    }
}
