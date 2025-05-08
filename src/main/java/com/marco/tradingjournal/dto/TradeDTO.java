package com.marco.tradingjournal.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class TradeDTO {
    private UUID id;
    private LocalDate dataAcquisto;
    private LocalTime oraAcquisto;
    private LocalDate dataVendita;
    private LocalTime oraVendita;
    private String asset;
    private double leva;
    private double lottaggio;
    private UUID strategia;
    private String tipologia; // LONG o SHORT
    private double aperturaCost;
    private double chiusuraCost;
    private String esito; // PROFITTO / STOP_LOSS / BREAK_EVEN
    private double profitto;
    private UUID traderId;

    // Getters e Setters
}
