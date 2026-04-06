# Case Study - StockWise API (Inventory Management)

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk" alt="Java">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring%20Security-JWT-blue?style=for-the-badge&logo=springsecurity" alt="Security">
  <img src="https://img.shields.io/badge/Database-PostgreSQL-blue?style=for-the-badge&logo=postgresql" alt="PostgreSQL">
</p>

---

## 📘 Information

### Project Definition (StockWise API)
Bu proje; kurumsal ölçekte stok takibi, tedarikçi yönetimi ve satın alma süreçlerini dijitalleştirmek için tasarlanmış, Spring Boot tabanlı bir **Inventory Management System** çözümüdür. Sistem, JWT tabanlı stateless güvenlik mimarisi üzerine inşa edilmiş olup, rol tabanlı yetkilendirme (RBAC) ile korunmaktadır.

### End-to-end flow (Create Purchase):
* **Client sends:** `POST /api/purchases` endpoint'ine `PurchaseRequest` (productId, supplierId, miktar, birim fiyat) ile başvurur.
* **Service Logic:** Tedarikçinin aktifliği kontrol edilir, ürünün stok miktarı güncellenir ve satın alma kaydı veritabanına işlenir.
* **Persistence:** İşlem başarılı olduğunda `Purchase` entity'si kalıcı hale getirilir ve stok seviyesi anlık olarak güncellenir.

## 🚀 Explore Rest APIs

### Endpoints Summary

| Method | URL | Description | Request Body | Auth / Permissions |
| :--- | :--- | :--- | :--- | :--- |
| **POST** | `/api/auth/register` | Yeni kullanıcı kaydı | `RegisterRequest` | PermitAll |
| **POST** | `/api/auth/authenticate` | JWT Token üretimi (Login) | `AuthRequest` | PermitAll |
| **GET** | `/api/products` | Sayfalamalı ürün listesi | — | USER/ADMIN |
| **POST** | `/api/products` | Yeni ürün ekleme (Admin) | `ProductRequest` | ADMIN |
| **POST** | `/api/purchases` | Satın alma ve stok artırımı | `PurchaseRequest` | ADMIN |
| **GET** | `/api/suppliers` | Tedarikçi listesi | — | USER/ADMIN |

## 🛠 Technologies

* **Backend:** Java 17, Spring Boot 3.x, Spring Data JPA
* **Security:** Spring Security, JWT, BCrypt
* **Mapping:** ModelMapper (Strict Matching)
* **Validation:** Jakarta Bean Validation
* **Documentation:** OpenAPI 3.0 / Swagger UI
* **Database:** PostgreSQL / MySQL

## 📋 Prerequisites

### Define Variables in `.env` or `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/stockwise_db
jwt.secret=${JWT_SECRET_KEY}
jwt.expiration=86400000
