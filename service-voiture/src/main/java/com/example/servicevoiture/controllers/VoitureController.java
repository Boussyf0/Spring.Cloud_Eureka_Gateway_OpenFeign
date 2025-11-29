package com.example.servicevoiture.controllers;

import com.example.servicevoiture.clients.ClientRestClient;
import com.example.servicevoiture.entities.Voiture;
import com.example.servicevoiture.models.Client;
import com.example.servicevoiture.repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoitureController {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private ClientRestClient clientRestClient;

    @GetMapping("/voitures")
    public List<Voiture> findAll() {
        List<Voiture> voitures = voitureRepository.findAll();
        voitures.forEach(voiture -> {
            voiture.setClient(clientRestClient.findClientById(voiture.getClientId()));
        });
        return voitures;
    }

    @GetMapping("/voiture/{id}")
    public ResponseEntity<Voiture> findById(@PathVariable Long id) {
        return voitureRepository.findById(id)
                .map(voiture -> {
                    voiture.setClient(clientRestClient.findClientById(voiture.getClientId()));
                    return ResponseEntity.ok(voiture);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/voitures/client/{clientId}")
    public List<Voiture> findByClient(@PathVariable Long clientId) {
        List<Voiture> voitures = voitureRepository.findByClientId(clientId);
        Client client = clientRestClient.findClientById(clientId);
        voitures.forEach(voiture -> voiture.setClient(client));
        return voitures;
    }

    @PostMapping("/voiture")
    public ResponseEntity<Voiture> create(@RequestBody Voiture voiture) {
        Voiture savedVoiture = voitureRepository.save(voiture);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVoiture);
    }

    @PutMapping("/voiture/{id}")
    public ResponseEntity<Voiture> update(@PathVariable Long id, @RequestBody Voiture voitureDetails) {
        return voitureRepository.findById(id)
                .map(voiture -> {
                    voiture.setMarque(voitureDetails.getMarque());
                    voiture.setMatricule(voitureDetails.getMatricule());
                    voiture.setModel(voitureDetails.getModel());
                    voiture.setClientId(voitureDetails.getClientId());
                    Voiture updatedVoiture = voitureRepository.save(voiture);
                    return ResponseEntity.ok(updatedVoiture);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/voiture/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return voitureRepository.findById(id)
                .map(voiture -> {
                    voitureRepository.delete(voiture);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}