package com.marco.tradingjournal.exception;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException(String message) {
        super("Valuta non valida: " + message);
    }
}
