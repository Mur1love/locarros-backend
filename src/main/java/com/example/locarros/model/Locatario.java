package com.example.locarros.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;

@Entity
@DiscriminatorValue("LOCATARIO")
public class Locatario extends Usuario {

    @OneToMany(mappedBy = "locatario")
    private ArrayList<Aluguel> alugueis;

    public Locatario() {
    }

    public Locatario(ArrayList<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    public Locatario(int id, String nome, String email, Endereco endereco, ArrayList<Aluguel> alugueis) {
        super(id, nome, email, endereco);
        this.alugueis = alugueis;
    }

    public ArrayList<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(ArrayList<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }
}
