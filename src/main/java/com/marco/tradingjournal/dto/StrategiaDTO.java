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
public class StrategiaDTO {
    private UUID id;
    private String nome;
    private String descrizione;
}
