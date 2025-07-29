# Quản lý Thư viện với Spring Data JPA

Đây là một ứng dụng web được xây dựng bằng Spring Framework (không sử dụng Spring Boot) để quản lý hệ thống thư viện. Ứng dụng cung cấp các API RESTful trả về dữ liệu JSON để quản lý sách, tác giả và thể loại sách, sử dụng Spring MVC, Spring Data JPA, Hibernate và MySQL.

## Video Demo

https://drive.google.com/file/d/19CGsZO2YqWYt7JeWP8BIJT6FIFXaqegG/view?usp=sharing

## Giới thiệu

Dự án này được phát triển nhằm mục đích học tập và thực hành sử dụng Spring Framework để xây dựng một ứng dụng web với các chức năng CRUD cơ bản. Ứng dụng được triển khai trên server Apache Tomcat và sử dụng MySQL làm cơ sở dữ liệu thông qua XAMPP. Các cấu hình được thực hiện hoàn toàn bằng Java-based configuration và annotations, không sử dụng file XML cho cấu hình Spring.

### Mục tiêu

- Cung cấp các API RESTful để quản lý sách (`Book`), tác giả (`Author`), và thể loại (`Category`).
- Tích hợp cơ sở dữ liệu MySQL với Hibernate thông qua Spring Data JPA.
- Đảm bảo tính nhất quán và bảo mật dữ liệu thông qua các mối quan hệ `@ManyToOne`.
- Triển khai ứng dụng trên Apache Tomcat với context path `/library-management`.

### Tính năng

- **Quản lý sách**: Tạo, đọc, cập nhật, xóa sách với thông tin về tiêu đề, tác giả, thể loại và giá.
- **Quản lý tác giả**: Tạo và liệt kê danh sách tác giả với thông tin tên và quốc tịch.
- **Quản lý thể loại**: Tạo và liệt kê danh sách thể loại sách.
- **API RESTful**: Tất cả các endpoint trả về dữ liệu JSON, dễ dàng tích hợp với các ứng dụng khác.
- **Cơ sở dữ liệu**: Tự động tạo và cập nhật bảng bằng Hibernate (`hibernate.hbm2ddl.auto=update`).

## Công nghệ sử dụng

- **Java**: Phiên bản 11
- **Spring Framework**: 5.3.23
  - Spring MVC: Xử lý các yêu cầu HTTP và trả về JSON.
  - Spring Data JPA: Quản lý truy vấn cơ sở dữ liệu.
  - Spring ORM: Tích hợp với Hibernate.
- **Hibernate**: 5.6.10.Final.
- **MySQL**: 8.0.28.
- **Apache Tomcat**: 9.0.104.
- **Jackson**: 2.13.4.
- **Maven**: Quản lý dependency và build dự án.
- **IntelliJ IDEA**: IDE để phát triển ứng dụng.
- **Operating System**: Windows 11.

## Các Model

Ứng dụng sử dụng ba model chính, được ánh xạ sang các bảng trong cơ sở dữ liệu MySQL:

1. **Book**:

   - **Thuộc tính**:
     - `id`: Long, khóa chính, tự động tăng.
     - `title`: String, tiêu đề sách.
     - `author`: Author, mối quan hệ `@ManyToOne` với bảng `author`.
     - `category`: Category, mối quan hệ `@ManyToOne` với bảng `category`.
     - `price`: Double, giá sách.
   - **Bảng database**: `book`.

2. **Author** (Tác giả):

   - **Thuộc tính**:
     - `id`: Long, khóa chính, tự động tăng.
     - `name`: String, tên tác giả.
     - `nationality`: String, quốc tịch của tác giả.
   - **Bảng database**: `author`.

3. **Category**:
   - **Thuộc tính**:
     - `id`: Long, khóa chính, tự động tăng.
     - `name`: String, tên thể loại.
   - **Bảng database**: `category`.

### Mối quan hệ

- Một sách (`Book`) thuộc về một tác giả (`Author`) và một thể loại (`Category`) thông qua các mối quan hệ `@ManyToOne`.
- Các khóa ngoại `author_id` và `category_id` trong bảng `book` đảm bảo tính toàn vẹn dữ liệu.

## Cấu trúc thư mục

Cấu trúc thư mục của repository:

```
-Naitei-Practice_SpringDataJpa/
├── apache-tomcat-9.0.104/ # Thư mục chứa Apache Tomcat server
│ ├── bin/ # File thực thi của Tomcat
│ ├── webapps/ # Chứa file WAR (library-management.war)
├── library-management/ # Thư mục chứa mã nguồn ứng dụng
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ └── com/example/
│ │ │ │ ├── config/ # Cấu hình Spring (AppConfig.java)
│ │ │ │ ├── controller/ # Các controller RESTful (BookController.java, AuthorController.java, CategoryController.java)
│ │ │ │ ├── model/ # Các model (Book.java, Author.java, Category.java)
│ │ │ │ ├── repository/ # Các repository JPA (BookRepository.java, AuthorRepository.java, CategoryRepository.java)
│ │ │ │ └── service/ # Các service (BookService.java, AuthorService.java, CategoryService.java)
│ │ │ ├── resources/
│ │ │ └── webapp/
│ │ │ └── WEB-INF/
│ │ │ └── web.xml # Cấu hình DispatcherServlet
│ ├── pom.xml # File Maven quản lý dependency và build
│ └── target/ # Thư mục chứa file WAR sau khi build
└── README.md
```

## Hướng dẫn cài đặt

### Yêu cầu

- Java 11
- Maven 3.6.x trở lên
- MySQL (sử dụng XAMPP)
- Apache Tomcat 9.0.104
- IntelliJ IDEA (khuyến nghị)
- Windows OS

### Các bước cài đặt

1. **Clone repository**:

   ```bash
   git clone -b duylda_practice https://github.com/duyle1062/-Naitei-Practice_SpringDataJpa.git
   cd -Naitei-Practice_SpringDataJpa
   ```

2. **Cấu hình MySQL**:

   - Khởi động XAMPP và bật MySQL.
   - Truy cập phpMyAdmin (`http://localhost/phpmyadmin`) và tạo database tên `db_quanlysach`.

3. **Triển khai lên Tomcat**:

   ```bash
   cd apache-tomcat-9.0.104/bin
   .\startup.bat
   ```

4. **Kiểm tra API**:

   - Truy cập `http://localhost:8080/library-management/api/books` để kiểm tra danh sách sách.
   - Sử dụng Postman để gọi các endpoint.

## Các Endpoint

### Books

- **GET** `/api/books`: Lấy danh sách tất cả sách.
- **GET** `/api/books/{id}`: Lấy thông tin sách theo ID.
- **POST** `/api/books`: Tạo sách mới (yêu cầu `author_id` và `category_id`).
- **PUT** `/api/books/{id}`: Cập nhật thông tin sách.
- **DELETE** `/api/books/{id}`: Xóa sách.

### Authors

- **GET** `/api/authors`: Lấy danh sách tất cả tác giả.
- **POST** `/api/authors`: Tạo tác giả mới.

### Categories

- **GET** `/api/categories`: Lấy danh sách tất cả thể loại.
- **POST** `/api/categories`: Tạo thể loại mới.
