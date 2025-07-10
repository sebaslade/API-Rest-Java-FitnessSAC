package com.init.APIRestIsilFitnessSAC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.APIRestIsilFitnessSAC.entity.Cliente;
import com.init.APIRestIsilFitnessSAC.entity.Reservas;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Integer> {
    Reservas findById(int id_reserva);
    List<Reservas> findByCliente(Cliente cliente);
}
