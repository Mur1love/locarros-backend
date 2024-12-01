package com.example.locarros.dto;

import java.time.LocalDate;

public class AluguelDTO {
        private int aluguelId;
        private String carroModelo;
        private String carroPlaca;
        private String locadorNome;
        private LocalDate dataInicio;
        private LocalDate dataFim;
        private double valor;
        private String status;

        public AluguelDTO(int aluguelId, String carroModelo, String carroPlaca, String locadorNome, LocalDate dataInicio, LocalDate dataFim, double valor, String status) {
            this.aluguelId = aluguelId;
            this.carroModelo = carroModelo;
            this.carroPlaca = carroPlaca;
            this.locadorNome = locadorNome;
            this.dataInicio = dataInicio;
            this.dataFim = dataFim;
            this.valor = valor;
            this.status = status;
        }


    public int getAluguelId() {
        return aluguelId;
    }

    public void setAluguelId(int aluguelId) {
        this.aluguelId = aluguelId;
    }

    public String getCarroModelo() {
        return carroModelo;
    }

    public void setCarroModelo(String carroModelo) {
        this.carroModelo = carroModelo;
    }

    public String getCarroPlaca() {
        return carroPlaca;
    }

    public void setCarroPlaca(String carroPlaca) {
        this.carroPlaca = carroPlaca;
    }

    public String getLocadorNome() {
        return locadorNome;
    }

    public void setLocadorNome(String locadorNome) {
        this.locadorNome = locadorNome;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
