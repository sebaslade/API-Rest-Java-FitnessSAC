package com.init.APIRestIsilFitnessSAC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.APIRestIsilFitnessSAC.entity.Entrenamiento;

@Repository
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Integer> {
    Entrenamiento findById(int id_entrenamiento);
}
