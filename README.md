# 📦 StockWise | Intelligent Inventory & Supply Chain Management

[![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Security-JWT-blue?style=for-the-badge&logo=springsecurity)](https://spring.io/projects/spring-security)
[![Database](https://img.shields.io/badge/Database-PostgreSQL-blue?style=for-the-badge&logo=postgresql)](https://www.postgresql.org/)

**StockWise**, modern işletmelerin envanter akışlarını, tedarikçi ilişkilerini ve satın alma operasyonlarını uçtan uca yönetebileceği, yüksek güvenlik standartlarına sahip bir **Enterprise REST API** çözümüdür.

## 🚀 Öne Çıkan Mühendislik Çözümleri

### 🔐 1. Güvenlik Mimarisi (Stateless Security)
* **JWT Service:** `io.jsonwebtoken` kütüphanesi ile özelleştirilmiş token üretimi, doğrulama ve claim yönetimi.
* **Custom Security Handlers:** `CustomAuthenticationEntryPoint` (401) ve `CustomAccessDeniedHandler` (403) ile yetkisiz erişimlerde standartlaştırılmış JSON yanıtları.
* **Role-Based Access Control (RBAC):** Metot bazlı `@PreAuthorize` kullanımı ile `ADMIN` ve `USER` rollerine göre dinamik yetkilendirme.

### 💼 2. Akıllı İş Mantığı (Business Logic)
* **Otomatik Stok Yönetimi:** `Purchase` işlemi onaylandığında, ilgili ürünün stok miktarı `Transactional` olarak güncellenir.
* **Dinamik Stok Analizi:** Ürün listeleme sırasında `lowStock` (kritik seviye) ve `outOfStock` bayrakları iş kurallarına göre anlık hesaplanır.
* **Soft Delete:** Veri bütünlüğünü korumak adına fiziksel silme yerine `active` flag'i üzerinden yönetim sağlanır.

### 🛡️ 3. Merkezi Hata ve İstisna Yönetimi
* **Global Exception Handler:** `RestControllerAdvice` kullanılarak tüm iş hataları (`SupplierNotActive`, `EmailAlreadyExists`, `ResourceNotFound`) merkezi bir noktadan yakalanır ve istemciye tutarlı bir `ErrorResponse` objesi döner.

## 🛠 Teknoloji Yığını

| Alan | Kullanılan Teknoloji |
| :--- | :--- |
| **Backend** | Java 17, Spring Boot 3.x, Spring Data JPA |
| **Güvenlik** | Spring Security, JWT (Stateless) |
| **Mapping** | ModelMapper (Strict Strategy) |
| **Dokümantasyon** | Swagger UI & OpenAPI 3.0 |
| **Veritabanı** | PostgreSQL / MySQL (Hibernate) |

## 🏗 Proje Katmanları

- **`config/`**: Security, Mapper ve Swagger konfigürasyonları.
- **`controller/`**: REST API uç noktaları ve istek yönetimi.
- **`service/`**: İş mantığı arayüzleri ve `impl` katmanı.
- **`repository/`**: Veritabanı sorguları (Derived Query Methods).
- **`entity/`**: JPA modelleri ve `BaseEntity` denetim sistemi.
- **`dto/`**: Request/Response ayrımıyla veri transfer nesneleri.

## ⚙️ Hızlı Başlangıç

### 🔧 Kurulum ve Çalıştırma
1. **Depoyu Klonlayın:**
   ```bash
   git clone [https://github.com/ahmetsezer/stockwise.git](https://github.com/ahmetsezer/stockwise.git)
   cd stockwise
