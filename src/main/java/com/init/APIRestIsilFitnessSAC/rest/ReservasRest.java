package com.init.APIRestIsilFitnessSAC.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.APIRestIsilFitnessSAC.entity.Reservas;
import com.init.APIRestIsilFitnessSAC.repository.ReservasRepository;

@RestController
@RequestMapping("/reservas")
public class ReservasRest {
    @Autowired
    private ReservasRepository reservasRepository;

    @GetMapping
    public ResponseEntity<List<Reservas>> getReservas() {
        List<Reservas> reservas = reservasRepository.findAll();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{reservaId}")
    public ResponseEntity<Reservas> getReserva(@PathVariable("reservaId") int id_reserva) {
        Reservas reserva = reservasRepository.findById(id_reserva);
        return ResponseEntity.ok(reserva);
    }
}
