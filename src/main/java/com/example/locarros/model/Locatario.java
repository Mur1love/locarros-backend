package com.example.locarros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@DiscriminatorValue("LOCATARIO")
@Table(name = "locatarios")

@Getter
@Setter
@NoArgsConstructor
public class Locatario extends Usuario {

    @OneToMany(mappedBy = "locatario")
    private ArrayList<Aluguel> alugueis;

    public Locatario(int id, String nome, String email, Endereco endereco, ArrayList<Aluguel> alugueis) {
        super(id, nome, email, endereco);
        this.alugueis = alugueis;
    }

}
