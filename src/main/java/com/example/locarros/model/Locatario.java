package com.example.locarros.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("LOCATARIO")
@Table(name = "locatarios")
@Getter
@Setter
@NoArgsConstructor
public class Locatario extends Usuario {

    @OneToMany(mappedBy = "locatario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Aluguel> alugueis = new ArrayList<>();

    public Locatario(int id, String nome, String email, Endereco endereco, ArrayList<Aluguel> alugueis) {
        super(id, nome, email, endereco);
        this.alugueis = alugueis != null ? alugueis : new ArrayList<>();
    }

}
