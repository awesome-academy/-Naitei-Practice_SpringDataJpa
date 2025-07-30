# Demo CRUD Spring Data JPA - Quản Lý Hệ Thống AI

**➡️ [Video Demo](https://drive.google.com/drive/folders/12M23deSen9ny1QYawHzSf8Ix35xtkss-?hl=vi)**

Đây là một dự án web CRUD (Tạo, Đọc, Cập nhật, Xóa) đơn giản được xây dựng trên nền tảng Spring MVC và Spring Data JPA cổ điển (không dùng Spring Boot). Ứng dụng cho phép người dùng quản lý một danh sách các hệ thống AI giả tưởng thông qua giao diện web.

Mục đích chính của dự án là để chứng minh kiến thức nền tảng về việc cấu hình một ứng dụng Spring từ đầu bằng Java-based configuration và tích hợp tầng persistence sử dụng Spring Data JPA và Hibernate.

## Chức Năng

-   **Tạo (Create):** Thêm một hệ thống AI mới vào cơ sở dữ liệu qua form trên web.
-   **Đọc (Read):** Xem toàn bộ danh sách các hệ thống AI.
-   **Cập Nhật (Update):** Chỉnh sửa thông tin của một hệ thống AI đã có.
-   **Xóa (Delete):** Xóa một hệ thống AI khỏi cơ sở dữ liệu.
-   Giao diện người dùng đơn giản, tích hợp tất cả chức năng trên một trang duy nhất.

## Công Nghệ Sử Dụng

-   **Backend:**
    -   Java 21
    -   Spring Framework 6.1.10 (Core, MVC, ORM)
    -   Spring Data JPA 3.3.1
    -   Hibernate 6.5.2
    -   MySQL Connector/J 8.4.0
    -   Jackson Databind (để xử lý JSON)
-   **Build & Deployment:**
    -   Apache Maven 3+
    -   Apache Tomcat 10.1+ (Bắt buộc vì dùng `jakarta.*` namespace)
-   **Frontend:**
    -   HTML, CSS, và JavaScript cơ bản (sử dụng Fetch API để gửi request).

## Yêu Cầu Môi Trường

Trước khi chạy ứng dụng, hãy đảm bảo bạn đã cài đặt:
-   JDK 21 hoặc mới hơn.
-   Apache Maven.
-   MySQL Server đang hoạt động.
-   Apache Tomcat v10.1 hoặc mới hơn.

## Hướng Dẫn Chạy Dự Án

1.  **Clone Repository**
    ```bash
    git clone https://github.com/Kuan-niisan/-Naitei-Practice_SpringDataJpa.git
    cd -Naitei-Practice_SpringDataJpa
    ```

2.  **Tạo Database**
    Kết nối vào MySQL server của bạn và chạy lệnh sau để tạo schema:
    ```sql
    CREATE DATABASE spring_jpa_demo;
    ```

3.  **Cấu Hình Kết Nối Database**
    Mở file `src/main/resources/database.properties` và kiểm tra lại `db.username` và `db.password` để khớp với tài khoản MySQL trên máy của bạn.

4.  **Build Dự Án**
    Sử dụng Maven để biên dịch code và đóng gói thành file `.war`.
    ```bash
    mvn clean install
    ```
    Lệnh này sẽ tạo ra file `classic-spring-jpa-demo.war` trong thư mục `target/`.

5.  **Deploy Lên Tomcat**
    -   Copy file `classic-spring-jpa-demo.war`.
    -   Dán vào thư mục `webapps/` của Apache Tomcat.

6.  **Khởi Động Tomcat**
    -   Di chuyển đến thư mục `bin/` của Tomcat.
    -   Chạy file `startup.sh` (trên Linux/macOS) hoặc `startup.bat` (trên Windows).

7.  **Truy Cập Ứng Dụng**
    Mở trình duyệt và truy cập vào địa chỉ:
    **`http://localhost:8080/classic-spring-jpa-demo/`**

## Các API Endpoints

Ứng dụng cung cấp các RESTful API sau:

| Method | URL                  | Chức năng                            |
|--------|----------------------|--------------------------------------|
| `POST` | `/api/ai-systems`    | Tạo một hệ thống AI mới.             |
| `GET`  | `/api/ai-systems`    | Lấy danh sách tất cả hệ thống AI.  |
| `GET`  | `/api/ai-systems/{id}` | Lấy thông tin một hệ thống AI theo ID. |
| `PUT`  | `/api/ai-systems/{id}` | Cập nhật một hệ thống AI đã có.       |
| `DELETE`| `/api/ai-systems/{id}` | Xóa một hệ thống AI theo ID.      |