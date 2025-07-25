# Student Management System

Đây là một project Java sử dụng JPA để quản lý sinh viên, trường học, môn học và mối quan hệ giữa sinh viên và môn học thông qua bảng trung gian `StudentSubject`.

## Kỹ thuật

- Sử dụng Spring Core + Spring Data JPA
- Java Config (AppConfig)

---

## Kiến trúc dự án

### Mô hình dữ liệu (Models)

* **`School`**
  Đại diện cho một trường học. Mỗi trường có nhiều sinh viên.

* **`Student`**
  Đại diện cho sinh viên, có ràng buộc với `School`.

* **`Subject`**
  Đại diện cho môn học. Mỗi môn có nhiều sinh viên thông qua bảng `StudentSubject`.

* **`StudentSubject`**
  Bảng trung gian quản lý mối quan hệ nhiều-nhiều giữa `Student` và `Subject`.

* **`StudentSubjectId`**
  Là `@Embeddable` composite key của bảng `StudentSubject`.

## Mối quan hệ giữa các bảng

```text
School (1) ────< (N) Student
Student (N) ────< (M) Subject thông qua StudentSubject
```

### Repository

Sử dụng Spring Data JPA:

* `SchoolRepository`
* `StudentRepository`
* `SubjectRepository`
* `StudentSubjectRepository`

Các repository này kế thừa `JpaRepository`, hỗ trợ đầy đủ CRUD, có mở rộng truy vấn tùy chỉnh (JPLQ & Native SQL).

### Service

Tầng service tách biệt business logic với tầng controller/main. Gồm:

* `StudentService` / `StudentServiceImpl`
* `SubjectService` / `SubjectServiceImpl`
* `StudentSubjectService` / `StudentSubjectServiceImpl`

Cung cấp các chức năng CRUD

## Tính năng

* Quản lý sinh viên: thêm/sửa/xóa/tìm kiếm
* Quản lý môn học
* Quản lý trường học
* Ghi nhận môn học đã đăng ký của sinh viên (many-to-many)


## Kỹ thuật khác (Hiện tại chưa áp dụng):
- Triển khai API (MVC)
- Validation


