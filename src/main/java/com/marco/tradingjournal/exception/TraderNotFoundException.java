package com.marco.tradingjournal.exception;

import java.util.UUID;

public class TraderNotFoundException extends RuntimeException {
    public TraderNotFoundException(UUID id) {
        super("Trader con ID " + id + " non trovato.");
    }
}
