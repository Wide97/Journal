package com.marco.tradingjournal.exception;

import java.util.UUID;

public class TradeNotFoundException extends RuntimeException {
    public TradeNotFoundException(UUID id) {
        super("Trade con ID " + id + " non trovato.");
    }
}
