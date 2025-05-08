package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Capitale;
import com.marco.tradingjournal.repositories.CapitaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CapitaleService {

    private final CapitaleRepository capitaleRepository;

    public CapitaleService(CapitaleRepository capitaleRepository) {
        this.capitaleRepository = capitaleRepository;
    }

    public List<Capitale> findAll() {
        return capitaleRepository.findAll();
    }

    public Optional<Capitale> findById(UUID id) {
        return capitaleRepository.findById(id);
    }

    public Capitale save(Capitale capitale) {
        return capitaleRepository.save(capitale);
    }

    public void deleteById(UUID id) {
        capitaleRepository.deleteById(id);
    }

    public List<Capitale> findByTraderId(UUID traderId) {
        return capitaleRepository.findByTraderIdOrderByDataAsc(traderId);
    }
}

