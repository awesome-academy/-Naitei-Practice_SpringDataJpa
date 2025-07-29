# Quản lý sách – Spring Core + Spring Data JPA

---

## Mục tiêu

Xây dựng project Java không dùng Spring Boot, sử dụng **Spring Core + Spring Data JPA** để thực hiện các chức năng CRUD cơ bản cho đối tượng `Book`. Dự án mô phỏng hệ thống quản lý sách trong thư viện hoặc nhà sách.

---

## Kỹ thuật yêu cầu

- Không sử dụng Spring Boot  
- Sử dụng Spring Core + Spring Data JPA  
- Cấu hình bằng XML (không dùng Java Config)  
- Tối thiểu 1 Entity (`Book`)  
- Tối thiểu 4 method CRUD (Create, Read, Update, Delete)  
- Dùng annotation để đánh dấu entity  
- Chạy bằng `ClassPathXmlApplicationContext`  
- Kết nối DB qua file `db.properties`

---

## Entity chính

### 1. `Book`

| Thuộc tính     | Kiểu dữ liệu |
|----------------|--------------|
| `id`           | Long         |
| `title`        | String       |
| `author`       | String       |
| `price`        | Double       |
| `publishedDate`| LocalDate    |

**Ghi chú**: 
- `Book` là một entity sử dụng annotation `@Entity`, `@Id`, `@GeneratedValue`, `@Column`.
- Mapping đến bảng `book` trong CSDL.
- Sử dụng Spring Data JPA để thao tác với DB thông qua interface `BookRepository`.



