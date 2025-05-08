package com.marco.tradingjournal.services;

import com.marco.tradingjournal.entities.Valuta;
import com.marco.tradingjournal.repositories.ValutaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ValutaService {

    private final ValutaRepository valutaRepository;

    public ValutaService(ValutaRepository valutaRepository) {
        this.valutaRepository = valutaRepository;
    }

    public List<Valuta> findAll() {
        return valutaRepository.findAll();
    }

    public Optional<Valuta> findById(UUID id) {
        return valutaRepository.findById(id);
    }

    public Valuta save(Valuta valuta) {
        return valutaRepository.save(valuta);
    }

    public void deleteById(UUID id) {
        valutaRepository.deleteById(id);
    }
}
