package com.init.APIRestIsilFitnessSAC.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.APIRestIsilFitnessSAC.entity.Cliente;
import com.init.APIRestIsilFitnessSAC.repository.ClienteRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClienteRest {
    @Autowired
    private ClienteRepository clienteRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }
    
    //GET By Id
    @RequestMapping(value="{clienteId}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("clienteId") int id_cliente) {
        Cliente cliente = clienteRepository.findById(id_cliente);
        return ResponseEntity.ok(cliente);
    }

    //PUT
    @PutMapping
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(cliente.getId_cliente());
        existingCliente.setNombre(cliente.getNombre());
        existingCliente.setEmail(cliente.getEmail());
        existingCliente.setPassword(cliente.getPassword());
        existingCliente.setEstado(cliente.getEstado());
        Cliente updatedCliente = clienteRepository.save(existingCliente);
        return ResponseEntity.ok(updatedCliente);
    }

    //DELETE
    @DeleteMapping(value="{clienteId}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("clienteId") int id_cliente) {
        clienteRepository.deleteById(id_cliente);
        return ResponseEntity.ok(null);
    }

    //GET By Email and Password => Iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente request) {
        Cliente clienteExistente = clienteRepository.findByEmail(request.getEmail());

        if (clienteExistente == null) {
            return ResponseEntity.status(404).body("El usuario no está registrado");
        }

        Cliente cliente = clienteRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());

        if (cliente == null) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        cliente.setPassword(null); // opcional: ocultar contraseña

        return ResponseEntity.ok(cliente);
    }
    
}
