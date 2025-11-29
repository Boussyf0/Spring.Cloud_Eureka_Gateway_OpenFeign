package com.example.servicevoiture;

import com.example.servicevoiture.entities.Voiture;
import com.example.servicevoiture.repositories.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class ServiceVoitureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVoitureApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("  SERVICE VOITURE DEMARRE AVEC SUCCES");
        System.out.println("  API : http://localhost:8089/");
        System.out.println("===========================================\n");
    }

    @Bean
    CommandLineRunner initDatabase(VoitureRepository voitureRepository) {
        return args -> {
            voitureRepository.save(new Voiture(null, "Toyota", "A-123-BC", "Corolla", 1L, null));
            voitureRepository.save(new Voiture(null, "Renault", "B-456-DE", "Megane", 2L, null));
            voitureRepository.save(new Voiture(null, "Peugeot", "C-789-FG", "308", 1L, null));
            voitureRepository.save(new Voiture(null, "Mercedes", "D-012-HI", "Classe A", 3L, null));
            System.out.println("Données de test insérées dans SERVICE-VOITURE");
        };
    }
}
