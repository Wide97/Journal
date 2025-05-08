package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Trade;
import com.marco.tradingjournal.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public Optional<Trade> findById(UUID id) {
        return tradeRepository.findById(id);
    }

    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    public void deleteById(UUID id) {
        tradeRepository.deleteById(id);
    }
}
