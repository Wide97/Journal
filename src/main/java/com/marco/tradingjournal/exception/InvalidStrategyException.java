package com.marco.tradingjournal.exception;

public class InvalidStrategyException extends RuntimeException {
    public InvalidStrategyException(String message) {
        super("Strategia non valida: " + message);
    }
}
