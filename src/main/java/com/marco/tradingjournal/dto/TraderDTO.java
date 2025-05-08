package com.marco.tradingjournal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TraderDTO {
    private UUID id;
    private String username;
    private String email;
    private String password;  // solo in input, valuta se ignorarlo in response
    private UUID valutaId;
    private double capitaleIniziale;
    private double capitaleAttuale;
}
