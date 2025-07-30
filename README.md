# Thực hành Spring Core & Spring Data JPA

Đây là một dự án thực hành nhằm mục đích làm chủ việc sử dụng **Spring Core** và **Spring Data JPA** mà không phụ thuộc vào cơ chế tự động cấu hình của Spring Boot. Ứng dụng là một hệ thống CRUD (Tạo, Đọc, Cập nhật, Xóa) đơn giản trên giao diện dòng lệnh (command-line) để quản lý sách, tác giả và thể loại trong một thư viện.

## 1. Yêu cầu

*   Sử dụng Spring Core + Spring Data JPA
*   Java Config
*   Tối thiểu 1 model
*   Config entity (Annotation)
*   Dùng file properties
*   Cấu trúc phần service sẽ chia theo loại file: cụ thể sẽ có package service và service.impl.

## 2. Các Entity

### 2.1. Book
*   `id` (Long): Khóa chính, tự tăng.
*   `title` (String): Tên sách.
*   `version` (int): Trường dùng cho Optimistic Locking, tự động tăng sau mỗi lần cập nhật.

### 2.2. Author
*   `id` (Long): Khóa chính, tự tăng.
*   `name` (String): Tên tác giả.

### 2.3. Category
*   `id` (Long): Khóa chính, tự tăng.
*   `name` (String): Tên thể loại.

## 3. Quan hệ giữa các Bảng

*   **Mối quan hệ giữa `Book` và `Author` là Nhiều-Nhiều (`@ManyToMany`)**. Mối quan hệ này được triển khai bằng một bảng trung gian (`book_author`) được JPA tự động quản lý thông qua annotation `@JoinTable`.
*   **Mối quan hệ giữa `Book` và `Category` là Nhiều-Một (`@ManyToOne`)**. Một cuốn sách chỉ thuộc về một thể loại, nhưng một thể loại có thể có nhiều cuốn sách.
    *   `Category` 1 -- * `Book`: Một thể loại có nhiều sách.
    *   `Book` * -- 1 `Category`: Nhiều cuốn sách thuộc về một thể loại.

## 4. Các chức năng chính

Ứng dụng cung cấp các chức năng CRUD cho các thực thể thông qua các lớp Service và Repository.

### `BookService`
*   `createBook(book)`: Tạo một cuốn sách mới.
*   `getBookById(id)`: Tìm một cuốn sách theo ID.
*   `getAllBooks(pageable)`: Lấy danh sách tất cả các cuốn sách, hỗ trợ phân trang và sắp xếp.
*   `updateBook(book)`: Cập nhật thông tin một cuốn sách.
*   `deleteBook(id)`: Xóa một cuốn sách.

### `BookRepository`
*   `findByCategoryName(categoryName)`: Tìm sách theo tên thể loại (sử dụng Derived Query).
*   `findByAuthorName(authorName)`: Tìm sách theo tên tác giả (sử dụng Custom `@Query` với JPQL).

### `AuthorRepository` & `CategoryRepository`
Cung cấp các phương thức CRUD cơ bản được kế thừa từ `CrudRepository`.

## 5. Hướng dẫn Cài đặt và Chạy

### 5.1. Yêu cầu
*   JDK 11+
*   Apache Maven 3.6+
*   MySQL Server

### 5.2. Cấu hình Database
1.  **Clone Repository** về máy.
2.  **Cấu hình kết nối:**
    *   Sao chép file `src/main/resources/database.properties.example` thành `src/main/resources/database.properties`.
    *   Mở file `database.properties` và cập nhật các thông tin `db.url`, `db.username`, và `db.password` cho phù hợp với môi trường MySQL của bạn.

    > **Lưu ý:** Dự án được cấu hình với `hibernate.hbm2ddl.auto=update` và `createDatabaseIfNotExist=true` trong URL kết nối. Do đó, database và các bảng sẽ được **tự động tạo** trong lần chạy đầu tiên. Dữ liệu mẫu cũng sẽ được tạo bởi mã nguồn trong lớp `Main.java`.

### 5.3. Build và Chạy
1.  Mở terminal tại thư mục gốc của dự án.
2.  Build dự án với Maven:
    ```shell
    mvn clean install
    ```
3.  Chạy ứng dụng:
    *   Chạy phương thức `main` trong file `src/main/java/com/example/library/Main.java` trực tiếp từ IDE của bạn (IntelliJ, VS Code, Eclipse).

Ứng dụng sẽ thực thi một kịch bản demo hoàn chỉnh bao gồm việc tạo dữ liệu, CRUD, và các truy vấn tùy chỉnh, sau đó in kết quả ra console.
