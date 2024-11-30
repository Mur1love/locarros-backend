package com.example.locarros.repository;


import com.example.locarros.model.Carro;
import com.example.locarros.model.Locador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Integer > {
    List<Carro> findByMarca(String marca);
    List<Carro> findByModelo(String modelo);
    List<Carro> findByPlaca(String placa);
    List<Carro> findByLocador(Locador locador); //Retorna todos os carros de um locador específico
}
