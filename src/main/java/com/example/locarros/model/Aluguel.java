package com.example.locarros.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aluguel_id;

    @ManyToOne
    @JoinColumn(name = "carro_id", nullable = false)
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "locatario_id", nullable = false)
    private Usuario locatario;

    @ManyToOne
    @JoinColumn(name = "locador_id", nullable = false)
    private Locador locador;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double valor;

    @Enumerated(EnumType.STRING)
    private StatusAluguel status;

    public Aluguel(int aluguel_id, Carro carro, Usuario locatario, Locador locador, LocalDate dataInicio, LocalDate dataFim, double valor, StatusAluguel status) {
        this.aluguel_id = aluguel_id;
        this.carro = carro;
        this.locatario = locatario;
        this.locador = locador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.status = status;
    }

    public Aluguel() {
    }

    public int getAluguel_id() {
        return aluguel_id;
    }

    public void setAluguel_id(int aluguel_id) {
        this.aluguel_id = aluguel_id;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Usuario getLocatario() {
        return locatario;
    }

    public void setLocatario(Usuario locatario) {
        this.locatario = locatario;
    }

    public Locador getLocador() {
        return locador;
    }

    public void setLocador(Locador locador) {
        this.locador = locador;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public StatusAluguel getStatus() {
        return status;
    }

    public void setStatus(StatusAluguel status) {
        this.status = status;
    }
}
