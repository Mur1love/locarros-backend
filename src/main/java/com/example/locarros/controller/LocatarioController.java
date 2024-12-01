package com.example.locarros.controller;

import com.example.locarros.service.LocatarioService;
import org.springframework.stereotype.Controller;
import com.example.locarros.model.Locatario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/locatarios")
@Controller
public class LocatarioController {

    @Autowired
    private LocatarioService locatarioService;

    @GetMapping
    public ResponseEntity<List<Locatario>> listarLocatarios() {
        List<Locatario> locatarios = locatarioService.listarLocatarios();
        return ResponseEntity.ok(locatarios);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Locatario> criarLocatario(@RequestBody Locatario locatario) {
        Locatario novoLocatario = locatarioService.cadastrarLocatario(locatario);
        return ResponseEntity.ok(novoLocatario);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deletarLocatario(@PathVariable int id) {
        Optional<Locatario> locatario = locatarioService.buscarLocatario(id);
        if (locatario.isPresent()) {
            locatarioService.excluirLocatario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();}
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Locatario> atualizarLocatario(@PathVariable int id, @RequestBody Locatario locatarioAtualizado) {
        Optional<Locatario> locatarioExistente = locatarioService.buscarLocatario(id);
        if (locatarioExistente.isPresent()) {
            Locatario locatarioAtualizadoDb = locatarioService.atualizarLocatario(id, locatarioAtualizado);
            return ResponseEntity.ok(locatarioAtualizadoDb);
        } else {
            return ResponseEntity.notFound().build();

        }
    }
}
