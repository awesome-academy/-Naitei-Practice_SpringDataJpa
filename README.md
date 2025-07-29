# Naite-Practice_SpringDataJpa



This is a Maven-based Java project demonstrating the use of Spring Core and Spring Data JPA to manage two entities: `Student` and `Course`. The project includes basic CRUD (Create, Read, Update, Delete) operations using an in-memory H2 database.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Configuration](#configuration)
- [Testing](#testing)

## Features
- Two entity classes: `Student` and `Course` with JPA annotations.
- Spring Core configuration using `@Configuration` and `@ComponentScan`.
- Spring Data JPA repositories for CRUD operations.
- H2 in-memory database for data persistence.
- Console-based demonstration of CRUD operations.

## Prerequisites
- Java 17 or higher.
- Maven 3.6+.
- An IDE like Eclipse or IntelliJ IDEA (optional but recommended).
- Basic knowledge of Spring and JPA.

## Project Structure
```
MyMavenProject
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           ├── config
│   │   │           │   └── AppConfig.java
│   │   │           ├── entity
│   │   │           │   ├── Student.java
│   │   │           │   └── Course.java
│   │   │           ├── repository
│   │   │           │   ├── StudentRepository.java
│   │   │           │   └── CourseRepository.java
│   │   │           └── AppMain.java
│   │   └── resources
│   │       └── application.properties
├── pom.xml
└── README.md
```

## Setup Instructions
1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd MyMavenProject
   ```

2. **Install dependencies**:
   Run the following command to download and install Maven dependencies:
   ```bash
   mvn clean install
   ```

3. **Import into IDE**:
   - Open Eclipse/IntelliJ and import the project as a Maven project.
   - Ensure Java 17 is configured as the JDK.

4. **Verify setup**:
   - Check that no errors appear in the IDE (e.g., no red marks in the "Problems" tab).

## Usage
1. **Run the application**:
   - Execute the `AppMain.java` class (right-click > Run As > Java Application in Eclipse).
   - The program will perform CRUD operations on `Student` and `Course` entities and display results in the console.

2. **Expected Output**:
   - Log messages from Hibernate showing SQL queries.
   - Console output confirming creation, reading, updating, and deletion of records.

## Configuration
- **Database**: Uses H2 in-memory database with URL `jdbc:h2:mem:testdb`.
- **Properties**: Configured in `src/main/resources/application.properties`:
  ```
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  ```
- **Spring Configuration**: Defined in `AppConfig.java` with `@EnableJpaRepositories` and custom `DataSource` and `EntityManagerFactory` beans.

## Testing
- Build the project with `mvn clean install`.
- Run `AppMain.java` to test CRUD operations.
- Verify logs for successful table creation and data manipulation.


