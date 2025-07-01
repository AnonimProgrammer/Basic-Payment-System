# 🧾 Simple Payment System

A minimal microservices-based payment system built with **Spring Boot**, **RabbitMQ**, **Docker**, and **PostgreSQL**. The system demonstrates **event-driven communication** between decoupled services using RabbitMQ as a message broker.

---

## 🔧 Microservices Overview

This project consists of two primary services:

- **Order Service** – Receives orders and initiates the payment process.
- **Payment Service** – Handles payment creation and updates order status via messaging.

Each service has its own dedicated **PostgreSQL** database, and they communicate asynchronously via **RabbitMQ**.

---

## 📦 Project Structure
basic-payment-system/
├── order-service-app/
├── order-service-db/
├── payment-service-app/
├── payment-service-db/
├── payment-system-rabbitmq/

---

## 🧱 Docker Network Architecture

order-service-network/
├── order-service-app
├── order-service-db

payment-service-network/
├── payment-service-app
├── payment-service-db

total-payment-system-network/
├── order-service-app
├── payment-service-app
├── payment-system-rabbitmq

---

## 🔁 Flow of Operations

### 📝 Order Creation
1. Client sends a **POST** request to Order Service with `totalAmount`.
2. Order is created and saved in DB with `Status = PENDING`, `PaymentStatus = NOT_PAID`.
3. A `PaymentEventDTO` containing the `orderId` and `paymentStatus` is published to RabbitMQ's **Payment Queue**.

### 💳 Payment Processing
1. Payment Service listens to the **Payment Queue**, receives the event.
2. Creates a new Payment, updates status to `PAID` or `FAILED`, stores it in DB.
3. Publishes updated event to RabbitMQ's **Order Queue** with new status.

### 🔄 Order Status Update
1. Order Service listens to the **Order Queue**.
2. Receives updated `PaymentStatus` and updates the corresponding Order.

### 🔍 Retrieval
- Client can **GET** order details to view: `orderId`, `totalAmount`, `Status`, and `PaymentStatus`.

---

## 📁 Service Directory Highlights

### `order-service-app/`
- **controller/** – Handles REST endpoints.
- **DTO/** – Data Transfer Object for message passing.
- **message_broker/** – Publishes and consumes RabbitMQ messages.
- **model/** – Contains `Order`, `Status`, and `PaymentStatus` classes.
- **service/** – Business logic for order and messaging workflows.
- **exceptions/** – Custom exceptions for robust error handling.

### `payment-service-app/`
- Mirrors `order-service-app` structure.
- Logic focused on **Payment creation**, **DB interaction**, and **message propagation**.

---

## Dockerized Environment

Each service is **dockerized** and connected through custom Docker networks. 

---

## 🐳 Payment System Docker Configuration

All services can be build and run using Docker or `docker-compose`.

### 🗄️ Databases

**Order Service DB**
```bash
docker run --name order-service-db \
  --network order-service-network \
  -e POSTGRES_USER=user \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_DB=order_service_db \
  -p 5435:5432 \
  -d postgres
```  
**Payment Service DB**
```bash
docker run --name payment-service-db \
  --network payment-service-network \
  -e POSTGRES_USER=user \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_DB=payment_service_db \
  -p 5436:5432 \
  -d postgres
```
**Order Service App**
```bash
docker run --name order-service-app \
  --network order-service-network \
  -p 8130:8230 \
  -d order-service-app
```
**Payment Service App**
```bash
docker run --name payment-service-app \
  --network payment-service-network \
  -p 8140:8240 \
  -d payment-service-app
``` 
**RabbitMQ**
```bash
docker run -d --name rabbitmq \
--network total-payment-system-network \
-p 5672:5672 -p 15672:15672 \
rabbitmq:3-management
```