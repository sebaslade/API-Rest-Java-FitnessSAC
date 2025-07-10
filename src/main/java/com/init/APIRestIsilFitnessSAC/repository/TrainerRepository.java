package com.init.APIRestIsilFitnessSAC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.APIRestIsilFitnessSAC.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    Trainer findById(int id_trainer);
}
