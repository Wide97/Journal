package com.marco.tradingjournal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "trader")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trader {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "valuta_id")
    private Valuta valuta;

    private double capitaleIniziale;
    private double capitaleAttuale;

    @OneToMany(mappedBy = "trader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trade> trades = new ArrayList<>();

    @OneToMany(mappedBy = "trader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Capitale> andamentoCapitale = new ArrayList<>();

}

