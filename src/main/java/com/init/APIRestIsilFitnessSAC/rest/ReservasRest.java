package com.init.APIRestIsilFitnessSAC.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.APIRestIsilFitnessSAC.entity.Cliente;
import com.init.APIRestIsilFitnessSAC.entity.Entrenamiento;
import com.init.APIRestIsilFitnessSAC.entity.Reservas;
import com.init.APIRestIsilFitnessSAC.repository.ReservasRepository;

@CrossOrigin(origins = "*")
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

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody Map<String, Object> datos) {
        try {
            if (!datos.containsKey("id_cliente") || !datos.containsKey("id_entrenamiento") || !datos.containsKey("estado")) {
                return ResponseEntity.badRequest().body("Faltan campos obligatorios");
            }

            int idCliente = ((Number) datos.get("id_cliente")).intValue();
            int idEntrenamiento = ((Number) datos.get("id_entrenamiento")).intValue();
            String estado = datos.get("estado").toString();

            Cliente cliente = new Cliente();
            cliente.setId_cliente(idCliente);

            Entrenamiento entrenamiento = new Entrenamiento();
            entrenamiento.setIdEntrenamiento(idEntrenamiento);

            Reservas reserva = new Reservas();
            reserva.setCliente(cliente);
            reserva.setEntrenamiento(entrenamiento);
            reserva.setEstado(estado);
            reserva.setFecha_reserva(LocalDate.now());

            Reservas nueva = reservasRepository.save(reserva);
            return ResponseEntity.ok(nueva);

        } catch (Exception e) {
            e.printStackTrace(); // Para ver el error en consola
            return ResponseEntity.status(500).body("Error interno al crear la reserva: " + e.getMessage());
        }
    }

}
