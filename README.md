# -Naitei-Practice_SpringDataJpa
Practice Spring data Jpa &amp; Spring core
# 🛒 Product Management CRUD Application

## 📌 Giới thiệu
Dự án này là một ứng dụng Java thuần (Java Core) sử dụng **Spring Core** và **Spring Data JPA** để xây dựng chức năng **CRUD (Create, Read, Update, Delete)** cho quản lý sản phẩm.  
Ứng dụng sử dụng **H2 Database (in-memory)** để lưu trữ dữ liệu và hỗ trợ **H2 Web Console** để xem dữ liệu trực tiếp trên trình duyệt.

---

## 🏗️ Cấu trúc thư mục

Product-Management/
│── src/main/java/com/example/Product/
│ ├── config/
│ │ ├── AppConfig.java # Cấu hình Spring Core + JPA
│ │ └── H2ServerConfig.java # Bật H2 Web Server và TCP Server
│ │
│ ├── entity/
│ │ └── Product.java # Entity sản phẩm (Model)
│ │
│ ├── repository/
│ │ └── ProductRepository.java # Spring Data JPA Repository
│ │
│ ├── service/
│ │ └── ProductService.java # Service xử lý logic CRUD
│ │
│ ├── MainApp.java # Class main chạy ứng dụng
│
│── pom.xml # Maven dependencies



## ⚙️ Công nghệ sử dụng
- **Java 17**
- **Spring Core (DI, IoC Container)**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database (In-memory)**
- **Maven**

---

## 🧩 Entity
### Product.java
- `id` (Long) – Khóa chính, tự động tăng  
- `name` (String) – Tên sản phẩm  
- `description` (String) – Mô tả sản phẩm  
- `price` (BigDecimal) – Giá sản phẩm  
- `category` (String) – Danh mục  
- `stockQuantity` (int) – Số lượng tồn kho  
- `createdAt`, `updatedAt` (LocalDateTime) – Thời gian tạo & cập nhật  

---

## 🔑 Chức năng CRUD
Ứng dụng hỗ trợ đầy đủ 4 chức năng:
1. **Create:** Thêm sản phẩm mới  
2. **Read:** Lấy danh sách sản phẩm hoặc tìm kiếm theo ID  
3. **Update:** Cập nhật thông tin sản phẩm  
4. **Delete:** Xóa sản phẩm theo ID  

---

## 🚀 Chạy ứng dụng
### 1️⃣ Build project
mvn clean compile
2️⃣ Chạy ứng dụng
bash
java -cp target/classes com.example.Product.MainApp
3️⃣ Mở H2 Web Console
Truy cập: http://localhost:8082
Thông tin đăng nhập:

JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: (để trống)