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
@Table(name = "asset")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String simbolo;

    @Column(nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trade> trades = new ArrayList<>();

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", simbolo='" + simbolo + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}

