# Spring Cloud Microservices Architecture
### Eureka Discovery • API Gateway • OpenFeign Communication

A complete microservices architecture demonstration using Spring Cloud ecosystem, featuring service discovery with Eureka Server, centralized routing through Spring Cloud Gateway, and inter-service communication via OpenFeign.

---

## Table of Contents
- [Architecture Overview](#architecture-overview)
- [Microservices Components](#microservices-components)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Testing Guide](#testing-guide)
- [Monitoring & Management](#monitoring--management)
- [Screenshots](#screenshots)
- [Technology Stack](#technology-stack)

---

## Architecture Overview

This project demonstrates a production-ready microservices architecture with the following components:

```
┌─────────────────┐
│   API Gateway   │ :8888
│  (Entry Point)  │
└────────┬────────┘
         │
    ┌────┴─────┐
    │          │
┌───▼──────┐ ┌─▼────────────┐
│ Service  │ │   Service    │
│ Client   │ │   Voiture    │
│  :8088   │ │    :8089     │
└────┬─────┘ └──────┬───────┘
     │              │
     └──────┬───────┘
            │
     ┌──────▼──────┐
     │   Eureka    │
     │   Server    │
     │    :8761    │
     └─────────────┘
```

**Key Features:**
- **Service Discovery**: Automatic service registration and discovery via Eureka
- **Load Balancing**: Client-side load balancing through Spring Cloud
- **API Gateway**: Single entry point with intelligent routing
- **Inter-Service Communication**: Type-safe REST clients using OpenFeign

---

## Microservices Components

### 1. Eureka Server (Port: 8761)
Service registry for automatic service discovery and health monitoring.

**Access Points:**
- Dashboard: `http://localhost:8761`

**Responsibilities:**
- Service registration and heartbeat management
- Service instance tracking
- Load balancing support

---

### 2. Client Service (Port: 8088)
Microservice for managing client/customer data.

**Technology:**
- Spring Boot REST API
- H2 In-Memory Database
- Spring Data JPA

**Endpoints:**

| Method | Endpoint          | Description                    |
|--------|-------------------|--------------------------------|
| GET    | `/clients`        | Retrieve all clients           |
| GET    | `/client/{id}`    | Retrieve client by ID          |
| POST   | `/client`         | Create a new client            |
| PUT    | `/client/{id}`    | Update an existing client      |
| DELETE | `/client/{id}`    | Delete a client                |

---

### 3. Voiture Service (Port: 8089)
Microservice for managing vehicle data with OpenFeign integration.

**Technology:**
- Spring Boot REST API
- H2 In-Memory Database
- OpenFeign for inter-service calls

**Endpoints:**

| Method | Endpoint                    | Description                           |
|--------|-----------------------------|---------------------------------------|
| GET    | `/voitures`                 | Retrieve all vehicles (with owners)   |
| GET    | `/voiture/{id}`             | Retrieve vehicle by ID                |
| GET    | `/voitures/client/{clientId}` | Retrieve vehicles by client         |
| POST   | `/voiture`                  | Create a new vehicle                  |
| PUT    | `/voiture/{id}`             | Update a vehicle                      |
| DELETE | `/voiture/{id}`             | Delete a vehicle                      |

**OpenFeign Integration:**
This service demonstrates inter-service communication by fetching client details when retrieving vehicle information.

---

### 4. API Gateway (Port: 8888)
Centralized entry point providing unified access to all microservices.

**Features:**
- Automatic route discovery via Eureka
- Manual route configuration
- Client-side load balancing
- Request filtering and transformation

**Route Configuration:**

| Gateway Route     | Target Service      | Backend Endpoint  |
|-------------------|---------------------|-------------------|
| `/clients/**`     | SERVICE-CLIENT      | Port 8088         |
| `/client/**`      | SERVICE-CLIENT      | Port 8088         |
| `/voitures/**`    | SERVICE-VOITURE     | Port 8089         |
| `/voiture/**`     | SERVICE-VOITURE     | Port 8089         |

---

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Port availability: 8761, 8088, 8089, 8888

### Build the Project

```bash
mvn clean install
```

### Start Services (in order)

**Step 1: Start Eureka Server**
```bash
cd eureka-server
mvn spring-boot:run
```
Wait for Eureka to be fully operational. Verify at: http://localhost:8761

**Step 2: Start Client Service**
```bash
cd service-client
mvn spring-boot:run
```

**Step 3: Start Voiture Service**
```bash
cd service-voiture
mvn spring-boot:run
```

**Step 4: Start API Gateway**
```bash
cd gateway
mvn spring-boot:run
```

**Verification:** Check Eureka Dashboard to ensure all services are registered.

---

## API Documentation

### Testing via Gateway (Recommended)

All API calls should go through the Gateway for proper routing and load balancing.

**Client Service Examples:**

```bash
# List all clients
curl http://localhost:8888/clients

# Get specific client
curl http://localhost:8888/client/1

# Create a new client
curl -X POST http://localhost:8888/client \
  -H "Content-Type: application/json" \
  -d '{"nom": "Nouveau Client", "age": 28}'

# Update a client
curl -X PUT http://localhost:8888/client/1 \
  -H "Content-Type: application/json" \
  -d '{"nom": "Client Modifié", "age": 30}'

# Delete a client
curl -X DELETE http://localhost:8888/client/1
```

**Voiture Service Examples:**

```bash
# List all vehicles (includes client info via OpenFeign)
curl http://localhost:8888/voitures

# Get specific vehicle
curl http://localhost:8888/voiture/1

# Get vehicles by client
curl http://localhost:8888/voitures/client/1

# Create a new vehicle
curl -X POST http://localhost:8888/voiture \
  -H "Content-Type: application/json" \
  -d '{"marque": "BMW", "modele": "X5", "matricule": "ABC123", "clientId": 1}'
```

---

## Testing Guide

### Sample Data

The application comes pre-loaded with test data for demonstration.

**Clients (3 pre-inserted):**
1. Ahmed Bennani (30 ans)
2. Fatima Zahra (25 ans)
3. Youssef Alami (35 ans)

**Voitures (4 pre-inserted):**
1. Toyota Corolla → Ahmed Bennani
2. Renault Megane → Fatima Zahra
3. Peugeot 308 → Ahmed Bennani
4. Mercedes Classe A → Youssef Alami

---

## Monitoring & Management

### Eureka Dashboard
Monitor service health and registration status:
- URL: http://localhost:8761

### H2 Database Consoles

**Client Service Database:**
- URL: http://localhost:8088/h2-console
- JDBC URL: `jdbc:h2:mem:client-db`
- Username: `sa`
- Password: *(leave empty)*

**Voiture Service Database:**
- URL: http://localhost:8089/h2-console
- JDBC URL: `jdbc:h2:mem:voiture-db`
- Username: `sa`
- Password: *(leave empty)*

### Spring Boot Actuator

Health checks and metrics available at:
- Gateway: http://localhost:8888/actuator
- Client Service: http://localhost:8088/actuator
- Voiture Service: http://localhost:8089/actuator

---

## Screenshots

### Eureka Server Dashboard
<img src="https://raw.githubusercontent.com/Boussyf0/Spring.Cloud_Eureka_Gateway_OpenFeign/main/Screen/eureke_test.png" alt="Eureka Dashboard" width="800"/>

### Client List API Response
<img src="https://raw.githubusercontent.com/Boussyf0/Spring.Cloud_Eureka_Gateway_OpenFeign/main/Screen/Liste_client.png" alt="Liste des clients" width="800"/>

### Single Client API Response
<img src="https://raw.githubusercontent.com/Boussyf0/Spring.Cloud_Eureka_Gateway_OpenFeign/main/Screen/client_id_1.png" alt="Client avec ID=1" width="800"/>

### Vehicle List with Client Details (OpenFeign)
<img src="https://raw.githubusercontent.com/Boussyf0/Spring.Cloud_Eureka_Gateway_OpenFeign/main/Screen/Liste_voitures.png" alt="Liste des voitures" width="800"/>

---

## Technology Stack

| Technology                    | Version  | Purpose                          |
|-------------------------------|----------|----------------------------------|
| Spring Boot                   | 3.2.0    | Application framework            |
| Spring Cloud                  | 2023.0.0 | Microservices infrastructure     |
| Spring Cloud Netflix Eureka   | -        | Service discovery                |
| Spring Cloud Gateway          | -        | API Gateway                      |
| Spring Cloud OpenFeign        | -        | Declarative REST client          |
| Spring Data JPA               | -        | Data persistence                 |
| H2 Database                   | -        | In-memory database               |
| Lombok                        | -        | Code generation                  |
| Maven                         | -        | Build tool                       |

---

## OpenFeign Demonstration

OpenFeign enables declarative REST client creation, simplifying inter-service communication.

**Example Implementation:**

```java
@FeignClient(name = "SERVICE-CLIENT")
public interface ClientRestClient {
    @GetMapping("/client/{id}")
    Client findClientById(@PathVariable Long id);
}
```

**How It Works:**

1. When you call `/voitures`, the Voiture Service retrieves vehicle records
2. For each vehicle, it uses the OpenFeign client to fetch the owner's details from Client Service
3. Eureka provides service discovery - no hardcoded URLs needed
4. Spring Cloud handles load balancing automatically
5. The response includes enriched data with complete client information

This demonstrates the power of microservices: each service maintains its own data while seamlessly collaborating through well-defined APIs.

---

## Project Structure

```
Spring_Cloud_Eureka_Gateway_OpenFeign/
├── eureka-server/          # Service registry
├── gateway/                # API Gateway
├── service-client/         # Client management service
├── service-voiture/        # Vehicle management service
├── Screen/                 # Screenshots
└── pom.xml                # Parent POM
```

---

## License

This project is part of Spring Cloud learning exercises.