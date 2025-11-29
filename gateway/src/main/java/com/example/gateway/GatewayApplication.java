package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API Gateway - Point d'entrée unique pour l'architecture microservices
 *
 * La Gateway gère :
 * - Le routage des requêtes vers les microservices appropriés
 * - L'équilibrage de charge via Spring Cloud LoadBalancer
 * - La découverte des services via Eureka
 */
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("  API GATEWAY DEMARRE AVEC SUCCES");
        System.out.println("  Point d'entree : http://localhost:8888/");
        System.out.println("===========================================\n");
    }
}