package com.example.locarros.controller;

import com.example.locarros.model.Aluguel;
import com.example.locarros.model.Locatario;
import com.example.locarros.repository.AluguelRepository;
import com.example.locarros.repository.LocatarioRepository;
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

    @Autowired
    private LocatarioRepository locatarioRepository;

    @PostMapping
    public ResponseEntity<Aluguel> createAluguel(@RequestBody Aluguel aluguel) {

        if (aluguel.getLocatario() != null && aluguel.getLocatario().getId() != 0) {
            Locatario locatario = locatarioRepository.findById(aluguel.getLocatario().getId())
                    .orElseThrow(() -> new RuntimeException("Locatário não encontrado"));
            aluguel.setLocatario(locatario);
        }


        Aluguel savedAluguel = aluguelRepository.save(aluguel);
        return ResponseEntity.ok().body(savedAluguel);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Aluguel> getAluguelById(@PathVariable int id) {
        Optional<Aluguel> aluguel = aluguelRepository.findById(id);
        return aluguel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Aluguel> updateAluguel(@PathVariable int id, @RequestBody Aluguel aluguelDetails) {
        Optional<Aluguel> optionalAluguel = aluguelRepository.findById(id);

        if (optionalAluguel.isPresent()) {
            Aluguel aluguel = optionalAluguel.get();


            if (aluguelDetails.getLocatario() != null && aluguelDetails.getLocatario().getId() != 0) {
                Locatario locatario = locatarioRepository.findById(aluguelDetails.getLocatario().getId())
                        .orElseThrow(() -> new RuntimeException("Locatário não encontrado"));
                aluguel.setLocatario(locatario);
            }


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


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(@PathVariable int id) {
        if (aluguelRepository.existsById(id)) {
            aluguelRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/locador/{locadorId}")
    public ResponseEntity<List<Aluguel>> getAlugueisByLocador(@PathVariable int locadorId) {
        List<Aluguel> alugueis = aluguelRepository.findByLocador_Id(locadorId);
        return ResponseEntity.ok(alugueis);
    }


    @GetMapping("/locatario/{locatarioId}")
    public ResponseEntity<List<Aluguel>> getAlugueisByLocatario(@PathVariable int locatarioId) {
        List<Aluguel> alugueis = aluguelRepository.findByLocatario_Id(locatarioId);
        return ResponseEntity.ok(alugueis);
    }


    @GetMapping("/carro/{carroId}")
    public ResponseEntity<List<Aluguel>> getAlugueisByCarro(@PathVariable int carroId) {
        List<Aluguel> alugueis = aluguelRepository.findByCarro_CarroId(carroId);
        return ResponseEntity.ok(alugueis);
    }

}
