# Quản lý phòng học – Spring Core + Spring Data JPA

---

## Mục tiêu

Xây dựng project Java không dùng Spring Boot, sử dụng **Spring Core + Spring Data JPA** để thực hiện các chức năng CRUD cơ bản cho đối tượng `Classroom`. 

---

## Kỹ thuật yêu cầu

- Không sử dụng Spring Boot  
- Sử dụng Spring Core + Spring Data JPA  
- Cấu hình bằng XML (không dùng Java Config)  
- Tối thiểu 1 Entity (`Classroom`)  
- Tối thiểu 4 method CRUD (Create, Read, Update, Delete)  
- Dùng annotation để đánh dấu entity  
- Chạy bằng `ClassPathXmlApplicationContext`  
- Kết nối DB qua file `db.properties`
- Giao diện CLI (Command Line Interface)

---

## Entity chính

### 1. `Classroom`

| Thuộc tính     | Kiểu dữ liệu |
|----------------|--------------|
| `id`           | Long         |
| `name`         | String       |
| `location`     | String       |
| `capacity`     | Integer      |

**Ghi chú**: 
- `Classroom` là một entity sử dụng annotation `@Entity`, `@Id`, `@GeneratedValue`, `@Table`.
- Mapping đến bảng `classrooms` trong CSDL.
- Sử dụng Spring Data JPA để thao tác với DB thông qua interface `ClassroomRepository`.

---

## Cấu trúc dự án

```
minhpq/
├── pom.xml                              # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/com/example/minhpq/
│   │   │   ├── MainApp.java             # Entry point của ứng dụng
│   │   │   ├── cli/
│   │   │   │   └── ClassroomCLI.java    # Giao diện dòng lệnh
│   │   │   ├── config/
│   │   │   │   └── AppConfig.java       # Configuration class
│   │   │   ├── controller/
│   │   │   │   └── ClassroomController.java  # REST Controller
│   │   │   ├── model/
│   │   │   │   └── Classroom.java       # Entity class
│   │   │   ├── repository/
│   │   │   │   └── ClassroomRepository.java  # Data access layer
│   │   │   └── service/
│   │   │       └── ClassroomService.java     # Business logic layer
│   │   └── resources/
│   │       ├── applicationContext.xml   # Spring XML configuration
│   │       ├── application.properties   # Application properties
│   │       ├── db.properties           # Database configuration
│   │       └── db.example.properties   # Example DB config
│   └── test/
│       └── java/com/example/minhpq/
│           └── MinhpqApplicationTests.java
```

---

## Công nghệ sử dụng

- **Java 21**
- **Spring Core 6.1.5**
- **Spring Data JPA 3.2.3**
- **Hibernate 6.4.4.Final**
- **MariaDB** (JDBC Driver)
- **Maven** (Build tool)
