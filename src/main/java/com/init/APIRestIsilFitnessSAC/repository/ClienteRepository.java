package com.init.APIRestIsilFitnessSAC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.init.APIRestIsilFitnessSAC.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findById(int id_cliente);
    Cliente findByEmail(String email);
    Cliente findByEmailAndPassword(String email, String password);
}
