package com.example.locarros.controller;

import com.example.locarros.model.Locador;
import com.example.locarros.service.LocadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/locadores")
public class LocadorController {

    @Autowired
    private LocadorService locadorService;

    @GetMapping
    public ResponseEntity<List<Locador>> listarLocadores() {
        List<Locador> locadores = locadorService.listarLocadores();
        return ResponseEntity.ok(locadores);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Locador> criarLocador(@RequestBody Locador locador) {
        Locador novoLocador = locadorService.createLocador(locador);
        return ResponseEntity.ok(novoLocador);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deletarLocador(@PathVariable int id) {
        Optional<Locador> locador  = locadorService.buscarLocadorPorId(id);
        if(locador.isPresent()) {
            locadorService.deletarLocador(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Locador> atualizarLocador(@PathVariable int id, @RequestBody Locador locadorAtualizado) {
        Optional<Locador> locadorExistente = locadorService.buscarLocadorPorId(id);
        if (locadorExistente.isPresent()) {
            Locador locadorAtualizadoDb = locadorService.atualizarLocador(id, locadorAtualizado);
            return ResponseEntity.ok(locadorAtualizadoDb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
