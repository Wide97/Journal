package com.marco.tradingjournal.dto;

import com.marco.tradingjournal.entities.TipoTrade;
import com.marco.tradingjournal.entities.EsitoTrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeDTO {

    private UUID id;

    private LocalDate dataAcquisto;
    private LocalTime oraAcquisto;
    private LocalDate dataVendita;
    private LocalTime oraVendita;

    private double lotti;
    private double leva;

    private double costoApertura;
    private double costoChiusura;

    private double profittoNetto;

    private TipoTrade tipo;       // enum: LONG, SHORT
    private EsitoTrade esito;     // enum: PROFITTO, STOP_LOSS, BREAK_EVEN

    private UUID strategiaId;
    private UUID assetId;
    private UUID traderId;
}
