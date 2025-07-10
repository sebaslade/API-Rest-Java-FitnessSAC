package com.init.APIRestIsilFitnessSAC.rest;

import java.util.List;
import java.util.Map;

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
    @PutMapping("/{clienteId}")
    public ResponseEntity<?> updateCliente(@PathVariable("clienteId") int id_cliente, @RequestBody Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(id_cliente);
        if (clienteExistente == null) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteRepository.save(clienteExistente);
        return ResponseEntity.ok(clienteExistente);
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
    
    //POST -> Registrar Cliente
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        // Cliente registrado debe tener email único
        Cliente existente = clienteRepository.findByEmail(cliente.getEmail());
        if (existente != null) {
            return ResponseEntity.status(409).body("El email ya está registrado");
        }

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio");
        }
        if (cliente.getPassword() == null || cliente.getPassword().length() < 6) {
            return ResponseEntity.badRequest().body("La contraseña debe tener al menos 6 caracteres");
        }

        cliente.setEstado("Activo");

        Cliente nuevo = clienteRepository.save(cliente);
        nuevo.setPassword(null);

        return ResponseEntity.ok(nuevo);
    }

    //Actualizar password
    @PutMapping("/{clienteId}/cambiarPassword")
    public ResponseEntity<?> cambiarPassword(@PathVariable("clienteId") int id_cliente, @RequestBody Map<String, String> passwordMap) {
        Cliente cliente = clienteRepository.findById(id_cliente);
        if (cliente == null) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }

        String passwordActual = passwordMap.get("passwordActual");
        String nuevaPassword = passwordMap.get("nuevaPassword");

        if (!cliente.getPassword().equals(passwordActual)) {
            return ResponseEntity.status(401).body("La contraseña actual no es correcta");
        }

        if (nuevaPassword == null || nuevaPassword.length() < 6) {
            return ResponseEntity.badRequest().body("La nueva contraseña debe tener al menos 6 caracteres");
        }

        cliente.setPassword(nuevaPassword);
        clienteRepository.save(cliente);
        return ResponseEntity.ok("Contraseña actualizada exitosamente");
    }
}
