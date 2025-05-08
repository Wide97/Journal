package com.marco.tradingjournal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CapitaleDTO {
    private UUID id;
    private LocalDate data;
    private double valore;
    private double variazione;
    private UUID traderId;
}
