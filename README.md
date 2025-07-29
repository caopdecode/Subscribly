# 📦 Subscribly

Subscribly is a backend Java application designed to simulate a subscription-based service. Built with **Spring Boot**, this project applies all **SOLID principles** in a real-world scenario including user management, plans, subscriptions, payments, and notifications.

It is intended as a **portfolio project** to demonstrate clean architecture, industry-standard documentation, and scalable design.

---

## 🚀 Features

- ✅ User registration and authentication
- ✅ Plan creation and management
- ✅ User subscription to plans
- ✅ Simulated payments with transaction IDs
- ✅ Email-style notifications (console-based)
- ✅ OpenAPI (Swagger) documentation
- ✅ PostgreSQL database (Docker-based)

---

## 🧠 SOLID Principles Applied

| Principle | Application |
|----------|-------------|
| **S** – Single Responsibility | Each class has one clear purpose (e.g., `SubscriptionService`, `NotificationService`) |
| **O** – Open/Closed | You can add new notification types without changing existing code |
| **L** – Liskov Substitution | `NotificationService` implementations can be swapped freely |
| **I** – Interface Segregation | Interfaces are clean and purpose-specific |
| **D** – Dependency Inversion | Services depend on interfaces, not implementations |

---

## ⚙️ Technologies Used

- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **Maven**
- **Swagger/OpenAPI (springdoc)**
- **Docker (for PostgreSQL)**

---

## 📁 Project Structure

```
src/
├── controller        → REST endpoints
├── service           → Business logic interfaces
├── serviceimpl       → Service implementations
├── model             → JPA Entities
├── dto               → Data Transfer Objects
├── repository        → Spring Data JPA Repositories
├── notification      → Notification interfaces and services
└── config            → Security and app configuration
```

---

## 🐘 PostgreSQL Setup with Docker

The project includes a `docker-compose.yml` file to spin up a PostgreSQL container easily.

### 🧪 How to run:

1. Make sure Docker is installed and running.

2. In the project root directory, run:

```bash
docker compose up -d
```

This will start a PostgreSQL container with the correct database, user, and password (defined in the `application.properties`).

---

## 🧪 How to Run the Project Locally

1. Clone the repository:

```bash
git clone https://github.com/yourusername/subscribly.git
cd subscribly
```

2. Start the PostgreSQL container:

```bash
docker compose up -d
```

3. Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

or from IntelliJ/VS Code using the "Run" button.

---

## 📖 API Documentation

Once the app is running, open your browser and go to:

```
http://localhost:8080/swagger-ui.html
```

There you will find the full API documentation with examples and the ability to test endpoints live.

---

## ✉️ Notifications

This project includes a simulated **email notification system** using the console.

For example, when a user subscribes to a plan, a message like this is printed:

```
📧 [Email sent to: user@example.com]
Content: Welcome to the Premium plan! Your subscription is valid until 2025-09-01.
```

---

## 👨‍💻 Author

**Camilo Osorio Páez | caopdecode**  
Multimedia Engineer | Backend Developer (Java, Spring Boot) | AI Learner  
📍 Colombia

---

## 📄 License

This project is open-source and available for any educational or portfolio use.
