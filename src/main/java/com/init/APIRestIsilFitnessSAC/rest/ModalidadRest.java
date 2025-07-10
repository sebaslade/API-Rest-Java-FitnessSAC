package com.init.APIRestIsilFitnessSAC.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.init.APIRestIsilFitnessSAC.entity.Modalidad;
import com.init.APIRestIsilFitnessSAC.repository.ModalidadRepository;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/modalidades")
public class ModalidadRest {
    @Autowired
    private ModalidadRepository modalidadRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<Modalidad>> getModalidades() {
        List<Modalidad> modalidades = modalidadRepository.findAll();
        return ResponseEntity.ok(modalidades);
    }

    //GET By Id
    @RequestMapping(value="{modalidadId}")
    public ResponseEntity<Modalidad> getModalidadById(@PathVariable("modalidadId") int id_modalidad) {
        Modalidad modalidad = modalidadRepository.findById(id_modalidad);
        return ResponseEntity.ok(modalidad);
    }
    
}
