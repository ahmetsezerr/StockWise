# 📦 StockWise – Inventory Management System

> 🔐 Secure Inventory API with JWT Authentication & Role-Based Authorization  
> 🐳 Dockerized | 📄 Swagger Documented | ⚙️ Clean Architecture  

---

## 📘 Information

### 📌 Project Definition (StockWise API)

StockWise API is a backend-focused inventory management system designed to manage products, categories, suppliers, and stock operations in a secure and scalable way.

The system provides RESTful endpoints for handling inventory workflows such as product lifecycle management, supplier relationships, and purchase-based stock updates. It simulates real-world business scenarios by enforcing rules like category validation, supplier activity control, and stock consistency.

Built with a layered architecture, the project uses DTO-based data transfer to ensure clean separation of concerns. Security is implemented using JWT-based authentication and role-based authorization, allowing controlled access to endpoints based on user roles (`ADMIN`, `USER`).

Additionally, the application includes centralized exception handling, request validation, and Swagger/OpenAPI documentation support, making it suitable for real-world backend development and portfolio demonstration.

---

## 🚀 Features

- 🔐 JWT-based Authentication & Authorization  
- 👥 Role-based access control (`ADMIN`, `USER`)  
- 📦 Product, Category, Supplier management  
- 🛒 Purchase system for stock increase operations  
- 📊 Minimum stock level tracking  
- 🔍 Pagination and search functionality  
- ⚠️ Centralized global exception handling  
- ✅ Request validation with Bean Validation  
- 📄 Swagger / OpenAPI documentation  
- 🐳 Dockerized application with Docker Compose  
- 🧱 Layered architecture (Controller, Service, Repository)  
- 🔄 DTO-based data transfer structure  







## 🧠 System Design

- Layered Architecture (Controller → Service → Repository)
- DTO-based communication
- Stateless authentication (JWT)
- Separation of concerns
- RESTful API design

---

## ⚙️ Core Features

### 🔐 Authentication & Authorization
- JWT-based authentication system
- Role-based access control (`ADMIN`, `USER`)
- Protected endpoints using `@PreAuthorize`
- Stateless session management

---

### 📦 Inventory Management

#### Product
- Create / Update / Delete products (ADMIN)
- List and search products (USER, ADMIN)
- Category-based organization

#### Category
- Manage product categories
- Full CRUD operations

#### Supplier
- Supplier lifecycle management
- Active/passive supplier control

---

### 🛒 Purchase System

- Purchase operations increase stock quantity
- Linked with Product and Supplier
- Simulates real-world stock flow

---

### 📊 Stock Control

- Minimum stock level support
- Business rule validations

---

## 🔍 API Overview

### 🔑 Authentication

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/authenticate` | Login and receive JWT |

---

### 📦 Products

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/api/products` | USER, ADMIN |
| POST | `/api/products` | ADMIN |
| PUT | `/api/products/{id}` | ADMIN |
| DELETE | `/api/products/{id}` | ADMIN |

---

### 📁 Categories & Suppliers

- Full CRUD operations  
- Write operations restricted to ADMIN  

---

### 🛒 Purchases

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | `/api/purchases` | Increase stock |
| GET | `/api/purchases` | List purchases |

---

## 🛠️ Technologies

### Backend
- Java 17  
- Spring Boot  
- Spring Security  
- Spring Data JPA (Hibernate)  

### Database
- PostgreSQL  

### Tools & Libraries
- JWT (JJWT)  
- Docker & Docker Compose  
- Swagger / OpenAPI  
- Maven  
- Postman  
- Git  

---

## 📂 Project Structure

```bash
src/main/java/org/ahmetsezer/stockwise
├── config          # Configuration classes
├── controller      # REST Controllers
├── dto             # Request / Response DTOs
├── entity          # JPA Entities
├── exception       # Custom exceptions & handlers
├── repository      # Data access layer
├── security        # JWT & Security configuration
├── service         # Business logic layer
