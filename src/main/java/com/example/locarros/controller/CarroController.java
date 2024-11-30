package com.example.locarros.controller;

import com.example.locarros.model.Carro;
import com.example.locarros.model.Locador;
import com.example.locarros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/carros")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> listarCarros() {
        List<Carro> carros = carroService.buscarTodosCarros();
        return ResponseEntity.ok(carros);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Carro> criarCarro(@RequestBody Carro carro) {
        Carro novoCarro = carroService.salvarCarro(carro);
        return ResponseEntity.ok(novoCarro);
    }
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable int id) {
        Optional<Carro> carro  = carroService.buscarCarroPorId(id);
        if(carro.isPresent()) {
            carroService.deletarCarro(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carro> atualizarcarro(@PathVariable int id, @RequestBody Carro carroAtualizado) {
        Optional<Carro> carroExistente = carroService.buscarCarroPorId(id);
        if (carroExistente.isPresent()) {
            Carro carroAtualizadoDb = carroService.atualizarCarro(id, carroAtualizado);
            return ResponseEntity.ok(carroAtualizadoDb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
