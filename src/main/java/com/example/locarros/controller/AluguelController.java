package com.example.locarros.controller;

import com.example.locarros.model.Aluguel;
import com.example.locarros.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {
    @Autowired
    private AluguelRepository aluguelRepository;

    // Criar um novo aluguel - remover comentários depois
    @PostMapping
    public ResponseEntity<Aluguel> createAluguel(@RequestBody Aluguel aluguel) {
        Aluguel savedAluguel = aluguelRepository.save(aluguel);
        return ResponseEntity.ok().body(savedAluguel);
    }
    // Obter todos os aluguéis - remover comentários depois
    @GetMapping("/{id}")
    public ResponseEntity<Aluguel> getAluguelById(@PathVariable int id) {
        Optional<Aluguel> aluguel = aluguelRepository.findById(id);
        return aluguel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um aluguel existente - remover comentários depois
    @PutMapping("/{id}")
    public ResponseEntity<Aluguel> updateAluguel(@PathVariable int id, @RequestBody Aluguel aluguelDetails) {
        Optional<Aluguel> optionalAluguel = aluguelRepository.findById(id);

        if (optionalAluguel.isPresent()) {
            Aluguel aluguel = optionalAluguel.get();
            aluguel.setCarro(aluguelDetails.getCarro());
            aluguel.setLocatario(aluguelDetails.getLocatario());
            aluguel.setLocador(aluguelDetails.getLocador());
            aluguel.setDataInicio(aluguelDetails.getDataInicio());
            aluguel.setDataFim(aluguelDetails.getDataFim());
            aluguel.setValor(aluguelDetails.getValor());
            aluguel.setStatus(aluguelDetails.getStatus());

            Aluguel updatedAluguel = aluguelRepository.save(aluguel);
            return ResponseEntity.ok(updatedAluguel);
        }

        return ResponseEntity.notFound().build();
    }

    // Deletar um aluguel - remover comentários depois
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(@PathVariable int id) {
        if (aluguelRepository.existsById(id)) {
            aluguelRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Obter aluguéis por locador - remover comentários depois
    @GetMapping("/locador/{locadorId}")
    public ResponseEntity<List<Aluguel>> getAlugueisByLocador(@PathVariable int locadorId) {
        List<Aluguel> alugueis = aluguelRepository.findByLocador_Id(locadorId);
        return ResponseEntity.ok(alugueis);
    }

    // Obter aluguéis por locatário - remover comentários depois
    @GetMapping("/locatario/{locatarioId}")
    public ResponseEntity<List<Aluguel>> getAlugueisByLocatario(@PathVariable int locatarioId) {
        List<Aluguel> alugueis = aluguelRepository.findByLocatario_Id(locatarioId);
        return ResponseEntity.ok(alugueis);
    }

    // Obter aluguéis por carro - remover comentários depois
    @GetMapping("/carro/{carroId}")
    public ResponseEntity<List<Aluguel>> getAlugueisByCarro(@PathVariable int carroId) {
        List<Aluguel> alugueis = aluguelRepository.findByCarro_CarroId(carroId);
        return ResponseEntity.ok(alugueis);
    }

}
