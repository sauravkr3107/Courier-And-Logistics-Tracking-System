# 🏦 Bank Management System

A **Bank Management System** developed using Java and Spring Boot that provides RESTful APIs to manage banks, customer accounts, and banking transactions. The application uses Spring Data JPA and Hibernate for database operations and PostgreSQL for persistent data storage.

## 🚀 Technologies Used

- **Java** – Core programming language
- **Spring Boot** – Application development and configuration
- **Spring Data JPA** – Database interaction and repository management
- **Hibernate** – ORM (Object-Relational Mapping)
- **PostgreSQL** – Relational database management system
- **REST APIs** – Communication between clients and the backend
- **Maven** – Dependency management and build automation
- **Postman** – API testing

## ✨ Features

### 🏦 Bank Management
- Create and manage bank records.
- Retrieve bank details by ID.
- Retrieve bank details using address-related information.
- Update existing bank information.
- Delete bank records subject to business rules.
- Validate bank details before saving.

### 💳 Account Management
- Create and manage bank accounts.
- Associate accounts with existing banks.
- Ensure account numbers are unique.
- Support savings and current account types.
- Apply minimum balance requirements according to account type.

### 💰 Transaction Management
- Deposit money into an account.
- Withdraw money subject to sufficient balance.
- Transfer money between accounts.
- Validate that transaction amounts are greater than zero.
- Prevent withdrawals and transfers that exceed the available balance.

### ✅ Data Validation and Business Rules
- Validate IFSC codes and contact number uniqueness.
- Ensure contact numbers contain exactly 10 digits.
- Validate that PIN codes contain exactly 6 digits.
- Require an address when creating a bank.
- Prevent account creation when the associated bank does not exist.
- Apply business rules before processing transactions.

## 🏗️ Project Architecture

The application follows a layered architecture to separate responsibilities and improve maintainability.

```text
Bank Management System
│
├── Controller Layer
│   └── Handles HTTP requests and REST endpoints
│
├── Service Layer
│   └── Implements business logic and validations
│
├── Repository Layer
│   └── Performs database operations using Spring Data JPA
│
├── Entity Layer
│   └── Defines database entities and relationships
│
└── Database
    └── PostgreSQL
```

## 🔄 How It Works

1. A client sends an HTTP request to a REST API endpoint.
2. The controller receives the request and passes it to the service layer.
3. The service layer executes business logic and validates the request.
4. The repository layer interacts with PostgreSQL through Spring Data JPA and Hibernate.
5. The application returns an appropriate HTTP response to the client.

## 🛠️ Prerequisites

Make sure the following tools are installed:

- Java Development Kit (JDK)
- Maven
- PostgreSQL
- An IDE such as Eclipse or IntelliJ IDEA
- Postman for API testing

## ⚙️ Installation and Setup

### 1. Clone the Repository

```bash
git clone <your-github-repository-url>
```

### 2. Navigate to the Project Directory

```bash
cd Bank-Management-System
```

### 3. Create a PostgreSQL Database

Open PostgreSQL and create a database:

```sql
CREATE DATABASE bank_management;
```

### 4. Configure Database Properties

Update your `src/main/resources/application.properties` file:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bank_management
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Configure `DB_USERNAME` and `DB_PASSWORD` as environment variables before running the application. Keep database credentials out of your source code.

**Note:** `ddl-auto=update` is suitable for local development. For production environments, consider using a database migration tool.

### 5. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Alternatively, run the main Spring Boot application class from your IDE.

Once the application starts, access the configured API endpoints using Postman.

## 🔗 REST API Endpoints

The following is an example endpoint structure. Replace or adjust the paths and HTTP methods to match your actual controller mappings.

| HTTP Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/banks` | Create a bank |
| GET | `/api/banks/{bankId}` | Retrieve a bank by ID |
| GET | `/api/banks` | Retrieve all banks |
| PUT | `/api/banks/{bankId}` | Update bank details |
| DELETE | `/api/banks/{bankId}` | Delete a bank |
| POST | `/api/accounts` | Create an account |
| GET | `/api/accounts/{accountId}` | Retrieve an account |
| POST | `/api/accounts/{accountId}/deposit` | Deposit money |
| POST | `/api/accounts/{accountId}/withdraw` | Withdraw money |
| POST | `/api/accounts/transfer` | Transfer money |

## 🧪 Testing

The REST APIs can be tested using Postman.

Test scenarios may include:

- Creating a bank with valid and invalid data.
- Retrieving existing and non-existing banks.
- Creating an account with a valid bank association.
- Testing duplicate account numbers.
- Depositing and withdrawing valid amounts.
- Attempting transactions with zero or negative amounts.
- Attempting withdrawals with insufficient funds.
- Transferring money between accounts.
- Validating contact numbers and PIN codes.

## 🎯 Learning Outcomes

Through this project, I gained practical experience with:

- Developing RESTful web services using Spring Boot.
- Implementing layered architecture in a Java backend application.
- Using Spring Data JPA and Hibernate for ORM and database operations.
- Designing entity relationships and repository interfaces.
- Connecting a Spring Boot application to PostgreSQL.
- Implementing business logic and input validation.
- Testing REST APIs using Postman.
- Handling database operations through a service and repository layer.

## 🔮 Future Enhancements

- Implement authentication and role-based authorization using Spring Security.
- Add transaction history and account statements.
- Introduce centralized exception handling.
- Add unit and integration tests.
- Implement pagination and sorting.
- Add API documentation using Swagger/OpenAPI.
- Containerize the application using Docker.

## 👨‍💻 Author

**Saurav Kumar**

Java Backend Developer | Spring Boot | REST APIs | PostgreSQL

GitHub: https://github.com/sauravkr3107

LinkedIn: https://www.linkedin.com/in/saurav-kumar-796163237

---

⭐ If you find this project useful, consider giving the repository a star.
