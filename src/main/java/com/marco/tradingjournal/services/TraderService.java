package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Trader;
import com.marco.tradingjournal.repositories.TraderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TraderService {

    private final TraderRepository traderRepository;

    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    public List<Trader> findAll() {
        return traderRepository.findAll();
    }

    public Optional<Trader> findById(UUID id) {
        return traderRepository.findById(id);
    }

    public Trader save(Trader trader) {
        return traderRepository.save(trader);
    }

    public void deleteById(UUID id) {
        traderRepository.deleteById(id);
    }
}
