package com.example.locarros.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "alugueis")
@Getter
@Setter
@NoArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aluguel_id;

    @ManyToOne
    @JoinColumn(name = "carro_id", nullable = false)
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "locatario_id", nullable = false)
    @JsonBackReference
    private Locatario locatario;

    @ManyToOne
    @JoinColumn(name = "locador_id", nullable = false)
    private Locador locador;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double valor;

    @Enumerated(EnumType.STRING)
    private StatusAluguel status;

    public Aluguel(int aluguel_id, Carro carro, Locatario locatario, Locador locador, LocalDate dataInicio, LocalDate dataFim, double valor, StatusAluguel status) {
        this.aluguel_id = aluguel_id;
        this.carro = carro;
        this.locatario = locatario;
        this.locador = locador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.status = status;
    }
}
