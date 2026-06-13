# MeCash Backend Challenge

Backend Money Transfer Service built with Spring Boot.

## Overview

This project is a backend application developed as part of the MeCash Backend Engineer assessment. The system provides core banking functionalities including user registration, authentication, account balance inquiry, fund transfers, transaction history retrieval, and user profile management.

---

## Features

### Authentication & User Management
- User Registration
- User Login
- Basic Authentication with Spring Security
- User Profile Update

### Account Management
- Automatic Account Creation
- Account Balance Inquiry

### Transactions
- Fund Transfer Between Accounts
- Transaction History Retrieval
- Transaction Status Tracking

### Database & Infrastructure
- PostgreSQL Integration
- Flyway Database Migrations
- Swagger/OpenAPI Documentation
- Global Exception Handling

---

## Technology Stack

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- Flyway
- Maven
- Swagger OpenAPI

---

## Authentication

The application uses **HTTP Basic Authentication**.

Protected endpoints require the user's registered email and password.

Most API tools such as Postman and Swagger UI automatically generate the required Authorization header when Basic Authentication credentials are supplied.

Example:

```http
Authorization: Basic <Base64(email:password)>
```

---

# API Endpoints

## Public Endpoints

These endpoints do not require authentication.

### Register User

```http
POST /api/auth/signup
```

Request Body

```json
{
  "email": "user@example.com",
  "password": "Password123$"
}
```

---

### Login User

```http
POST /api/auth/login
```

Request Body

```json
{
  "email": "user@example.com",
  "password": "Password123$"
}
```

---

## Protected Endpoints

The following endpoints require Basic Authentication.

### Update User Profile

```http
PUT /api/auth/profile
```

---

### Get Account Balance

```http
GET /api/accounts/{accountNumber}/balance
```

Example:

```http
GET /api/accounts/7908088688/balance
```

---

### Transfer Funds

```http
POST /api/transactions/transfer
```

Request Body

```json
{
  "sourceAccountNumber": "7908088688",
  "destinationAccountNumber": "2026800256",
  "amount": 1000
}
```

---

### Get Transaction History

```http
GET /api/transactions/{accountNumber}/history
```

Example:

```http
GET /api/transactions/7908088688/history
```

---

## Quick Test Flow

### 1. Register a User

```http
POST /api/auth/signup
```

Example:

```json
{
  "email": "user@example.com",
  "password": "Password123$"
}
```

### 2. Login

```http
POST /api/auth/login
```

### 3. Authenticate

Use the registered email and password as **Basic Authentication** credentials.

### 4. Check Account Balance

```http
GET /api/accounts/{accountNumber}/balance
```

### 5. Transfer Funds

```http
POST /api/transactions/transfer
```

### 6. View Transaction History

```http
GET /api/transactions/{accountNumber}/history
```

---

## Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE mecash;
```

Update the datasource configuration in:

```text
src/main/resources/application.yml
```

Example:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mecash
    username: postgres
    password: your_password
```

---

## Running the Application

### Clone Repository

```bash
git clone <repository-url>
```

### Navigate to Project

```bash
cd mecash
```

### Build Project

```bash
./mvnw clean package
```

### Run Application

```bash
./mvnw spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

---

## Testing with Postman

For protected endpoints:

1. Open the request.
2. Navigate to the **Authorization** tab.
3. Select **Basic Auth**.
4. Enter:

```text
Username: your-email@example.com
Password: your-password
```

5. Send the request.

Postman automatically generates the Authorization header.

---

## Swagger Documentation

After starting the application:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI Specification:

```text
http://localhost:8080/v3/api-docs
```

---

## Database Migrations

Database schema changes are managed using Flyway.

Migration scripts are located in:

```text
src/main/resources/db/migration
```

---

## Project Structure

```text
src/main/java/com/mecash
│
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── exception
├── util
└── config
```

---

## Build Verification

The project was successfully built using:

```bash
./mvnw clean package
```

Build Result:

```text
BUILD SUCCESS
```

Environment:

- Java 21
- Maven Wrapper
- PostgreSQL

---

## Author

**Ridoh Lawal**

Email: ridohlawal96@gmail.com

GitHub: https://github.com/Ridoh74. 

Run:

mvn spring-boot:run

Swagger:

http://localhost:8080/swagger-ui/index.html
