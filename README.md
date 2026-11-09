# ⚙️ TaskFlow — Task Management REST API (Backend)

A production-ready Spring Boot backend for a Task Management application, featuring secure JWT authentication and user data isolation.

## ✨ Features
- 🔐 Secure user registration and login with **JWT (JSON Web Token)**.
- 📝 Users can **create, view, update (status), and delete** their own tasks.
- 🔒 **Data isolation**: Users can only access and modify their own tasks.
- 🛡️ **CORS** configured for seamless React frontend integration.
- ⚡ Global exception handling for clean API responses.

## 🛠️ Tech Stack
- **Java 17** · **Spring Boot 3.x**
- **Spring Security + JWT** (Authentication & Authorization)
- **Spring Data JPA** + **PostgreSQL**
- **Maven** build system

## 🚀 How to Run Locally
  bash
# 1. Clone the repository
git clone https://github.com/Dilki31/taskflow-api.git

# 2. Create a PostgreSQL database named 'taskflow_db'

# 3. Update src/main/resources/application.properties with your DB credentials

# 4. Run the Spring Boot application

📂 Project Structure
config/ - Security & Data Initialization

controller/ - REST API Endpoints (Auth, Tasks)

dto/ - Data Transfer Objects

entity/ - Database Models (User, Task)

repository/ - Spring Data JPA Interfaces

security/ - JWT Filter, Service & UserDetails

service/ - Business Logic

👨‍💻 Author
Dilki Nanayakkara - @Dilki31
./mvnw spring-boot:run

# 5. The API will be available at http://localhost:8083/api/
