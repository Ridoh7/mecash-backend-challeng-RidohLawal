# MeCash Backend Challenge

## Features

- User Registration
- User Login
- Account Balance Inquiry
- Fund Transfer
- Currency Conversion
- Transaction History

## Technologies

- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Flyway
- Swagger OpenAPI

## API Endpoints

POST /api/auth/signup
POST /api/auth/login
GET /api/accounts/{accountNumber}/balance
POST /api/transactions/transfer
GET /api/transactions/{accountNumber}/history

## Setup

1. Clone repository
2. Create PostgreSQL database named mecash
3. Configure application.yml
4. Run:

mvn spring-boot:run

Swagger:

http://localhost:8080/swagger-ui/index.html
