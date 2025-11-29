package com.example.servicevoiture.entities;

import com.example.servicevoiture.models.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marque;

    @Column(nullable = false)
    private String matricule;

    @Column(nullable = false)
    private String model;

    @Column(name = "client_id")
    private Long clientId;

    @Transient
    private Client client;
}