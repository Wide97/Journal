package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.*;
import com.marco.tradingjournal.exception.InvalidTradeException;
import com.marco.tradingjournal.exception.TradeNotFoundException;
import com.marco.tradingjournal.repositories.TradeRepository;
import com.marco.tradingjournal.utils.DateUtils;
import com.marco.tradingjournal.utils.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    // ─────────────────────────────
    // CRUD BASE
    // ─────────────────────────────

    public Trade save(Trade trade) {
        validateTrade(trade);
        trade.setProfittoNetto(calcolaProfitto(trade));
        return tradeRepository.save(trade);
    }

    public Trade findById(UUID id) {
        return tradeRepository.findById(id)
                .orElseThrow(() -> new TradeNotFoundException(id));
    }

    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public void deleteById(UUID id) {
        if (!tradeRepository.existsById(id)) {
            throw new TradeNotFoundException(id);
        }
        tradeRepository.deleteById(id);
    }

    // ─────────────────────────────
    // VALIDAZIONI
    // ─────────────────────────────

    private void validateTrade(Trade trade) {
        if (!NumberUtils.isPositive(trade.getLotti())) {
            throw new InvalidTradeException("Lotti deve essere > 0");
        }
        if (!NumberUtils.isPositive(trade.getLeva())) {
            throw new InvalidTradeException("Leva deve essere > 0");
        }
        if (trade.getDataAcquisto() != null && !DateUtils.isValidDate(trade.getDataAcquisto())) {
            throw new InvalidTradeException("Data acquisto non valida");
        }
        if (trade.getDataVendita() != null && !DateUtils.isValidDate(trade.getDataVendita())) {
            throw new InvalidTradeException("Data vendita non valida");
        }
    }

    // ─────────────────────────────
    // FILTRI E STATISTICHE
    // ─────────────────────────────

    public List<Trade> findByTraderId(UUID traderId) {
        return tradeRepository.findAll().stream()
                .filter(t -> t.getTrader().getId().equals(traderId))
                .collect(Collectors.toList());
    }

    public double calcolaProfitto(Trade trade) {
        double rawProfit = trade.getCostoChiusura() - trade.getCostoApertura();
        return NumberUtils.roundTo2(rawProfit);
    }

    public double calcolaProfittoTotale(UUID traderId) {
        return findByTraderId(traderId).stream()
                .mapToDouble(Trade::getProfittoNetto)
                .sum();
    }

    public double calcolaWinrate(UUID traderId) {
        List<Trade> trades = findByTraderId(traderId);
        if (trades.isEmpty()) return 0;

        long vincenti = trades.stream()
                .filter(t -> t.getEsito() == EsitoTrade.PROFITTO)
                .count();

        return NumberUtils.roundTo2(((double) vincenti / trades.size()) * 100);
    }

    public Trade updateTrade(UUID id, Trade updated) {
        return tradeRepository.findById(id)
                .map(existing -> {
                    validateTrade(updated);
                    existing.setDataAcquisto(updated.getDataAcquisto());
                    existing.setDataVendita(updated.getDataVendita());
                    existing.setOraAcquisto(updated.getOraAcquisto());
                    existing.setOraVendita(updated.getOraVendita());
                    existing.setLotti(updated.getLotti());
                    existing.setLeva(updated.getLeva());
                    existing.setCostoApertura(updated.getCostoApertura());
                    existing.setCostoChiusura(updated.getCostoChiusura());
                    existing.setTipo(updated.getTipo());
                    existing.setEsito(updated.getEsito());
                    existing.setAsset(updated.getAsset());
                    existing.setStrategia(updated.getStrategia());
                    existing.setProfittoNetto(calcolaProfitto(updated));
                    return tradeRepository.save(existing);
                })
                .orElseThrow(() -> new TradeNotFoundException(id));
    }
}
