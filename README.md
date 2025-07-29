# **Quản lý Phim - Spring Core \& Spring Data JPA**

Dự án này minh họa cách xây dựng các chức năng CRUD (Create, Read, Update, Delete) cơ bản bằng cách sử dụng Spring Core và Spring Data JPA mà không phụ thuộc vào Spring Boot. Dự án tập trung vào việc cấu hình thủ công và hiểu rõ sự tương tác giữa các thành phần cốt lõi của Spring Framework.



### 1\. Mục tiêu

Xây dựng một ứng dụng hoàn chỉnh từ đầu chỉ với Spring Core và các module liên quan.

Hiểu rõ cách cấu hình DataSource, EntityManagerFactory, và TransactionManager bằng Java-based configuration (@Configuration).

Triển khai và sử dụng Spring Data JPA để giảm thiểu code boilerplate ở tầng truy cập dữ liệu (DAO/Repository).



### 2\. Các Entity

##### 2.1. Movie

* id (Long): Khóa chính, tự tăng.
* title (String): Tên phim.
* releaseYear (Year): Năm phát hành.
* director (String): Tên đạo diễn.



##### 2.2. Genre

* id (Long): Khóa chính, tự tăng.
* name (String): Tên thể loại (là duy nhất).



##### 2.3. MovieGenre

* id (Long): Khóa chính, tự tăng.
* movie (Movie): Khóa ngoại đến phim.
* genre (Genre): Khóa ngoại đến thể loại.



##### 2.4. Auditable

* createdAt (LocalDateTime): Thời điểm tạo
* updatedAt (LocalDateTime): Thời điểm cập nhật lần cuối



### 3\. Quan hệ giữa các Bảng

Mối quan hệ giữa Movie và Genre là Nhiều-Nhiều. Trong dự án này, mối quan hệ đó được triển khai bằng cách sử dụng một bảng trung gian có khóa chính riêng (movie\_genres), tạo thành hai mối quan hệ Một-Nhiều:

Movie 1 -- \* MovieGenre: Một bộ phim có thể có nhiều bản ghi liên kết thể loại.

Genre 1 -- \* MovieGenre: Một thể loại có thể có nhiều bản ghi liên kết với các bộ phim.

MovieGenre \* -- 1 Movie: Một bản ghi liên kết chỉ thuộc về một bộ phim.

MovieGenre \* -- 1 Genre: Một bản ghi liên kết chỉ thuộc về một thể loại.



### 4\. Các chức năng chính

Ứng dụng cung cấp các chức năng CRUD cho các thực thể thông qua các lớp Service.



##### GenreService

* createGenre(name): Tạo một thể loại mới.
* getAllGenres(): Lấy danh sách tất cả các thể loại.
* getGenreById(id): Tìm một thể loại theo ID.
* updateGenreName(id, newName): Đổi tên mới cho một thể loại theo ID.
* deleteGenre(id): Xóa một thể loại.



##### MovieService

* createMovie(title, year, director, genreIds): Tạo một bộ phim mới và gán nó vào một hoặc nhiều thể loại dựa trên danh sách ID của thể loại.
* getAllMovies(): Lấy danh sách tất cả các bộ phim.
* getMovieById(id): Tìm một bộ phim theo ID.
* getMovieByTitle(title): Tìm một bộ phim theo Title.
* getMovieByReleaseYear(year): Tìm một bộ phim theo năm phát hành.
* updateMovieGenres(movieId, newGenreIds): Cập nhật lại toàn bộ danh sách thể loại cho một bộ phim.
* deleteMovie(id): Xóa một bộ phim.



### 5\. Hướng dẫn Cài đặt và Chạy

5.1. Clone Repository

5.2. Tạo database cho dự án: Chạy file movie.sql trong classpath

5.3. Thêm dữ liệu mẫu cho database: Chạy file sample\_data.sql trong classpath

5.4. Sao chép file src/main/resources/database.properties.example thành src/main/resources/database.properties.

5.5. Mở file database.properties và cập nhật các thông tin db.url, db.username, và db.password cho phù hợp với môi trường của bạn.

5.6. Build dự án với Maven: 

* cd movietime
* mvn clean install

5.7. Chạy ứng dụng:

* Để kiểm tra kết nối DB: Chạy phương thức main trong file src/main/java/com/example/movie/**DatabaseConnectionChecker.java**
* Để chạy demo CRUD: Chạy phương thức main trong file src/main/java/com/example/movie/**Main.java**
