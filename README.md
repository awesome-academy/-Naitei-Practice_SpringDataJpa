# Task Manager (No Spring Boot)

Một ứng dụng quản lý công việc đơn giản sử dụng **Spring Core + Spring Data JPA** (không dùng Spring Boot).

- Ứng dụng gồm 2 thực thể chính `Task` và `Category` có quan hệ `@ManyToOne`.

## Công nghệ sử dụng
- Java 17
- Maven
- Spring Core (IoC, DI, @ComponentScan)
- Spring Data JPA
- Hibernate
- MySQL

## Cấu trúc thư mục
```bash
task-vylinh/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/taskmanager/
│       │       ├── config/
│       │       │   └── AppConfig.java
│       │       ├── controller/
│       │       ├── model/
│       │       │   ├── Task.java
│       │       │   └── Category.java
│       │       ├── repository/
│       │       │   ├── TaskRepository.java
│       │       │   └── CategoryRepository.java
│       │       ├── service/
│       │       │   ├── TaskService.java
│       │       │   └── impl/
│       │       │       └── TaskServiceImpl.java
│       │       └── MainApp.java
│       └── resources/
│           ├── application.properties       # bị ignore
│           └── application.properties.example
├── pom.xml
├── .gitignore
└── README.md
---

## Chức năng chính
- Tạo, đọc, cập nhật, và xóa công việc (task)
- Kết nối MySQL qua Hibernate (không dùng Spring Boot)
- Cấu hình bean và transaction thủ công qua `AppConfig.java`

## Chạy

### 1. Tạo file cấu hình
Tạo file `src/main/resources/application.properties` với nội dung:

```properties
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/task_db
db.username=your_username
db.password=your_password
```

Note: không commit file `application.properties` thật lên GitHub mà tạo thêm file `application.properties.example` với nội dung giống nhưng thay bằng giá trị giả. Ví dụ:
```properties
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/task_db
db.username=your_username
db.password=your_password
```
Và thêm `application.properties` vào `.gitignore`.

### 2. Tạo database
Truy cập MySQL và tạo database:
```sql
CREATE DATABASE task_db;
```

### 3. Chạy ứng dụng
Trong thư mục gốc (nơi có `pom.xml`), chạy:
```bash
mvn clean compile
mvn exec:java
```

## Kết quả demo
Chương trình sẽ:
- Tạo 1 task mẫu
- In ra danh sách task hiện có
- Tìm task theo ID
- Cập nhật trạng thái task
- Xoá task
- In ra danh sách task sau khi xóa

## Ghi chú triển khai
- Không sử dụng Spring Boot → không có `@SpringBootApplication`
- Sử dụng `AnnotationConfigApplicationContext` để khởi tạo container
- Toàn bộ bean được cấu hình thông qua `@Component`, `@Service`, `@Repository` và scan qua `@ComponentScan`
- Kết nối DB + quản lý transaction cấu hình trong `AppConfig.java`
- Repository kế thừa từ `CrudRepository` để có sẵn các phương thức CRUD
