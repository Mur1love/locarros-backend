package com.example.locarros.model;

import jakarta.persistence.*;

@Entity
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

    public Carro () {}

    public Carro(int carro_id, String marca, String modelo, String placa, Locador locador) {
        this.carro_id = carro_id;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.locador = locador;
    }

    public int getCarro_id() {
        return carro_id;
    }

    public void setCarro_id(int carro_id) {
        this.carro_id = carro_id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Locador getLocador() {
        return locador;
    }

    public void setLocador(Locador locador) {
        this.locador = locador;
    }
}
