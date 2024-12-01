package com.example.locarros.controller;

import com.example.locarros.dto.AluguelDTO;
import com.example.locarros.model.Aluguel;
import com.example.locarros.model.Locatario;
import com.example.locarros.repository.AluguelRepository;
import com.example.locarros.repository.LocatarioRepository;
import com.example.locarros.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private AluguelService aluguelService;


    @GetMapping
    public ResponseEntity<List<AluguelDTO>> listarAlugueis() {
        List<AluguelDTO> alugueisDTO = aluguelService.listarAlugueis();
        return ResponseEntity.ok(alugueisDTO);
    }


    @PostMapping("/cadastro")
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
    public ResponseEntity<AluguelDTO> getAluguelById(@PathVariable int id) {
        AluguelDTO aluguelDTO = aluguelService.buscarAluguelPorId(id);
        if (aluguelDTO != null) {
            return ResponseEntity.ok(aluguelDTO);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/atualizar/{id}")
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

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteAluguel(@PathVariable int id) {
        if (aluguelRepository.existsById(id)) {
            aluguelRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizarAluguel(@PathVariable int id) {
        AluguelDTO aluguelDTO = aluguelService.finalizarAluguel(id);
        if (aluguelDTO != null) {
            return ResponseEntity.ok(aluguelDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluguel não encontrado.");
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarAluguel(@PathVariable int id) {
        AluguelDTO aluguelDTO = aluguelService.cancelarAluguel(id);
        if (aluguelDTO != null) {
            return ResponseEntity.ok(aluguelDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluguel não encontrado.");
        }
    }

    @GetMapping("/locador/{locadorId}")
    public ResponseEntity<List<AluguelDTO>> getAlugueisByLocador(@PathVariable int locadorId) {
        List<AluguelDTO> alugueisDTO = aluguelService.buscarPorLocadorId(locadorId);
        return ResponseEntity.ok(alugueisDTO);
    }

    @GetMapping("/locatario/{locatarioId}")
    public ResponseEntity<List<AluguelDTO>> getAlugueisByLocatario(@PathVariable int locatarioId) {
        List<AluguelDTO> alugueisDTO = aluguelService.buscarPorLocatarioId(locatarioId);
        return ResponseEntity.ok(alugueisDTO);
    }

    @GetMapping("/carro/{carroId}")
    public ResponseEntity<List<AluguelDTO>> getAlugueisByCarro(@PathVariable int carroId) {
        List<AluguelDTO> alugueisDTO = aluguelService.buscarPorCarroId(carroId);
        return ResponseEntity.ok(alugueisDTO);
    }

}
