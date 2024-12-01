package com.example.locarros.service;

import com.example.locarros.dto.AluguelDTO;
import com.example.locarros.model.Aluguel;
import com.example.locarros.model.StatusAluguel;
import com.example.locarros.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public Aluguel criarAluguel(Aluguel aluguel) {
        aluguel.setStatus(StatusAluguel.ATIVO);
        return aluguelRepository.save(aluguel);
    }

    public List<AluguelDTO> listarAlugueis() {
        return aluguelRepository.findAll().stream()
                .map(this::converterParaDTO)
                .toList();
    }


    public AluguelDTO buscarAluguelPorId(int id) {
        return aluguelRepository.findById(id).map(this::converterParaDTO).orElse(null);
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

    public AluguelDTO finalizarAluguel(int id) {
        Aluguel aluguel = aluguelRepository.findById(id).orElse(null);

        if (aluguel != null) {
            aluguel.setStatus(StatusAluguel.FINALIZADO);
            aluguel = aluguelRepository.save(aluguel);

            return converterParaDTO(aluguel);
        }
        return null;
    }

    public AluguelDTO cancelarAluguel(int id) {

        Aluguel aluguel = aluguelRepository.findById(id).orElse(null);

        if (aluguel != null) {
            aluguel.setStatus(StatusAluguel.CANCELADO);
            aluguel = aluguelRepository.save(aluguel);

            return converterParaDTO(aluguel);
        }
        return null;
    }


    public List<AluguelDTO> buscarPorLocadorId(int locadorId) {
        List<Aluguel> alugueis = aluguelRepository.findByLocador_Id(locadorId);
        return alugueis.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public List<AluguelDTO> buscarPorLocatarioId(int locatarioId) {
        List<Aluguel> alugueis = aluguelRepository.findByLocatario_Id(locatarioId);
        return alugueis.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public List<AluguelDTO> buscarPorCarroId(int carroId) {
        List<Aluguel> alugueis = aluguelRepository.findByCarro_CarroId(carroId);
        return alugueis.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public AluguelDTO converterParaDTO(Aluguel aluguel) {
        return new AluguelDTO(
                aluguel.getAluguel_id(),
                aluguel.getCarro().getModelo(),
                aluguel.getCarro().getPlaca(),
                aluguel.getLocador().getNome(),
                aluguel.getDataInicio(),
                aluguel.getDataFim(),
                aluguel.getValor(),
                aluguel.getStatus().toString()
        );
    }
}
