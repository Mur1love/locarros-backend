package com.example.locarros.service;

import com.example.locarros.model.Locatario;
import com.example.locarros.repository.LocatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocatarioService {

    @Autowired
    private LocatarioRepository locatarioRepository;

    public Locatario cadastrarLocatario(Locatario locatario) {
        return locatarioRepository.save(locatario);
    }

    public void excluirLocatario(int id) {
        locatarioRepository.deleteById(id);
    }

    public Optional<Locatario> buscarLocatario(int id) {
        return locatarioRepository.findById(id);
    }

    public List<Locatario> listarLocatarios() {
        return locatarioRepository.findAll();
    }

    public Locatario atualizarLocatario(int id, Locatario locatarioAlterado) {
        Optional<Locatario> locatarioAntigoOpt = locatarioRepository.findById(id);

        if (locatarioAntigoOpt.isPresent()) {
            Locatario locatarioAntigo = locatarioAntigoOpt.get();

            locatarioAntigo.setNome(locatarioAlterado.getNome());
            locatarioAntigo.setEmail(locatarioAlterado.getEmail());
            locatarioAntigo.setEndereco(locatarioAlterado.getEndereco());

            return locatarioRepository.save(locatarioAntigo);
        }
        else{
            throw new RuntimeException("Locatário com ID " + id + " não encontrado");
        }

    }

}
