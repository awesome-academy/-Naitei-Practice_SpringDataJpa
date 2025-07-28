# -Naitei-Practice_SpringDataJpa - Library Management System

Link demo: [Link](https://drive.google.com/file/d/1KQSHuFR7jq_ItRzbAJsCwfARaoF_66_z/view?usp=sharing)

## ✅ 1. Mục tiêu

- Xây dựng một ứng dụng quản lý thư viện cho phép quản lý **Sách**, **Tác giả**, **Thể loại**, và **Nhà xuất bản**.
- Hỗ trợ các chức năng CRUD và tìm kiếm dữ liệu.
- Sử dụng **Spring Boot + JPA + Hibernate** để triển khai backend.
- Lưu trữ dữ liệu với **MySQL**.

## ✅ 2. Kỹ thuật yêu cầu

- Không sử dụng Spring Boot
- Sử dụng Spring Core + Spring Data JPA
- Java Config (không dùng XML)
- Tối thiểu 1 model (ở đây có 4)
- Tối thiểu 4 method CRUD
- Sử dụng annotation để config Entity
- Chạy với `AnnotationConfigApplicationContext`

## ✅ 3. Các Entity chính

- **Book**

  - `id`, `title`, `description`, `publishedDay`, `totalQuantity`
  - Quan hệ:
    - `@ManyToOne` → Publisher
    - `@ManyToMany` → Author, Genre

- **Publisher**

  - `id`, `name`, `address`, `email`
  - Quan hệ:
    - `@OneToMany` → Book

- **Author**

  - `id`, `name`, `bio`
  - Quan hệ:
    - `@ManyToMany` → Book

- **Genre**
  - `id`, `name`, `description`
  - Quan hệ:
    - `@ManyToMany` → Book

## ✅ 4. Cấu trúc thư mục

```
library_management/
│
├── src/main/java/
│   └── com.example.library.library_management/
│       ├── App.java → Lớp khởi động chính của ứng dụng.
│       │
│       ├── config/
│       │   └── AppConfig.java → Cấu hình Spring, khai báo Bean, JPA.
│       │
│       ├── entity/
│       │   ├── Author.java → Entity tác giả.
│       │   ├── Book.java → Entity sách.
│       │   ├── Genre.java → Entity thể loại sách.
│       │   └── Publisher.java → Entity nhà xuất bản.
│       │
│       ├── repository/
│       │   └── BookRepository.java → Repository thao tác với bảng Book.
│       │
│       └── service/
│           ├── BookService.java → Interface khai báo dịch vụ xử lý nghiệp vụ.
│           └── BookServiceImpl.java → Triển khai của BookService.
│
├── src/main/resources/ → Chứa file cấu hình (ví dụ: application.properties) và tài nguyên tĩnh nếu có.
│
├── src/test/java/ → Chứa các lớp test (JUnit).
│
├── pom.xml → File cấu hình Maven, khai báo dependencies.
│
└── target/ → Thư mục build do Maven tạo ra.
```
