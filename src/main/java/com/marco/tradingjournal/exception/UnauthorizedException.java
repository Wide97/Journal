package com.marco.tradingjournal.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Non sei autorizzato a eseguire questa operazione.");
    }
}
