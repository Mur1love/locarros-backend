package com.example.locarros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("LOCADOR")
@Table(name = "locadores")
@Getter
@Setter
@NoArgsConstructor
public class Locador extends Usuario {

    @OneToMany(mappedBy = "locador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carro> carros = new ArrayList<>();
    public Locador(int id, String nome, String email, Endereco endereco, ArrayList<Carro> carros) {
        super(id, nome, email, endereco);
        this.carros = carros != null ? carros : new ArrayList<>();
    }
}
