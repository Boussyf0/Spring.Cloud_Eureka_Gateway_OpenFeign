package com.example.serviceclient.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité JPA représentant un Client
 *
 * @Entity - Indique que cette classe est une entité JPA (table dans la base)
 * @Data - Lombok génère automatiquement getters, setters, toString, equals, hashCode
 * @AllArgsConstructor - Lombok génère un constructeur avec tous les champs
 * @NoArgsConstructor - Lombok génère un constructeur sans arguments (requis par JPA)
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private Float age;
}