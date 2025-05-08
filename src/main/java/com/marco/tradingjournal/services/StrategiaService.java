package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Strategia;
import com.marco.tradingjournal.repositories.StrategiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StrategiaService {

    private final StrategiaRepository strategiaRepository;

    public StrategiaService(StrategiaRepository strategiaRepository) {
        this.strategiaRepository = strategiaRepository;
    }

    public List<Strategia> findAll() {
        return strategiaRepository.findAll();
    }

    public Optional<Strategia> findById(UUID id) {
        return strategiaRepository.findById(id);
    }

    public Strategia save(Strategia strategia) {
        return strategiaRepository.save(strategia);
    }

    public void deleteById(UUID id) {
        strategiaRepository.deleteById(id);
    }
}
