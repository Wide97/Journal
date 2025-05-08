package com.marco.tradingjournal.exception;

import java.util.UUID;

public class AssetNotFoundException extends RuntimeException {
    public AssetNotFoundException(UUID id) {
        super("Asset con ID " + id + " non trovato.");
    }
}
