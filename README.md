# 📚 Hệ thống quản lý khóa học – CRUD với Spring Data JPA

## 📝 Giới thiệu
Dự án mô phỏng một hệ thống quản lý khóa học đơn giản, bao gồm các thực thể: **Giảng viên (Instructor)**, **Khóa học (Course)** và **Ghi danh sinh viên (Enrollment)**.  
Người dùng có thể thực hiện các thao tác **CRUD** với từng thực thể và quản lý mối quan hệ giữa chúng.

## 🛠️ Kỹ thuật sử dụng
- **Ngôn ngữ:** Java 21
- **Framework:** Spring Core + Spring Data JPA (không sử dụng Spring Boot)
- **Quản lý phụ thuộc:** Maven
- **Cơ sở dữ liệu:** MySQL
- **Kiểm thử:** JUnit 5
- **IDE:** IntelliJ IDEA Community Edition

## 🗄️ Mô tả cơ sở dữ liệu và mối quan hệ
### ⚙️ Các bảng chính:
- **Instructor**: `id`, `name`, `email`
- **Course**: `id`, `title`, `description`, `instructor_id`
- **Enrollment**: `id`, `student_name`, `enrollment_date`, `course_id`

### 🔗 Mối quan hệ:
```
Instructor (1) ------- (N) Course
Course     (1) ------- (N) Enrollment
```

## 💡 Các tính năng sử dụng với JPA
- Mapping quan hệ 1-n (Instructor-Course), 1-n (Course-Enrollment)
- Tự động ánh xạ entity với bảng trong DB (annotation: `@Entity`, `@Table`, `@ManyToOne`, `@OneToMany`, ...)
- Sử dụng `JpaRepository` để thực hiện các thao tác CRUD nhanh chóng
- Viết query tìm kiếm bằng từ khóa với `findByNameContainingIgnoreCase`
- Tùy chỉnh logic dịch vụ trong tầng `Service` và `ServiceImpl`

## 🚀 Hướng mở rộng
- Phát triển thành Web App (Thymeleaf hoặc React)
- Thêm phân quyền: Admin, Instructor, Student
- Gửi email khi ghi danh
- Thống kê số lượng học viên theo khóa
- REST API với Swagger

## 📽️ Trình bày dự án

Để hiểu rõ hơn về kiến trúc, các chức năng chính và quy trình phát triển của dự án, vui lòng xem slide trình bày chi tiết:

🔗 **[Truy cập slide trên Canva](https://www.canva.com/design/DAGk_glRndE/4_XYDI8QCuWkrJKGsHXGJA/edit)**
