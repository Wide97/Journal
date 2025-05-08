package com.marco.tradingjournal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "trade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate dataAcquisto;
    private LocalDate dataVendita;

    private LocalTime oraAcquisto;
    private LocalTime oraVendita;

    private double lotti;
    private double leva;

    private double costoApertura;
    private double costoChiusura;

    private double profittoNetto;

    @Enumerated(EnumType.STRING)
    private TipoTrade tipo; // LONG o SHORT

    @Enumerated(EnumType.STRING)
    private EsitoTrade esito; // PROFITTO, PERDITA, BREAK_EVEN

    @ManyToOne
    @JoinColumn(name = "strategia_id")
    private Strategia strategia;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "trader_id")
    private Trader trader;
}
