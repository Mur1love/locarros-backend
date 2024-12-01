package com.example.locarros.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "carro_id")
    private Integer carroId;

    private String marca;
    private String modelo;
    private String placa;

    @ManyToOne
    @JoinColumn(name = "locador_id")
    @JsonBackReference
    private Locador locador;

    public Carro(int carroId, String marca, String modelo, String placa, Locador locador) {
        this.carroId = carroId;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.locador = locador;
    }
}
