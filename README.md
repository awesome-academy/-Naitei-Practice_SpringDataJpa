# Project: Quản Lý Sách và Tác Giả (Spring Core & Spring Data JPA)

Dự án này là một minh họa chi tiết về cách xây dựng một ứng dụng CRUD (Create, Read, Update, Delete) hoàn chỉnh bằng cách sử dụng **Spring Core** và **Spring Data JPA** mà không phụ thuộc vào Spring Boot.

Dự án tập trung vào việc cấu hình thủ công và hiểu rõ sự tương tác giữa các thành phần cốt lõi của Spring Framework, giúp người học nắm vững bản chất của Spring trước khi chuyển sang các công cụ tự động hóa.

## Mục Tiêu & Tính Năng

### 1. Mục Tiêu Học Tập
- **Xây dựng từ gốc**: Hiểu rõ cách thiết lập một dự án Spring từ đầu, bao gồm việc quản lý dependency với Maven.
- **Cấu hình tường minh**: Nắm vững cách cấu hình `DataSource`, `EntityManagerFactory`, và `TransactionManager` bằng Java-based configuration (`@Configuration`) thay vì XML.
- **Tầng Repository hiệu quả**: Triển khai và sử dụng Spring Data JPA để giảm thiểu code boilerplate ở tầng truy cập dữ liệu (DAO/Repository).
- **Quản lý quan hệ phức tạp**: Triển khai và xử lý mối quan hệ Nhiều-Nhiều (`@ManyToMany`) giữa các thực thể một cách chính xác và an toàn.
- **Kiến trúc Service hoàn chỉnh**: Xây dựng đầy đủ các tầng service cho từng nghiệp vụ chính.

### 2. Các Chức Năng Chính

Ứng dụng cung cấp các chức năng CRUD hoàn chỉnh cho cả hai thực thể `Book` và `Author` thông qua các lớp Service chuyên biệt.

#### AuthorService (6 phương thức)
*   `createAuthor(author)`: Tạo một tác giả mới.
*   `findAuthorById(id)`: Tìm một tác giả theo ID.
*   `findAllAuthors()`: Lấy danh sách tất cả các tác giả.
*   `findAuthorsByName(name)`: Tìm kiếm tác giả theo tên (không phân biệt hoa thường, tìm kiếm gần đúng).
*   `updateAuthor(id, authorDetails)`: Cập nhật thông tin chi tiết cho một tác giả.
*   `deleteAuthor(id)`: Xóa một tác giả (với logic kiểm tra ràng buộc: không cho phép xóa nếu tác giả còn sách).

#### BookService (10 phương thức)
*   `createBook(book)`: Tạo một cuốn sách mới. 
*   `findBookById(id)`: Tìm một cuốn sách chỉ với thông tin cơ bản.
*   `findBookByIdWithAuthors(id)`: Lấy thông tin sách và danh sách tác giả liên quan trong một lần truy vấn (sử dụng `JOIN FETCH` để tránh `LazyInitializationException`).
*   `findAllBooks()`: Lấy danh sách tất cả các cuốn sách.
*   `findBooksByAuthorId(authorId)`: Tìm tất cả các sách được viết bởi một tác giả cụ thể.
*   `updateBookTitle(id, newTitle)`: Cập nhật chỉ tiêu đề của một cuốn sách.
*   `addBookAuthor(bookId, authorId)`: Liên kết một tác giả đã có vào một cuốn sách.
*   `removeBookAuthor(bookId, authorId)`: Gỡ một tác giả khỏi một cuốn sách.
*   `replaceBookAuthors(bookId, authorIds)`: Thay thế toàn bộ danh sách tác giả của một cuốn sách bằng một danh sách mới.
*   `deleteBookById(id)`: Xóa một cuốn sách và các liên kết của nó trong bảng trung gian.

## Các Công Nghệ Sử Dụng

- **Ngôn ngữ**: Java 11
- **Build Tool**: Apache Maven
- **Frameworks & Thư viện**:
  -   Spring Core `5.3.x`
  -   Spring ORM `5.3.x`
  -   Spring Data JPA `2.7.x`
- **JPA Provider**: Hibernate `5.6.x`
- **Database**:
  -   **H2 In-memory Database**: Dùng cho demo nhanh, không cần cài đặt.
  -   **MySQL 8**: Hỗ trợ cho môi trường phát triển thực tế.
- **Logging**: Logback

## Cấu Trúc Project

Cấu trúc dự án được tổ chức một cách logic, phân tách rõ ràng các tầng trách nhiệm.

```
spring-jpa-many-to-many/
├── pom.xml                   # File cấu hình Maven, quản lý các thư viện
├── README.md                 # Tài liệu hướng dẫn
└── src/
    └── main/
        ├── java/
        │   └── vn/com/example/
        │       ├── AppMain.java          # Lớp chính để chạy và kiểm thử ứng dụng
        │       ├── config/
        │       │   └── JpaConfig.java      # Cấu hình Spring, JPA và Transaction
        │       ├── entity/
        │       │   ├── Author.java
        │       │   └── Book.java
        │       ├── repository/
        │       │   ├── AuthorRepository.java
        │       │   └── BookRepository.java
        │       └── service/
        │           ├── AuthorService.java  # <<< MỚI
        │           ├── BookService.java
        │           └── impl/
        │               ├── AuthorServiceImpl.java # <<< MỚI
        │               └── BookServiceImpl.java
        └── resources/
            ├── application.properties  # Cấu hình kết nối database, Hibernate
            └── logback.xml             # Cấu hình logging
```

## Hướng Dẫn Cài Đặt và Chạy

### Yêu Cầu
-   Java JDK 11 trở lên.
-   Apache Maven 3.6 trở lên.
-   Một IDE như IntelliJ IDEA hoặc Eclipse (khuyến khích).
-   MySQL Server (nếu bạn muốn chạy với MySQL).

### Các Bước Cài Đặt

**1. Clone Repository**
```bash
git clone <URL_REPOSITORY_CUA_BAN>
cd <TEN_THU_MUC_PROJECT>
```

**2. Cài đặt Dependencies**

Sử dụng Maven để tải các thư viện cần thiết. Mở terminal tại thư mục gốc của project và chạy:
```bash
mvn clean install
```
Lệnh này sẽ tải dependencies được định nghĩa trong `pom.xml` về máy.

**3. Cấu hình Database**

Mở file `src/main/resources/application.properties`. Project đã được cấu hình mặc định để chạy với H2.

- **Để chạy với H2 (Mặc định, không cần làm gì thêm):**
  Cấu hình hiện tại đã sẵn sàng. Dữ liệu sẽ được tạo và xóa sau mỗi lần chạy.

- **Để chuyển sang MySQL:**
  a. Mở `pom.xml` và đảm bảo dependency của `mysql-connector-java` đã được thêm vào.

  b. Trong file `application.properties`, comment lại các dòng của H2 và bỏ comment các dòng của MySQL.

  c. **Cập nhật thông tin kết nối** của bạn vào các thuộc tính `spring.datasource.url`, `spring.datasource.username`, và `spring.datasource.password`.

  ```properties
  # -- Cấu hình H2 (Mặc định) --
  #spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1
  #spring.datasource.driverClassName=org.h2.Driver
  #spring.datasource.username=sa
  #spring.datasource.password=
  #hibernate.dialect=org.hibernate.dialect.H2Dialect

  # -- Cấu hình MySQL (Bỏ comment để sử dụng) --
  spring.datasource.url=jdbc:mysql://localhost:3306/db_book_management?createDatabaseIfNotExist=true
  spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
  
  # Cấu hình chung của Hibernate
  hibernate.hbm2ddl.auto=update # 'create-drop' cho H2, 'update' cho MySQL
  hibernate.show_sql=true
  hibernate.format_sql=true
  hibernate.use_sql_comments=true
  ```

**4. Chạy Ứng Dụng**

- Mở project trong IDE của bạn (IntelliJ, Eclipse,...).
- Tìm đến file `src/main/java/vn/com/example/AppMain.java`.
- Chạy phương thức `main()` (Right-click -> Run 'AppMain.main()').

### Kết Quả Mong Đợi

Sau khi chạy, bạn sẽ thấy output trên console:
- Các log khởi tạo của Spring và Hibernate.
- Các câu lệnh SQL (`CREATE TABLE`, `INSERT`, `SELECT`, `UPDATE`, `DELETE`) được Hibernate sinh ra.
- Các dòng thông báo từ `System.out.println()` trong `AppMain.java`, xác nhận các bước CRUD cho cả `Book` và `Author` đã được thực thi thành công.