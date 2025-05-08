package com.marco.tradingjournal.exception;

public class InvalidTradeException extends RuntimeException {
    public InvalidTradeException(String message) {
        super("Trade non valido: " + message);
    }
}
