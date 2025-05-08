package com.marco.tradingjournal.exception;

public class InvalidAssetException extends RuntimeException {
    public InvalidAssetException(String message) {
        super("Asset non valido: " + message);
    }
}
