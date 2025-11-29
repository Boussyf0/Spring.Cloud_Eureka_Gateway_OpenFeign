package com.example.serviceclient.repositories;

import com.example.serviceclient.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Client
 *
 * JpaRepository<Client, Long> fournit automatiquement les méthodes CRUD :
 * - findAll() : récupère tous les clients
 * - findById(Long id) : récupère un client par son ID
 * - save(Client client) : sauvegarde ou met à jour un client
 * - deleteById(Long id) : supprime un client par son ID
 * - count() : compte le nombre de clients
 * - existsById(Long id) : vérifie si un client existe
 *
 * Vous pouvez également ajouter des méthodes personnalisées :
 * - List<Client> findByNom(String nom);
 * - List<Client> findByAgeGreaterThan(Float age);
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Pas besoin de code ! Spring Data JPA génère tout automatiquement
}