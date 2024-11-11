package com.example.locarros.service;

import com.example.locarros.model.Locador;
import com.example.locarros.repository.LocadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocadorService {

    @Autowired
    private LocadorRepository locadorRepository;

    public Locador createLocador(Locador locador) {
        return locadorRepository.save(locador);
    }

    public void deletarLocador(int id) {
        locadorRepository.deleteById(id);
    }

    public Optional<Locador> buscarLocadorPorId(int id) {
        return locadorRepository.findById(id);
    }

    public List<Locador> listarLocadores() {
        return locadorRepository.findAll();
    }

    public Locador atualizarLocador(int id, Locador locadorAtualizado) {
        Optional<Locador> locadorExistenteOpt = locadorRepository.findById(id);

        if (locadorExistenteOpt.isPresent()) {
            Locador locadorExistente = locadorExistenteOpt.get();

            locadorExistente.setNome(locadorAtualizado.getNome());
            locadorExistente.setEmail(locadorAtualizado.getEmail());
            locadorExistente.setEndereco(locadorAtualizado.getEndereco());

            return locadorRepository.save(locadorExistente);
        } else {
            throw new RuntimeException("Locador com ID " + id + " não encontrado");
        }
    }

}
