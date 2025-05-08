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
public class ValutaDTO {
    private UUID id;
    private String codice;
    private String nome;
    private String simbolo;
}
