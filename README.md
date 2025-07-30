# Practice Spring Data JPA

## Mục tiêu

Xây dựng project Java sử dụng **Spring Core + Spring Data JPA** để thực hiện CRUD cho các thực thể.

---

## Kỹ thuật yêu cầu

- Không sử dụng Spring Boot  
- Sử dụng Spring Core + Spring Data JPA  
- Java Config
- Tối thiểu 1 model
- Tối thiểu 4 method CRUD cho model
- Config entity (Annotation)

---

## Các Entity chính

### 1. User
- `id: int`
- `name: String`
- `age: int`

Quan hệ:
- One-to-Many với Order 

### 2. Order
- `id: int`
- `price: double`
- `user: User`

Quan hệ:
- Many-to-One với `User`


