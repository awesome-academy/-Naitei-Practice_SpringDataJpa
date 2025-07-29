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


**Demo**:
<img width="865" height="300" alt="image" src="https://github.com/user-attachments/assets/92ba5295-2c11-43c2-b714-c332aa9a20ac" />
<img width="1041" height="284" alt="image" src="https://github.com/user-attachments/assets/cbe87216-2920-467d-b1e7-ddb3a31964d0" />
<img width="929" height="698" alt="image" src="https://github.com/user-attachments/assets/cf65e47a-eaa1-4b9c-92e1-0e093e21fec9" />
<img width="907" height="352" alt="image" src="https://github.com/user-attachments/assets/c30ba694-29f4-4603-9d38-efc42b7d3edd" />
<img width="1023" height="655" alt="image" src="https://github.com/user-attachments/assets/8855c490-e966-4a07-a5a6-35a78332888e" />




 
