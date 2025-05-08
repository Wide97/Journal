package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Strategia;
import com.marco.tradingjournal.entities.Trade;
import com.marco.tradingjournal.exception.InvalidStrategyException;
import com.marco.tradingjournal.exception.StrategyNotFoundException;
import com.marco.tradingjournal.repositories.StrategiaRepository;
import com.marco.tradingjournal.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StrategiaService {

    private final StrategiaRepository strategiaRepository;

    public StrategiaService(StrategiaRepository strategiaRepository) {
        this.strategiaRepository = strategiaRepository;
    }

    public List<Strategia> findAll() {
        return strategiaRepository.findAll();
    }

    public Strategia findById(UUID id) {
        return strategiaRepository.findById(id)
                .orElseThrow(() -> new StrategyNotFoundException(id));
    }

    public Strategia save(Strategia strategia) {
        if (StringUtils.isNullOrBlank(strategia.getNome())) {
            throw new InvalidStrategyException("Il nome è obbligatorio.");
        }

        strategia.setNome(StringUtils.capitalize(strategia.getNome()));
        return strategiaRepository.save(strategia);
    }

    public void deleteById(UUID id) {
        if (!strategiaRepository.existsById(id)) {
            throw new StrategyNotFoundException(id);
        }
        strategiaRepository.deleteById(id);
    }

    public Strategia findByNome(String nome) {
        return strategiaRepository.findAll().stream()
                .filter(s -> s.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new StrategyNotFoundException(UUID.randomUUID())); // simbolico
    }

    public boolean existsByNome(String nome) {
        return strategiaRepository.findAll().stream()
                .anyMatch(s -> s.getNome().equalsIgnoreCase(nome));
    }

    public List<Trade> getTradesByStrategiaId(UUID strategiaId) {
        Strategia strategia = strategiaRepository.findById(strategiaId)
                .orElseThrow(() -> new StrategyNotFoundException(strategiaId));
        return strategia.getTrades();
    }

    public boolean deleteStrategiaIfEmpty(UUID id) {
        Strategia strategia = strategiaRepository.findById(id)
                .orElseThrow(() -> new StrategyNotFoundException(id));

        if (strategia.getTrades().isEmpty()) {
            strategiaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Map<String, Long> countTradesPerStrategia() {
        return strategiaRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Strategia::getNome,
                        s -> (long) s.getTrades().size()
                ));
    }
}
