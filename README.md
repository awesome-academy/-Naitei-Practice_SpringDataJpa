# -Naitei-Practice\_SpringDataJpa

Practice Spring data Jpa \& Spring core

\# Project: quản lí chấm công cho nhân viên



\1 Giới thiệu

Đây là một dự án Spring Framework nhỏ minh họa cách cấu hình và sử dụng Spring Data JPA (với Hibernate) để tương tác với cơ sở dữ liệu MySQL. Dự án này bao gồm các lớp Entity, Repository, Service và cấu hình Spring Java-based kết hợp XML config



\2 Mục tiêu chính của dự án

\* Cấu hình Spring Application Context bằng Java-based Configuration (`@Configuration`).

\* Sử dụng Spring Data JPA để quản lý và tương tác với các JPA Entities.

\* Minh họa Dependency Injection và Transaction Management trong Spring.

\* Giải quyết các vấn đề cấu hình phổ biến khi thiết lập Spring JPA từ đầu.



\3 Công nghệ sử dụng

\* \*\*Spring Framework:\*\* Core, Context, Data JPA, ORM, Aspects

\* \*\*Hibernate:\*\* JPA Implementation

\* \*\*MySQL:\*\* Cơ sở dữ liệu

\* \*\*Maven:\*\* Công cụ quản lý dự án và dependency



\4 Cấu trúc dự án

├── src/

│   ├── main/

│   │   ├── java/

│   │   │   └── org.example/           # Package gốc của ứng dụng

│   │   │       ├── config/            # Chứa lớp cấu hình Spring (@Configuration)

│   │   │       │   └── Configuration.java

│   │   │       ├── Entity/            # Chứa các JPA Entity (@Entity)

│   │   │       │   ├── Attendance.java

│   │   │       │   └── Employee.java

│   │   │       ├── Repository/        # Chứa các Spring Data JPA Repository (@Repository)

│   │   │       │   ├── AttendanceRepository.java

│   │   │       │   └── EmployeeRepository.java

│   │   │       ├── Service/           # Chứa các lớp Service (@Service)

│   │   │       │   ├── AttendanceService.java (interface)

│   │   │       │   └── AttendanceServiceIpml.java (implementation)

│   │   │       └── App.java           # Lớp chứa phương thức main để chạy ứng dụng

│   │   └── resources/

│   │       └── app.properties         # Chứa thông tin cấu hình cơ sở dữ liệu

│   │       └── beans.xml              # (Nếu không sử dụng, nên xóa hoặc bỏ qua)

│   └── test/                      # (Thư mục cho các bài kiểm tra)

├── pom.xml                        # File cấu hình Maven

└── README.md                      # File này

\### Yêu cầu

\* Java Development Kit (JDK) 17+

\* Maven 3.6+

\* MySQL Server (đang chạy trên `localhost:3306` hoặc theo cấu hình của bạn)

\* IDE như IntelliJ IDEA hoặc Eclipse

\5 Mô tả database
\Bảng Employee: gồm thông tin nhân viên, 1 nhân viên có nhiều lịch chấm công
\Bảng Attendance: gồm thông tin lịch chấm công, chỉ thuộc về 1 nhân viên


\### Các bước cài đặt

1\.  \*\*Clone repository (nếu có):\*\*

&nbsp;   ```bash

&nbsp;   git clone \[https://github.com/new](https://github.com/new)

&nbsp;   cd \[Tên Project của bạn]

&nbsp;   ```

&nbsp;   Nếu bạn không sử dụng Git, chỉ cần tải xuống/giải nén mã nguồn.



2\.  \*\*Cấu hình Cơ sở dữ liệu:\*\*

&nbsp;   \* Tạo một cơ sở dữ liệu MySQL mới. Sau đó restore file AttendanceManagement\_backup.sql

&nbsp;   \* Cập nhật file `src/main/resources/app.properties` với thông tin kết nối cơ sở dữ liệu của bạn:

&nbsp;       app.properties

&nbsp;       spring.datasource.driver=com.mysql.cj.jdbc.Driver

&nbsp;       spring.datasource.url=jdbc:mysql://localhost:3306/database\_name

&nbsp;       spring.datasource.username=username

&nbsp;       spring.datasource.password=password

&nbsp; 

3\.  \*\*Build dự án với Maven:\*\*

&nbsp;   Mở terminal trong thư mục gốc của dự án và chạy:

&nbsp;   ```bash

&nbsp;   mvn clean install

&nbsp;   ```

&nbsp;   Thao tác này sẽ tải xuống tất cả các dependency và biên dịch mã nguồn.



