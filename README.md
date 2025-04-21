# Student Management System

A Spring Boot application for managing student records using full CRUD functionality.  
The system includes both a REST API and a responsive web-based UI built with Thymeleaf.

---

## Features

- Add, view, update, and delete student records
- Server-rendered web interface using Thymeleaf and Bootstrap
- RESTful API for integration with external systems
- Swagger UI for API documentation and testing
- H2 in-memory database for rapid prototyping
- Unit and integration testing with JUnit and Mockito

---

## Technologies Used

- Java 17
- Spring Boot 3.1.8
- Spring Data JPA
- Spring Web + Thymeleaf
- H2 Database
- Spring Validation
- Springdoc OpenAPI (Swagger)
- JUnit 5, Mockito
- Gradle

---

## How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/your-username/student-management.git
cd student_service
```

### 2. Build the project

```bash
./gradlew build
```

### 3. Run the application (Development)

```bash
./gradlew bootRun
```

---

## Demo

### Run from Source (Dev Mode)

```bash
./gradlew bootRun
```

- Web UI: http://localhost:8080/ui/students
- Swagger UI: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console
    - JDBC URL: `jdbc:h2:mem:studentdb`
    - Username: `shaghayegh`
    - Password: *(leave blank)*

### Run the Executable JAR

An executable JAR is already included in the project under the `jar/` directory.

To run the application:

```bash
java -jar jar/student-service-0.0.1-SNAPSHOT.jar
```

This will start the application on port `8080` and make all features accessible through your browser.

---

### Recorded Demo Video

You can watch a walkthrough of the system here:  
**[Watch Demo](https://your-link-here.com)**

> Replace the link above with your YouTube or Google Drive video link.

---

## REST API Endpoints

| Method | Endpoint          | Description                  |
|--------|-------------------|------------------------------|
| GET    | `/students`       | Retrieve all students        |
| GET    | `/students/{id}`  | Retrieve a student by ID     |
| POST   | `/students`       | Add a new student            |
| PUT    | `/students/{id}`  | Update an existing student   |
| DELETE | `/students/{id}`  | Delete a student by ID       |

---

## Sample JSON Payloads

### Create Student

```json
{
  "name": "Alice",
  "email": "alice@example.com",
  "age": 21
}
```

### Update Student

```json
{
  "name": "Updated Alice",
  "email": "updated@example.com",
  "age": 22
}
```

---

## Testing

You can run all test cases using:

```bash
./gradlew test
```

This covers:
- Unit tests for service layer
- Integration tests for repository
- Controller tests using MockMvc

Test coverage ensures correctness of logic, error handling, and data flow.

---

## Project Structure

```
src/
 └── main/
     ├── java/
     │    └── com.bounteous.student_service/
     │         ├── controller/          → Web + REST controllers
     │         ├── service/             → Business logic
     │         ├── repository/          → Spring Data JPA interfaces
     │         ├── model/               → Student entity
     │         └── exception/           → Custom error handling
     └── resources/
          ├── templates/               → Thymeleaf HTML views
          └── application.properties   → DB, port, etc.
```

---

## Notes

- All forms in the web UI are Bootstrap-styled and linked through a shared layout with navbar and footer.
- Form submissions (update, delete) handle empty or partial inputs gracefully.
- Responses for UI operations are visually shown using Bootstrap cards or alerts.

---

## Author

Shaghayegh Ghasemi  
Developed during the Bounteous Graduate Program 2025
