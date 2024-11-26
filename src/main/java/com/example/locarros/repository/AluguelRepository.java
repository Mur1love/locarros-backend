package com.example.locarros.repository;

import com.example.locarros.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {
    // Buscar aluguéis por locador
    List<Aluguel> findByLocador_Id(int locadorId);

    // Buscar aluguéis por locatário
    List<Aluguel> findByLocatario_Id(int locatarioId);

    // Buscar aluguéis por carro
    List<Aluguel> findByCarro_Id(int carroId);
}
