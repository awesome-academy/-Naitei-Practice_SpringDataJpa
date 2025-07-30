# Naitei Practice Spring Data JPA

Đây là dự án thực hành sử dụng Spring Data JPA và Spring Core. Ứng dụng cung cấp các chức năng quản lý sách trong thư viện với đầy đủ các tính năng CRUD (Create, Read, Update, Delete) và các chức năng tìm kiếm nâng cao.

## Tính năng

- Thêm sách mới
- Xem thông tin chi tiết một cuốn sách
- Xem danh sách tất cả sách
- Cập nhật thông tin sách
- Xóa sách
- Tìm kiếm sách theo tác giả
- Tìm kiếm sách theo tiêu đề
- Tìm kiếm sách theo năm xuất bản
- Tìm kiếm sách theo khoảng giá
- API RESTful
- Sử dụng MySQL làm cơ sở dữ liệu
- Chạy trên Tomcat nhúng

## Công nghệ sử dụng

- Java 11+
- Spring Core 5.3.20
- Spring Data JPA 2.7.1
- Spring Web MVC
- Hibernate 5.6.9
- MySQL
- Tomcat nhúng
- Logback (logging)

## Hướng dẫn cài đặt

### Yêu cầu

- Java 11 trở lên
- Maven
- MySQL

### Cài đặt cơ sở dữ liệu

1. Tạo database MySQL tên là book_management. 

2. Trong thư mục src/main/resources, cập nhật file db.properties với thông tin kết nối database của bạn:
   
properties
   jdbc.driverClassName=com.mysql.cj.jdbc.Driver
   jdbc.url=jdbc:mysql://localhost:3306/book_management?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
   jdbc.username=your_username
   jdbc.password=your_password
   
   hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   hibernate.show_sql=true
   hibernate.format_sql=true
   hibernate.hbm2ddl.auto=update
   

3. Ứng dụng sẽ tự động tạo bảng books khi khởi chạy lần đầu nhờ cấu hình hibernate.hbm2ddl.auto=update.

### Build & Chạy ứng dụng


    

 Chạy ứng dụng:
    
    mvn clean compile tomcat7:run
    

3. API sẽ chạy tại địa chỉ http://localhost:8080/.

## Các API chính

| Phương thức | Endpoint                    | Chức năng                           |
|-------------|-----------------------------|-------------------------------------|
| POST        | /books                    | Thêm sách mới                       |
| GET         | /books/{id}               | Lấy thông tin sách theo ID          |
| GET         | /books                    | Lấy danh sách tất cả sách           |
| PUT         | /books/{id}               | Cập nhật thông tin sách             |
| DELETE      | /books/{id}               | Xóa sách theo ID                    |
| GET         | /books/by-author          | Tìm sách theo tác giả               |
| GET         | /books/by-title           | Tìm sách theo tiêu đề               |
| GET         | /books/by-year            | Tìm sách theo năm xuất bản          |
| GET         | /books/by-price-range     | Tìm sách theo khoảng giá            |

## Ví dụ request

*Thêm sách mới:*

POST /books
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "9780132350884",
  "description": "A Handbook of Agile Software Craftsmanship",
  "publicationYear": 2008,
  "price": 45.99
}

*Cập nhật sách:*

PUT /books/1
{
  "title": "Clean Code - Updated Edition",
  "author": "Robert C. Martin",
  "isbn": "9780132350884",
  "description": "A Handbook of Agile Software Craftsmanship - Updated",
  "publicationYear": 2008,
  "price": 49.99
}

*Tìm sách theo tác giả:*

GET /books/by-author?author=Robert

*Tìm sách theo tiêu đề:*

GET /books/by-title?title=Clean

*Tìm sách theo năm xuất bản:*

GET /books/by-year?year=2000

*Tìm sách theo khoảng giá:*

GET /books/by-price-range?minPrice=20&maxPrice=50

