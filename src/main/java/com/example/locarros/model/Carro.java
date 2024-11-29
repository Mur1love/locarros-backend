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
    @Column(name = "carro_id") // Mapeando explicitamente a coluna no banco de dados
    private int carroId;  // Alterando o nome da variável para "carroId" para seguir a convenção Java

    private String marca;
    private String modelo;
    private String placa;

    @ManyToOne
    @JoinColumn(name = "locador_id")
    private Locador locador;

    public Carro(int carroId, String marca, String modelo, String placa, Locador locador) {
        this.carroId = carroId;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.locador = locador;
    }
}
