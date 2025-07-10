package com.init.APIRestIsilFitnessSAC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.APIRestIsilFitnessSAC.entity.Reservas;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Integer> {
    Reservas findById(int id_reserva);
    //Reservas findByClienteId(Cliente cliente);
}
