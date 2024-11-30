package com.example.locarros.repository;

import com.example.locarros.model.Locador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  LocadorRepository extends JpaRepository<Locador, Integer> {
}
