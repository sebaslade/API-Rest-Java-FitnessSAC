package com.init.APIRestIsilFitnessSAC.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.APIRestIsilFitnessSAC.entity.Entrenamiento;
import com.init.APIRestIsilFitnessSAC.repository.EntrenamientoRepository;

@RestController
@RequestMapping("/entrenamientos")
public class EntrenamientoRest {
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;
    
    @GetMapping
    public ResponseEntity<List<Entrenamiento>> getEntrenamientos() {
        List<Entrenamiento> entrenamientos = entrenamientoRepository.findAll();
        return ResponseEntity.ok(entrenamientos);
    }

    @GetMapping("/{entrenamientoId}")
    public ResponseEntity<Entrenamiento> getEntrenamientoById(@PathVariable int entrenamientoId) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(entrenamientoId);
        return ResponseEntity.ok(entrenamiento);
    }
}
