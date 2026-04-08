# StockWise – Inventory Management System

> Secure Inventory API with JWT Authentication and Role-Based Authorization  
> Dockerized | Swagger Documented | Clean Architecture

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [API Overview](#api-overview)
- [Installation](#installation)
- [Configuration](#configuration)
- [How to Run](#how-to-run)
- [API Documentation](#api-documentation)
- [Validation and Exception Handling](#validation-and-exception-handling)
- [Business Logic Highlights](#business-logic-highlights)
- [Future Improvements](#future-improvements)
- [License](#license)

---

## Overview

StockWise API is a backend-focused inventory management system designed to manage products, categories, suppliers, and stock operations in a secure and scalable way.

The system provides RESTful endpoints for handling inventory workflows such as product lifecycle management, supplier relationships, and purchase-based stock updates.

---

## Features

### JWT-Based Authentication and Authorization
Secure login system using JWT tokens.

### Role-Based Access Control
Supports `ADMIN` and `USER` roles.

### Product, Category, Supplier Management
Full CRUD operations with business rules.

### Purchase Management
Stock increase logic with supplier linkage.

### Stock Tracking
Minimum stock level support.

### Pagination and Search
Efficient data retrieval.

### Swagger Documentation
Interactive API testing.

### Docker Support
Containerized execution.

---

## Architecture

- Controller Layer
- Service Layer
- Repository Layer
- DTO Layer
- Security Layer

---

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA

### Database
- PostgreSQL

### Tools
- Docker
- Swagger
- Maven
- Postman

---

## Project Structure

```bash
src/main/java/org/ahmetsezer/stockwise
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
```

---

## API Overview

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register user |
| POST | `/api/auth/authenticate` | Login |

---

### Products

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/api/products` | USER, ADMIN |
| POST | `/api/products` | ADMIN |
| PUT | `/api/products/{id}` | ADMIN |
| DELETE | `/api/products/{id}` | ADMIN |

---

### Categories

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/api/categories` | USER, ADMIN |
| POST | `/api/categories` | ADMIN |
| PUT | `/api/categories/{id}` | ADMIN |
| DELETE | `/api/categories/{id}` | ADMIN |

---

### Suppliers

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/api/suppliers` | USER, ADMIN |
| POST | `/api/suppliers` | ADMIN |
| PUT | `/api/suppliers/{id}` | ADMIN |
| DELETE | `/api/suppliers/{id}` | ADMIN |

---

### Purchases

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/api/purchases` | USER, ADMIN |
| POST | `/api/purchases` | ADMIN |

---

## Installation

### Prerequisites

- Java 17
- Maven
- PostgreSQL
- Docker (optional)

---

### Clone Repository

```bash
git clone https://github.com/your-username/StockWise.git
cd StockWise
```

---

### Build Project

```bash
./mvnw clean install
```

---

## Configuration

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/stockwise
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_secret_key
jwt.expiration=86400000
```

---

## How to Run

### Run Locally

```bash
./mvnw spring-boot:run
```

---

### Run with Docker

```bash
docker-compose up --build
```

---

## API Documentation

```
http://localhost:8080/swagger-ui/index.html
```

---


## Future Improvements

- Refresh token support
- Unit & integration tests
- CI/CD pipeline
- Redis caching

---

## License

This project is developed for educational and portfolio purposes.
