package com.example.serviceclient;

import com.example.serviceclient.entities.Client;
import com.example.serviceclient.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * Service Client - Microservice de gestion des clients
 *
 * @EnableDiscoveryClient active l'enregistrement automatique dans Eureka
 * Ce service expose des API REST pour gérer les clients (CRUD)
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceClientApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("  SERVICE CLIENT DEMARRE AVEC SUCCES");
        System.out.println("  API : http://localhost:8088/clients");
        System.out.println("  Enregistre dans Eureka comme : SERVICE-CLIENT");
        System.out.println("===========================================\n");
    }

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(new Client(null, "Ahmed Bennani", 30.0f));
            clientRepository.save(new Client(null, "Fatima Zahra", 25.0f));
            clientRepository.save(new Client(null, "Youssef Alami", 35.0f));
            System.out.println("Données de test insérées dans SERVICE-CLIENT");
        };
    }
}