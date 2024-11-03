## Description
This repository contains the Microservices for the TravelBox project. TravelBox is an application built using a 
microservices architecture, designed to manage various aspects of a travel booking and management system. 
This repository contains all the microservices that form part of the project.

## Team EvolveTravel
* Alessandro Alex Vega Paico - u201910225
* Tomas Ortiz Fajardo - U201910146
* Carlos Alberto Ochoa Colonio - U202315945
* Sebastian Enrique - U201611430
* Erick Hernan Ruiz Torrez - U202113432 

## Project Architecture
The project follows a microservices architecture, meaning that each component of the system is an independent service 
that communicates with others through an API Gateway. The services are decoupled, providing greater flexibility, 
scalability, and ease of maintenance.

## Microservices
1. Admin Microservices
This microservice handles the administrative functions of the system, providing an interface for general user management and other services within the platform.

2. API Gateway
The API Gateway acts as a single entry point for all requests to the microservices. It handles routing, authentication, and coordination of requests to services like order, cart, and product.

3. Cart Microservice
This service manages shopping cart operations, providing functionality for adding, removing, and updating products in the user's cart.

4. Config Data
Contains the centralized configurations for all microservices in YAML format. Each microservice has its own configuration file (e.g., api-gateway.yml, cart.yml, etc.), which is used by the Config Service.

5. Config Service
This service provides centralized configuration management to each microservice dynamically. It allows configuration updates without restarting the services.

6. Eureka Service
Implements service discovery using Netflix Eureka. All microservices register with this service to enable dynamic discovery and communication between them.

7. Order Microservice
This service handles the order management logic. It coordinates interactions with the user, cart, and product microservices to process user orders.

8. Product Microservice
Manages product-related operations. It supports CRUD (Create, Read, Update, Delete) operations for products and integrates with the cart and order microservices.

9. Trip Microservice
This service focuses on managing travel operations. It provides functionality for creating, updating, and searching for travel itineraries and reservations.

10. User Microservice
Manages all user-related logic, including registration, authentication, and personal data management.

## Technologies Used
* Java with Spring Boot for microservices development.
* Netflix Eureka for service discovery.
* Spring Cloud Config for centralized configuration management.
* Gradle as the build tool.
* API Gateway for request routing management.

## How to Run the Project
1. Clone this repository.
2. Set up your environment for Java and Gradle.
3. Run each microservice using Gradle commands within its respective folder.
4. Ensure the Eureka service is running to allow microservices registration.
5. Access the application through the API Gateway.
