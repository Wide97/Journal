package com.marco.tradingjournal.exception;

import java.util.UUID;

public class CapitaleNotFoundException extends RuntimeException {
    public CapitaleNotFoundException(UUID id) {
        super("Capitale con ID " + id + " non trovato.");
    }
}
