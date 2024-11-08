package com.example.locarros.service;

import com.example.locarros.model.Carro;
import com.example.locarros.model.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public Carro salvarCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    public void deletarCarro(int id) {
        carroRepository.deleteById(id);
    }

    public Carro buscarCarroPorId(int id) {
        return carroRepository.findById(id).orElse(null);
    }

}
