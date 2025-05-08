package com.marco.tradingjournal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "valuta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Valuta {

    @Id
    @GeneratedValue
    private UUID id;

    private String codice;  // Es. EUR, USD
    private String nome;    // Euro, Dollaro Statunitense
    private String simbolo; // €, $
}
