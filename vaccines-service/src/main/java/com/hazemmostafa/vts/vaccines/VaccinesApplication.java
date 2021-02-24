package com.hazemmostafa.vts.vaccines;

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
	@Info(title = "Vaccines API", version = "1.0", description = "Documentation Vaccines API v1.0")
)
public class VaccinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccinesApplication.class, args);
	}

}
