# 🚚 Courier and Logistics Tracking System

A **Courier and Logistics Tracking System** developed using Java and Spring Boot to manage shipments, customers, delivery agents, warehouses, packages, payments, and shipment tracking history. The application provides RESTful APIs for managing courier operations, tracking shipment progress, and enforcing logistics business rules.

## 🚀 Technologies Used

- **Java** – Core programming language
- **Spring Boot** – Backend application development
- **Spring Data JPA** – Database access and repository management
- **Hibernate** – Object-Relational Mapping (ORM)
- **PostgreSQL** – Relational database management system
- **REST APIs** – Communication between clients and the backend
- **Maven** – Dependency management and build automation
- **Postman** – API testing

## ✨ Key Features

### 👥 Customer Management

- Create and manage customer records.
- Maintain customer information associated with shipments.
- Retrieve customer details through REST APIs.

### 📦 Package Management

- Manage package details, including weight and dimensions.
- Support different package types.
- Identify fragile packages.
- Apply additional charges for fragile items.

### 🚚 Shipment Management

- Create and manage shipments.
- Associate shipments with customers, warehouses, delivery agents, packages, and payment records.
- Generate and maintain unique tracking numbers.
- Retrieve shipment details using shipment IDs or tracking numbers.
- Manage shipment status throughout the delivery process.

### 📍 Shipment Tracking

- Maintain tracking history for shipments.
- Record shipment status updates and remarks.
- Track shipment progress through different delivery stages.
- Retrieve shipment tracking information using a tracking number.

### 🏢 Warehouse Management

- Manage warehouse information.
- Associate shipments with their relevant warehouses.
- Maintain warehouse capacity information.

### 🧑‍✈️ Delivery Agent Management

- Manage delivery agent details.
- Assign delivery agents to shipments.
- Prevent shipment assignment when an agent is unavailable.

### 💳 Payment Management

- Maintain payment information associated with shipments.
- Associate payment records with the relevant shipment.
- Support payment status tracking according to the implemented business rules.

### ⚙️ Business Logic and Validation

- Ensure tracking numbers are unique.
- Calculate shipment charges based on package weight.
- Apply additional charges for fragile packages.
- Prevent assignment of unavailable delivery agents.
- Maintain relationships between shipments and their associated entities.
- Validate incoming data before saving records.

## 🏗️ Project Architecture

The application follows a layered architecture to separate API handling, business logic, database access, and entity management.

```text
Courier and Logistics Tracking System
│
├── Controller Layer
│   └── Handles REST API requests and responses
│
├── Service Layer
│   └── Implements business logic and validations
│
├── Repository Layer
│   └── Performs database operations using Spring Data JPA
│
├── Entity Layer
│   ├── Customer
│   ├── Shipment
│   ├── PackageEntity
│   ├── DeliveryAgent
│   ├── WareHouse
│   ├── Payment
│   └── TrackingHistory
│
└── Database
    └── PostgreSQL
```

## 🔄 How the Application Works

1. A customer and package are registered in the system.
2. A shipment is created and associated with the required customer, warehouse, package, payment, and delivery information.
3. The application validates shipment details and generates or stores a unique tracking number.
4. Shipping charges are calculated based on package weight, with additional charges for fragile packages.
5. An available delivery agent is assigned to the shipment.
6. Shipment status updates are recorded in the tracking history.
7. Clients can retrieve shipment information and track delivery progress through REST APIs.

## 🛠️ Prerequisites

Install the following tools before running the project:

- Java Development Kit (JDK)
- Maven
- PostgreSQL
- Eclipse, IntelliJ IDEA, or another Java IDE
- Postman for API testing

## ⚙️ Installation and Setup

### 1. Clone the Repository

```bash
git clone <your-github-repository-url>
```

### 2. Navigate to the Project Directory

```bash
cd Courier-and-Logistics-Tracking-System
```

### 3. Create the PostgreSQL Database

```sql
CREATE DATABASE courier_logistics;
```

### 4. Configure Database Properties

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/courier_logistics
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Set the `DB_USERNAME` and `DB_PASSWORD` environment variables before running the application. Do not commit database credentials to GitHub.

**Note:** The `ddl-auto=update` setting is intended for development. Database migration tools are preferable for production environments.

### 5. Run the Application

```bash
mvn spring-boot:run
```

Alternatively, run the main Spring Boot application class from your IDE.

Once the application starts, use Postman to test your configured REST API endpoints.

## 🔗 REST API Endpoints

The following endpoints are illustrative. Update the paths and HTTP methods to match your actual controller mappings.

| HTTP Method | Example Endpoint                           | Purpose                   |
| ----------- | ------------------------------------------ | ------------------------- |
| POST        | `/api/customers`                           | Create a customer         |
| GET         | `/api/customers/{id}`                      | Retrieve customer details |
| POST        | `/api/packages`                            | Register a package        |
| GET         | `/api/packages/{id}`                       | Retrieve package details  |
| POST        | `/api/warehouses`                          | Register a warehouse      |
| POST        | `/api/delivery-agents`                     | Register a delivery agent |
| POST        | `/api/shipments`                           | Create a shipment         |
| GET         | `/api/shipments/{id}`                      | Retrieve shipment details |
| GET         | `/api/shipments/tracking/{trackingNumber}` | Track a shipment          |
| PUT         | `/api/shipments/{id}/status`               | Update shipment status    |
| GET         | `/api/shipments/{id}/tracking-history`     | Retrieve tracking history |

## 🧪 Testing

Use Postman to test the application's REST APIs and business rules.

Important test scenarios include:

- Creating a shipment with valid associated entities.
- Rejecting duplicate tracking numbers.
- Calculating shipping charges based on package weight.
- Applying additional charges for fragile packages.
- Preventing assignment to unavailable delivery agents.
- Updating shipment status and recording tracking history.
- Retrieving shipment details using a tracking number.
- Validating missing or invalid request data.

## 🎯 Learning Outcomes

Through this project, I gained practical experience in:

- Building RESTful web services with Spring Boot.
- Implementing layered architecture in Java applications.
- Mapping entity relationships using JPA and Hibernate.
- Integrating PostgreSQL with Spring Boot.
- Implementing shipment pricing and logistics business rules.
- Managing shipment status and tracking history.
- Using Spring Data JPA repositories for database operations.
- Testing and validating REST APIs using Postman.

## 🔮 Future Enhancements

- Implement authentication and role-based access control using Spring Security.
- Add real-time shipment tracking and delivery notifications.
- Integrate Google Maps or another mapping service for delivery routes.
- Add pagination and sorting to listing APIs.
- Implement centralized exception handling.
- Add unit and integration tests.
- Document APIs using Swagger/OpenAPI.
- Containerize the application using Docker.

## 👨‍💻 Author

**Saurav Kumar**

Java Backend Developer | Spring Boot | REST APIs | PostgreSQL

GitHub: https://github.com/sauravkr3107

LinkedIn: https://www.linkedin.com/in/saurav-kumar-796163237

---

⭐ If you find this project useful, consider giving the repository a star.
