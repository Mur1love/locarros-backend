package com.example.locarros.service;

import com.example.locarros.model.Aluguel;
import com.example.locarros.model.StatusAluguel;
import com.example.locarros.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public Aluguel criarAluguel(Aluguel aluguel) {
        aluguel.setStatus(StatusAluguel.ATIVO);
        return aluguelRepository.save(aluguel);
    }

    public List<Aluguel> listarAlugueis() {
        return aluguelRepository.findAll();
    }

    public Optional<Aluguel> buscarAluguelPorId(int id) {
        return aluguelRepository.findById(id);
    }

    public Aluguel atualizarAluguel(int id, Aluguel aluguelAtualizado) {

        Optional<Aluguel> aluguelExistente = aluguelRepository.findById(id);
        if (aluguelExistente.isPresent()) {
            Aluguel aluguel = aluguelExistente.get();
            aluguel.setCarro(aluguelAtualizado.getCarro());
            aluguel.setLocatario(aluguelAtualizado.getLocatario());
            aluguel.setLocador(aluguelAtualizado.getLocador());
            aluguel.setDataInicio(aluguelAtualizado.getDataInicio());
            aluguel.setDataFim(aluguelAtualizado.getDataFim());
            aluguel.setValor(aluguelAtualizado.getValor());


            return aluguelRepository.save(aluguel);
        } else {
            return null;
        }
    }

    public void deletarAluguel(int id) {
        aluguelRepository.deleteById(id);
    }

    public Aluguel finalizarAluguel(int id) {
        Optional<Aluguel> aluguelExistente = aluguelRepository.findById(id);
        if (aluguelExistente.isPresent()) {
            Aluguel aluguel = aluguelExistente.get();
            aluguel.setStatus(StatusAluguel.FINALIZADO);
            return aluguelRepository.save(aluguel);
        } else {
            return null;
        }
    }

    public Aluguel cancelarAluguel(int id) {
        Optional<Aluguel> aluguelExistente = aluguelRepository.findById(id);
        if (aluguelExistente.isPresent()) {
            Aluguel aluguel = aluguelExistente.get();
            aluguel.setStatus(StatusAluguel.CANCELADO);
            return aluguelRepository.save(aluguel);
        } else {
            return null;
        }
    }
}
