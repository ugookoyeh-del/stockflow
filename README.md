# StockFlow
A Java 17 Spring Boot inventory REST API using Spring Data JPA and MySQL.
## Features
- Create, list, update and delete inventory products
- Unique SKU validation
- Track unit price and on-hand quantity
- Low-stock reporting with a configurable threshold
- Bean Validation for request payloads
## Run
Configure a local MySQL database and optional environment variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, then run:
```bash
mvn spring-boot:run
```
API base URL: `http://localhost:8081/api/products`.
Example JSON: `{"name":"Wireless Mouse","sku":"ACC-001","unitPrice":8500.00,"quantity":24}`.
## Endpoints
| Method | Path | Purpose |
|---|---|---|
| GET | /api/products | List |
| GET | /api/products/{id} | Detail |
| POST | /api/products | Create |
| PUT | /api/products/{id} | Update |
| DELETE | /api/products/{id} | Delete |
| GET | /api/products/low-stock?threshold=5 | Low-stock report |
## Roadmap
Add authorization, stock movement audit records, pagination, Flyway migrations and integration tests. This starter is not production-ready inventory software.