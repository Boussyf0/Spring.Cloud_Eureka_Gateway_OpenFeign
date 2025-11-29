package com.example.serviceclient.controllers;

import com.example.serviceclient.entities.Client;
import com.example.serviceclient.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour gérer les clients
 *
 * @RestController - Combine @Controller + @ResponseBody (renvoie automatiquement du JSON)
 *
 * Endpoints exposés :
 * - GET  /clients       : Liste tous les clients
 * - GET  /client/{id}   : Récupère un client par son ID
 * - POST /client        : Crée un nouveau client
 * - PUT  /client/{id}   : Met à jour un client existant
 * - DELETE /client/{id} : Supprime un client
 */
@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Récupère tous les clients
     * GET http://localhost:8088/clients
     */
    @GetMapping("/clients")
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    /**
     * Récupère un client par son ID
     * GET http://localhost:8088/client/1
     */
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(client -> ResponseEntity.ok(client))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouveau client
     * POST http://localhost:8088/client
     * Body: {"nom": "John Doe", "age": 30.0}
     */
    @PostMapping("/client")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    /**
     * Met à jour un client existant
     * PUT http://localhost:8088/client/1
     * Body: {"nom": "Jane Doe", "age": 35.0}
     */
    @PutMapping("/client/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client clientDetails) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setNom(clientDetails.getNom());
                    client.setAge(clientDetails.getAge());
                    Client updatedClient = clientRepository.save(client);
                    return ResponseEntity.ok(updatedClient);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime un client
     * DELETE http://localhost:8088/client/1
     */
    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}