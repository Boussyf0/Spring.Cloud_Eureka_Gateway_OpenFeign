package com.example.servicevoiture.clients;

import com.example.servicevoiture.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-CLIENT")
public interface ClientRestClient {

    @GetMapping("/client/{id}")
    Client findClientById(@PathVariable Long id);
}
