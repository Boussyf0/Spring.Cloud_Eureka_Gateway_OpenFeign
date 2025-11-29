package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Serveur Eureka - Service de découverte pour l'architecture microservices
 *
 * @EnableEurekaServer active le serveur Eureka qui agit comme un registre
 * où tous les microservices s'enregistrent automatiquement
 *
 * Dashboard accessible sur : http://localhost:8761/
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("  EUREKA SERVER DEMARRE AVEC SUCCES");
        System.out.println("  Dashboard : http://localhost:8761/");
        System.out.println("===========================================\n");
    }
}