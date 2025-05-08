package com.marco.tradingjournal.exception;

import java.util.UUID;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(UUID id) {
        super("Valuta con ID " + id + " non trovata.");
    }
}
