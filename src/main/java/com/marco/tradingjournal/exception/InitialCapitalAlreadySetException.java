package com.marco.tradingjournal.exception;

public class InitialCapitalAlreadySetException extends RuntimeException {
    public InitialCapitalAlreadySetException() {
        super("Il capitale iniziale è già stato impostato.");
    }
}
