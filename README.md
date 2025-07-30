# -Naitei-Practice_SpringDataJpa
Practice Spring data Jpa &amp; Spring core
# 📚 Spring Core + Spring Data JPA Console Application

## Giới thiệu

Đây là một ứng dụng Java console đơn giản sử dụng **Spring Core kết hợp với Spring Data JPA** để quản lý thông tin sách, tác giả và nhà xuất bản. Người dùng sẽ tương tác qua menu điều khiển trong terminal.

Ứng dụng có cấu trúc dữ liệu như sau:

- **Book** – chứa thông tin sách.
- **Author** – chứa thông tin tác giả.
- **Publisher** – thông tin nhà xuất bản.
- **BookAuthor** – bảng liên kết trung gian giữa Book và Author phục vụ cho mô quan hệ nhiều‐nhiều (N‑N).

---

## Mô hình dữ liệu

### 📘 Book
- `id`: Long (primary key)
- `title`: String
- `publisher`: Nhà xuất bản (quan hệ **N‑1** tới **Publisher**)
- `authors`: Danh sách tác giả (quan hệ **N‑N** qua bảng **BookAuthor**)

### ✍️ Author
- `id`: Long (primary key)
- `firstname`: String
- `lastname`: String
- `books`: Danh sách sách (quan hệ **N‑N** qua bảng **BookAuthor**)

### 🏢 Publisher
- `id`: Long (primary key)
- `name`: String

### 🔁 BookAuthor
- Bảng join table giữa **Book** và **Author**, đại diện cho mỗi liên kết tác giả – sách.

---

## Service Layer

### `AuthorService`
Cung cấp các phương thức:
- `findAllAuthors()`: Truy xuất tất cả tác giả.
- `findAuthorsByBookTitle(String title)`: Tìm tác giả theo tên sách.
- `findAuthorsByName(String name)`: Tìm tác giả theo firstname hoặc lastname.
- `addAuthor(Author author)`: Thêm tác giả mới.
- `deleteAuthorById(Long id)`: Xóa tác giả theo ID.

---

## Controller (Console Menu)

Điều khiển luồng chương trình qua menu console, cho phép người dùng:
- Hiển thị menu chức năng.
- Nhập lựa chọn tác vụ (ví dụ: "1. Danh sách tác giả", "2. Tìm theo tên sách", ...).
- Nhập dữ liệu theo yêu cầu.
- Nhận kết quả trực tiếp trên terminal.

---

## Cấu hình Database (`application.properties`)

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
