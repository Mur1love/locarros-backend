package com.example.locarros.model;

import jakarta.persistence.*;


import java.util.ArrayList;

@Entity
@DiscriminatorValue("LOCADOR")
public class Locador extends Usuario {

    @OneToMany(mappedBy = "locador")
    private ArrayList<Carro> carros;

    public Locador() {}

    public Locador(ArrayList<Carro> carros) {
        this.carros = carros;
    }

    public Locador(int id, String nome, String email, Endereco endereco, ArrayList<Carro> carros) {
        super(id, nome, email, endereco);
        this.carros = carros;
    }

    public ArrayList<Carro> getCarros() {
        return carros;
    }

    public void setCarros(ArrayList<Carro> carros) {
        this.carros = carros;
    }
}
