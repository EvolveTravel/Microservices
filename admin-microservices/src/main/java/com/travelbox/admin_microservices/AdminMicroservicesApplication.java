package com.travelbox.admin_microservices;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class AdminMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminMicroservicesApplication.class, args);
	}

}
