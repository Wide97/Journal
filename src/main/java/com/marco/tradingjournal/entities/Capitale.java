package com.marco.tradingjournal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "capitale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Capitale {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private double valore;

    private double variazione; // differenza rispetto al giorno precedente (opzionale)

    @ManyToOne
    @JoinColumn(name = "trader_id")
    private Trader trader;
}
