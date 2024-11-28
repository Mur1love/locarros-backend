package com.example.locarros.repository;

import com.example.locarros.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {
    List<Aluguel> findByLocador_Id(int locadorId);
    List<Aluguel> findByLocatario_Id(int locatarioId);
    List<Aluguel> findByCarro_CarroId(int carroId);
}
