package com.example.locarros.service;

import com.example.locarros.model.Carro;
import com.example.locarros.model.Locador;
import com.example.locarros.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> buscarTodosCarros() {
        return carroRepository.findAll();
    }

    public Optional<Carro> buscarCarroPorId(Integer id) {
        return carroRepository.findById(id);
    }

    public Carro salvarCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    public void deletarCarro(Integer id) {
        carroRepository.deleteById(id);
    }

    public Carro atualizarCarro(Integer id, Carro carroAtualizado) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if (carroOptional.isPresent()) {
            Carro carroExistente = carroOptional.get();

            if (carroAtualizado.getMarca() != null) {
                carroExistente.setMarca(carroAtualizado.getMarca());
            }
            if (carroAtualizado.getModelo() != null) {
                carroExistente.setModelo(carroAtualizado.getModelo());
            }
            if (carroAtualizado.getPlaca() != null) {
                carroExistente.setPlaca(carroAtualizado.getPlaca());
            }
            if (carroAtualizado.getLocador() != null) {
                carroExistente.setLocador(carroAtualizado.getLocador());
            }
            return carroRepository.save(carroExistente);
        } else {
            throw new RuntimeException("Carro com ID " + id + " não encontrado.");
        }
    }

    public List<Carro> buscarCarrosPorMarca(String marca) {
        return carroRepository.findByMarca(marca);
    }

    public List<Carro> buscarCarrosPorModelo(String modelo) {
        return carroRepository.findByModelo(modelo);
    }

    public List<Carro> buscarCarrosPorPlaca(String placa) {
        return carroRepository.findByPlaca(placa);
    }

    public List<Carro> buscarCarrosPorLocador(Locador locador) {
        return carroRepository.findByLocador(locador);
    }
}
