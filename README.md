<<<<<<< HEAD
# -Naitei-Practice_SpringDataJpa
Practice Spring data Jpa &amp; Spring core
=======
# 📚 Library Management – CRUD Application

### 🎯 **Mục tiêu dự án**

Xây dựng ứng dụng quản lý thư viện sử dụng **Spring Data JPA** (không dùng Spring Boot) để luyện tập cấu hình thủ công và thao tác với cơ sở dữ liệu.

---

## 🏷 **Thông tin chung**

* **Tên Project**: Library Management
* **Công nghệ**: Java, Spring Data JPA, MySQL, Maven
* **Chức năng chính**: CRUD cho các bảng trong DB và quản lý quan hệ giữa chúng.

---

## 🗂 **Cấu trúc chính**

### 🔹 **Entities**

* **Book**: chứa `title`, `author`, `publisher`, `published_day`, `book_detail`.
* **Author**: chứa `name`, `birthday`.
* **Publisher**: chứa `name`, `address`, `email`.
* **BookDetail**: chứa `coverUrl`, `pageCount`, `language`, `description`.

> `Book` liên kết với `Author` và `Publisher` theo quan hệ **Many-to-One**.

### 🔹 **Repository**

* Mỗi entity có một Repository (extends `JpaRepository`) để thao tác với DB.

### 🔹 **Service**

* Đóng vai trò trung gian giữa Repository và Controller (trong tương lai).
* Cung cấp các phương thức CRUD: `save`, `getAll`, `getById`, `delete`.

### 🔹 **Config**

* Lớp `LibConfig` cấu hình `DataSource`, `EntityManagerFactory`, và `TransactionManager` để kết nối với DB.

### 🔹 **App.class** là lớp chạy chính của ứng dụng.

* Sử dụng AnnotationConfigApplicationContext để khởi tạo Spring context từ LibConfig.

* Thực hiện các thao tác CRUD mẫu:

Lưu sách (saveBook) vào cơ sở dữ liệu.

Xóa sách (deleteBook) với việc in ra thông tin sách trước khi xóa.

Hiển thị danh sách sách (getAllBooks) sau khi thao tác.

---

### 🚧 **Hướng phát triển**

* Bổ sung **CRUD nâng cao** (tìm kiếm theo nhiều điều kiện, phân trang, sắp xếp).
* Bổ sung **Controller**.
>>>>>>> 65e9df5 (Initial project setup with full CRUD (entities, repositories, services, configs, and console controller))
