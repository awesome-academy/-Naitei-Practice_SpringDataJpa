
# Practice Spring Data JPA

## 🎯 Mục tiêu

Xây dựng project Java sử dụng **Spring Core + Spring Data JPA** để thực hiện các chức năng CRUD cho các thực thể.

---

## 🧰 Kỹ thuật sử dụng

- ❌ Không sử dụng Spring Boot
- ✅ Sử dụng Spring Core + Spring Data JPA
- 🧩 Java Config (Annotation-based)
- ✅ Tối thiểu 2 Entity
- ✅ Tối thiểu 4 method CRUD cho mỗi entity
- ✅ Cấu hình Entity bằng Annotation (JPA)

---

## 🗂️ Cấu trúc thư mục

```
src/
├── com.practice.jpa.config/           # Cấu hình Java (DataSource, JPA, TransactionManager)
├── com.practice.jpa.entity/           # Các entity JPA
├── com.practice.jpa.repository/       # Interface Repository
├── com.practice.jpa.service/          # Interface Service
├── com.practice.jpa.service.impl/     # Triển khai Service
├── com.practice.jpa/                  # MainApp và ứng dụng
└── application.properties             # Thông tin kết nối DB
```

---

## 🧱 Các Entity chính

### 1. Category

| Thuộc tính    | Kiểu dữ liệu | Ghi chú              |
|---------------|--------------|----------------------|
| `id`          | `Integer`    | Khóa chính           |
| `name`        | `String`     | Tên danh mục         |
| `description` | `String`     | Mô tả danh mục       |
| `parentId`    | `Integer`    | Danh mục cha (tùy chọn) |
| `createdAt`   | `Timestamp`  | Tự động tạo          |
| `updatedAt`   | `Timestamp`  | Tự động cập nhật     |

> Quan hệ:
> - **One-to-Many** với `Product` (Một danh mục chứa nhiều sản phẩm)

---

### 2. Product

| Thuộc tính       | Kiểu dữ liệu | Ghi chú              |
|------------------|--------------|----------------------|
| `id`             | `Integer`    | Khóa chính           |
| `name`           | `String`     | Tên sản phẩm         |
| `description`    | `String`     | Mô tả sản phẩm       |
| `imageUrl`       | `String`     | Ảnh sản phẩm         |
| `importPrice`    | `Double`     | Giá nhập             |
| `sellingPrice`   | `Double`     | Giá bán              |
| `stockQuantity`  | `Integer`    | Số lượng tồn kho     |
| `isFeatured`     | `Boolean`    | Nổi bật hay không    |
| `category`       | `Category`   | FK tới danh mục      |
| `createdAt`      | `Timestamp`  | Tự động tạo          |
| `updatedAt`      | `Timestamp`  | Tự động cập nhật     |

> Quan hệ:
> - **Many-to-One** với `Category` (Nhiều sản phẩm thuộc 1 danh mục)

---

## 🔧 Chức năng CRUD

- ✅ Tạo mới Category / Product
- ✅ Cập nhật Category / Product
- ✅ Xóa theo ID
- ✅ Tìm kiếm theo ID / Lấy tất cả

---

## 🚀 Hướng dẫn chạy

1. Tạo database MySQL tên `spring_jpa_demo`
2. Cập nhật `application.properties`:

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/spring_jpa_demo
jdbc.username=root
jdbc.password=yourpassword
```

3. Chạy class `MainApp.java` để thực thi các ví dụ thêm dữ liệu
4. Kiểm tra kết quả trong database

---

## 📌 Lưu ý

- Sử dụng `@CreationTimestamp` và `@UpdateTimestamp` cho các field thời gian
- Có thể tắt SQL log bằng cách chỉnh `hibernate.show_sql=false` trong cấu hình

---

## 📎 Mở rộng (Gợi ý)

- Tìm kiếm sản phẩm theo tên chứa keyword
- Lọc sản phẩm theo danh mục
- Sắp xếp theo giá tăng/giảm

---
