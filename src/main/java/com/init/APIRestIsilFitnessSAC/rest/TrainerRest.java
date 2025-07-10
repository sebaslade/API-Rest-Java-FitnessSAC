package com.init.APIRestIsilFitnessSAC.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.APIRestIsilFitnessSAC.entity.Trainer;
import com.init.APIRestIsilFitnessSAC.repository.TrainerRepository;

@RestController
@RequestMapping("/trainers")
public class TrainerRest {
    @Autowired
    private TrainerRepository trainerRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<Trainer>> getTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        return ResponseEntity.ok(trainers);
    }
    //GET by Id
    @RequestMapping(value="{trainerId}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable("trainerId") int id_trainer) {
        Trainer trainer = trainerRepository.findById(id_trainer);
        return ResponseEntity.ok(trainer);
    }
}
