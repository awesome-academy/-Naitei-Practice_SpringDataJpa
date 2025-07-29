# Naitei Practice Spring Data JPA

Đây là dự án thực hành sử dụng Spring Data JPA và Spring Core. Ứng dụng cung cấp các chức năng quản lý thư viện (hiện tại chỉ có tính năng người dùng cơ bản như đăng ký, đăng nhập, đổi mật khẩu và xóa người dùng.

## Tính năng

- Đăng ký và đăng nhập người dùng
- Đổi mật khẩu
- Xóa người dùng
- API RESTful
- Sử dụng MySQL làm cơ sở dữ liệu
- Chạy trên Tomcat nhúng

## Công nghệ sử dụng

- Java 21+
- Spring Core
- Spring Data JPA
- Lombok
- MySQL
- Tomcat nhúng

## Hướng dẫn cài đặt

### Yêu cầu

- Java 21 trở lên
- Maven
- MySQL

### Cài đặt cơ sở dữ liệu

1. Tạo database MySQL tên là `library_management`. 

3. Trong thư mục src/main/resources, đổi tên file `pplication.properties.example` thành `application.properties` và điền thông tin kết nối database:
   - `db.driver`
   - `db.url`
   - `db.username`
   - `db.password`
   - Các thuộc tính Hibernate khác nếu cần

3. Chạy file `schema.sql` để tạo bảng trong cơ sở dữ liệu. File này nằm trong thư mục `src/main/resources`.

### Build & Chạy ứng dụng

1. Clone repository:
    ```bash
    git clone https://github.com/nguyenbaduy011/-Naitei-Practice_SpringDataJpa.git
    cd -Naitei-Practice_SpringDataJpa
    ```
2. Chạy ứng dụng:
Chạy trực tiếp từ IDE (Maven config chạy được nhưng không gọi API được).

3. API sẽ chạy tại địa chỉ `http://localhost:8080/`.

## Các API chính

| Phương thức | Endpoint                      | Chức năng             |
|-------------|-------------------------------|-----------------------|
| POST        | `/api/user/login`             | Đăng nhập             |
| POST        | `/api/user/register`          | Đăng ký người dùng    |
| PATCH       | `/api/user/changepassword`    | Đổi mật khẩu          |
| DELETE      | `/api/user/delete/{username}` | Xóa người dùng        |
| GET         | `/api/user/all`               | Lấy tất cả người dùng |

## Ví dụ request

**Đăng ký:**

```json
POST /api/user/register
{
  "username": "john",
  "password": "password123",
  "fullName": "John Doe",
  "email": "john@example.com",
  "phone": "123456789"
}
```

**Đăng nhập:**

```json
POST /api/user/login
{
  "username": "john",
  "password": "password123"
}
```

