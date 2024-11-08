package com.example.locarros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "carros")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carro_id;
    private String marca;
    private String modelo;
    private String placa;

    @ManyToOne
    @JoinColumn(name = "locador_id")
    private Locador locador;

    public Carro(int carro_id, String marca, String modelo, String placa, Locador locador) {
        this.carro_id = carro_id;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.locador = locador;
    }
}
