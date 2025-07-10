package com.init.APIRestIsilFitnessSAC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.APIRestIsilFitnessSAC.entity.Modalidad;

@Repository
public interface ModalidadRepository extends JpaRepository<Modalidad, Integer> {
    Modalidad findById(int id_modalidad);
}
