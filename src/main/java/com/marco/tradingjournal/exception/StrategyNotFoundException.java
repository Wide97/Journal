package com.marco.tradingjournal.exception;

import java.util.UUID;

public class StrategyNotFoundException extends RuntimeException {
    public StrategyNotFoundException(UUID id) {
        super("Strategia con ID " + id + " non trovata.");
    }
}
