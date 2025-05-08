package com.marco.tradingjournal.exception;

public class InvalidCapitaleException extends RuntimeException {
    public InvalidCapitaleException(String message) {
        super("Capitale non valido: " + message);
    }
}
