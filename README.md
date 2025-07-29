# 📌 Quản lý Tình nguyện viên Chiến dịch Tình nguyện – Spring Core + Spring Data JPA

## 🚀 Mục tiêu

Xây dựng project Java không dùng Spring Boot, sử dụng **Spring Core + Spring Data JPA** để thực hiện CRUD cho các thực thể và quan hệ thực tế. Đề tài tập trung vào mô hình **quản lý tình nguyện viên** trong các chiến dịch hoạt động xã hội.

---

## ✅ Kỹ thuật yêu cầu

- Không sử dụng Spring Boot
- Sử dụng Spring Core + Spring Data JPA
- Java Config (không dùng XML)
- Tối thiểu 1 model (ở đây có đến 5)
- Tối thiểu 4 method CRUD
- Sử dụng annotation để config Entity
- Chạy với `AnnotationConfigApplicationContext`

---

## 🧱 Các Entity chính

### 1. `Volunteer`

- `id: Long`
- `fullName: String`
- `email: String`
- `phone: String`
- `joinedDate: Date`
- Quan hệ:
  - One-to-Many với `ParticipationLog`
  - One-to-Many với `SkillLevel`

---

### 2. `Campaign`

- `id: Long`
- `name: String`
- `startDate: Date`
- `endDate: Date`
- `location: String`
- Quan hệ:
  - One-to-Many với `ParticipationLog`

---

### 3. `Skill`

- `id: Long`
- `name: String` _(ví dụ: Chụp ảnh, MC, Hậu cần...)_
- Quan hệ:
  - One-to-Many với `SkillLevel`

---

### 4. `ParticipationLog` (Bảng trung gian mở rộng Volunteer – Campaign)

- `id: Long`
- `volunteer: Volunteer`
- `campaign: Campaign`
- `role: String` _(Leader, Member, Hỗ trợ hậu cần...)_
- `joinedDate: Date`
- `daysParticipated: Integer`

---

### 5. `SkillLevel` (Quan hệ mở rộng giữa Volunteer – Skill)

- `id: Long`
- `volunteer: Volunteer`
- `skill: Skill`
- `level: Enum (BEGINNER, INTERMEDIATE, ADVANCED)`

---

## 🔗 Quan hệ giữa các bảng

- `Volunteer` ⬌ `Campaign` thông qua `ParticipationLog` (`@OneToMany`)
- `Volunteer` ⬌ `Skill` thông qua `SkillLevel` (`@OneToMany`)
- Mọi quan hệ đều sử dụng `@JoinColumn`, `@ManyToOne`, `@OneToMany` rõ ràng (tránh dùng quan hệ N-N thuần)

---

## 📚 Các chức năng chính (CRUD + nâng cao)

| Chức năng                                         | Mô tả                                  |
| ------------------------------------------------- | -------------------------------------- |
| Thêm / Xem / Sửa / Xóa `Volunteer`                | Basic CRUD                             |
| Thêm / Xem / Sửa / Xóa `Campaign`                 | Basic CRUD                             |
| Thêm / Xem / Sửa / Xóa `Skill`                    | Basic CRUD                             |
| Thêm log tham gia Campaign (`ParticipationLog`)   | Ghi nhận vai trò + thời gian           |
| Gán kỹ năng và level cho Volunteer (`SkillLevel`) | Mỗi Volunteer có nhiều skill với level |
| Tìm volunteer theo kỹ năng cụ thể                 | Sử dụng query                          |
| Tìm volunteer đang tham gia campaign đang diễn ra | Dựa vào ngày bắt đầu/kết thúc          |

---

## 🧠 Kỹ thuật nâng cao đã áp dụng

| Kỹ thuật                                | Ứng dụng                                |
| --------------------------------------- | --------------------------------------- |
| `@ManyToOne`, `@OneToMany`              | Các mối quan hệ chính                   |
| `@JoinColumn`, `@MappedBy`              | Điều hướng quan hệ                      |
| `@Enumerated(EnumType.STRING)`          | Lưu enum level kỹ năng                  |
| Java Config                             | Config DataSource, EntityManagerFactory |
| `AnnotationConfigApplicationContext`    | Chạy chương trình                       |
| `@Query` với JPQL                       | Tìm kiếm nâng cao                       |
| `@Transactional`                        | Quản lý transaction                     |
| `@Component`, `@Service`, `@Repository` | Dependency Injection                    |

---

## 📁 Cấu trúc thư mục

```plaintext
src/
└── main/
    ├── java/
    │   └── org/
    │       └── example/
    │           ├── config/               <-- JavaConfig
    │           │   ├── AppConfig.java
    │           │   ├── DatabaseConfig.java
    │           │   └── JpaConfig.java
    │           ├── entity/               <-- Volunteer, Campaign, Skill, ParticipationLog, SkillLevel
    │           │   ├── Volunteer.java
    │           │   ├── Campaign.java
    │           │   ├── Skill.java
    │           │   ├── ParticipationLog.java
    │           │   └── SkillLevel.java
    │           ├── repository/           <-- Interface JpaRepository
    │           │   ├── VolunteerRepository.java
    │           │   ├── CampaignRepository.java
    │           │   ├── SkillRepository.java
    │           │   ├── ParticipationLogRepository.java
    │           │   └── SkillLevelRepository.java
    │           ├── service/              <-- Business Logic
    │           │   ├── VolunteerService.java
    │           │   ├── CampaignService.java
    │           │   └── SkillService.java
    │           ├── DataInitializer.java  <-- Khởi tạo dữ liệu mẫu
    │           └── MainApp.java          <-- Chạy ứng dụng
    └── resources/
```

---

## 🛠️ Công nghệ & Dependencies

### **Core Framework:**

- **Spring Core 6.2.6** - Dependency Injection
- **Spring Context 6.2.6** - Application Context
- **Spring ORM 6.2.6** - Object-Relational Mapping
- **Spring Data JPA 3.3.5** - Data Access Layer

### **Database & ORM:**

- **Hibernate 6.6.13.Final** - JPA Implementation
- **H2 Database 2.2.224** - In-memory Database
- **Jakarta Persistence API 3.1.0** - JPA Specification

### **Build Tool:**

- **Maven** - Dependency Management & Build

---

## 🚀 Hướng dẫn chạy ứng dụng

### **1. Yêu cầu hệ thống:**

- Java 11 trở lên
- Maven 3.6+

### **2. Build project:**

```bash
mvn clean compile
```

### **3. Chạy ứng dụng:**

```bash
mvn exec:java -Dexec.mainClass="org.example.MainApp"
```

### **4. Hoặc chạy từ IDE:**

- Chạy class `org.example.MainApp`

---

## 💻 Giao diện ứng dụng

### **Menu chính:**

```
📋 === MENU CHÍNH ===
1. 👥 Quản lý Tình nguyện viên
2. 🎯 Quản lý Chiến dịch
3. 🛠️ Quản lý Kỹ năng
4. 📊 Báo cáo & Thống kê
0. 🚪 Thoát
```

### **Chức năng chi tiết:**

#### **👥 Quản lý Tình nguyện viên:**

- ➕ Thêm tình nguyện viên mới
- 📋 Xem danh sách tình nguyện viên
- 🔍 Tìm tình nguyện viên theo email/tên
- 🛠️ Gán kỹ năng cho tình nguyện viên
- 📊 Xem kỹ năng của tình nguyện viên
- ❌ Xóa tình nguyện viên

#### **🎯 Quản lý Chiến dịch:**

- ➕ Thêm chiến dịch mới
- 📋 Xem danh sách chiến dịch
- 🔍 Tìm chiến dịch theo tên/địa điểm
- ⏰ Xem chiến dịch đang diễn ra
- 👥 Thêm tình nguyện viên vào chiến dịch
- 📊 Xem danh sách tham gia chiến dịch
- ❌ Xóa chiến dịch

#### **🛠️ Quản lý Kỹ năng:**

- ➕ Thêm kỹ năng mới
- 📋 Xem danh sách kỹ năng
- 🔍 Tìm kỹ năng theo tên
- 👥 Xem tình nguyện viên có kỹ năng cụ thể
- 🏆 Xem tình nguyện viên có kỹ năng cao cấp
- ❌ Xóa kỹ năng

#### **📊 Báo cáo & Thống kê:**

- 👥 Tổng số tình nguyện viên
- 🎯 Tổng số chiến dịch
- 🛠️ Tổng số kỹ năng
- ⏰ Tình nguyện viên đang hoạt động
- 📈 Chiến dịch đang diễn ra
- 🏆 Thống kê kỹ năng cao cấp

---

## 📊 Dữ liệu mẫu

### **Tình nguyện viên (20 người):**

- Nguyễn Văn An, Trần Thị Bình, Lê Hoàng Cường,...
- JoinDate: 15/03/2023
- Mỗi người có 2-3 kỹ năng với level ngẫu nhiên

### **Chiến dịch (4 chiến dịch):**

- **Vị Xuân 5**: Đồng Tháp (15/01/2024 - 15/02/2024)
- **Sắc Xanh 3**: An Giang (01/06/2024 - 15/06/2024)
- **Vị Xuân 6**: TP.HCM (05/01/2025 - 12/01/2025)
- **Sắc Xanh 4**: Bến Tre (19/07/2025 - 29/07/2025)

### **Kỹ năng (14 kỹ năng):**

- Lên nội dung, timeline
- Hoạt náo
- Gây quỹ
- Nấu ăn
- Chăm lo đời sống tình nguyện viên
- Sơ cứu
- Chụp ảnh
- Edit video
- Blend ảnh
- Viết content
- Ca hát
- Nhảy múa
- Chơi đàn
- Lãnh đạo

### **Vai trò trong chiến dịch:**

- Tình nguyện viên, Lãnh đạo, Thư ký, Tài xế, Y tế, Đầu bếp, MC, Photographer

---

## 🔧 Cấu hình Database

### **H2 Database (In-memory):**

```java
// DatabaseConfig.java
dataSource.setDriverClassName("org.h2.Driver");
dataSource.setUrl("jdbc:h2:mem:volunteer_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
dataSource.setUsername("sa");
dataSource.setPassword("");
```

### **Hibernate Configuration:**

```java
// JpaConfig.java
properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
properties.setProperty("hibernate.show_sql", "false");
properties.setProperty("hibernate.format_sql", "false");
```

---

## 🎯 Tính năng nổi bật

### **1. Quản lý quan hệ phức tạp:**

- Volunteer ↔ Campaign (qua ParticipationLog)
- Volunteer ↔ Skill (qua SkillLevel với enum level)
- Xử lý cascade delete an toàn

### **2. Tìm kiếm nâng cao:**

- Tìm volunteer theo kỹ năng
- Tìm volunteer đang hoạt động
- Tìm campaign theo thời gian/địa điểm
- Thống kê kỹ năng cao cấp

### **3. Giao diện thân thiện:**

- Menu console tiếng Việt
- Hướng dẫn rõ ràng
- Xử lý lỗi gracefully
- Format dữ liệu đẹp

### **4. Kiến trúc clean:**

- Tách biệt rõ ràng: Entity, Repository, Service, Config
- Sử dụng Spring Data JPA đúng cách
- Dependency Injection hoàn chỉnh
- Transaction management

---

## 🚀 Kết luận

Dự án đã hoàn thành đầy đủ các yêu cầu kỹ thuật:

- ✅ Không sử dụng Spring Boot
- ✅ Sử dụng Spring Core + Spring Data JPA
- ✅ Java Config hoàn toàn
- ✅ 5 Entity với quan hệ phức tạp
- ✅ CRUD operations đầy đủ
- ✅ Annotation-based configuration
- ✅ `AnnotationConfigApplicationContext`
- ✅ Console application hoàn chỉnh
- ✅ Dữ liệu mẫu phong phú

**Sẵn sàng cho việc mở rộng và triển khai thực tế!** 🎉
