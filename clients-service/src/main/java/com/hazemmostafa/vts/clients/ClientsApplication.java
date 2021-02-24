package com.hazemmostafa.vts.clients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(info =
    @Info(title = "Clients API", version = "1.0", description = "Documentation Clients API v1.0")
)
public class ClientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientsApplication.class, args);
    }
}
