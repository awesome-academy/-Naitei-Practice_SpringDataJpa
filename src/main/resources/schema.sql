-- Bảng tác giả
CREATE TABLE authors
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    biography  TEXT,
    avatar     VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Bảng thể loại (khóa ngoại tự tham chiếu cho parent_id)
CREATE TABLE genres
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(100) NOT NULL UNIQUE,
    parent_id INT DEFAULT NULL,
    FOREIGN KEY (parent_id) REFERENCES genres (id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Bảng nhà xuất bản
CREATE TABLE publishers
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    address    TEXT,
    logo       VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Bảng sách
CREATE TABLE books
(
    id                   INT AUTO_INCREMENT PRIMARY KEY,
    title                VARCHAR(255) NOT NULL,
    description          TEXT,
    author_id            INT          NOT NULL,
    genre_id             INT          NOT NULL,
    publisher_id         INT          NOT NULL,
    published_year       INT,
    borrow_duration_days INT          NOT NULL DEFAULT 14 CHECK (borrow_duration_days > 0),
    created_at           DATETIME              DEFAULT CURRENT_TIMESTAMP,
    total_quantity       INT          NOT NULL DEFAULT 0,
    available_quantity   INT          NOT NULL DEFAULT 0,
    FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (publisher_id) REFERENCES publishers (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Bảng bản sao sách
CREATE TABLE copies
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    book_id    INT         NOT NULL,
    copy_code  VARCHAR(50) NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Bảng sách không khả dụng
CREATE TABLE book_unavailabilities
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    copy_id           INT NOT NULL,
    status            ENUM('lost', 'damaged') DEFAULT 'damaged',
    issue_description TEXT,
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (copy_id) REFERENCES copies (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Bảng hình ảnh
CREATE TABLE images
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    book_id    INT          NOT NULL,
    image      VARCHAR(255) NOT NULL,
    is_cover   BOOLEAN  DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Bảng người dùng
CREATE TABLE users
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    username        VARCHAR(100) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    full_name       VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL UNIQUE,
    phone           VARCHAR(15)  NOT NULL UNIQUE,
    avatar          VARCHAR(255),
    role            VARCHAR(255) DEFAULT 'user' CHECK ( role IN ('admin', 'user')),
    activation_date DATE     DEFAULT NULL,
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Bảng yêu cầu mượn sách
CREATE TABLE borrow_requests
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    user_id       INT  NOT NULL,
    from_date     DATE NOT NULL,
    to_date       DATE NOT NULL,
    status        ENUM('pending', 'approved', 'partially_approved', 'rejected', 'cancelled') DEFAULT 'pending',
    reject_reason TEXT,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Bảng chi tiết yêu cầu mượn
CREATE TABLE borrow_request_details
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    borrow_request_id INT NOT NULL,
    copy_id           INT NOT NULL,
    status            ENUM('pending', 'approved', 'rejected') DEFAULT 'pending',
    reject_reason     TEXT     DEFAULT NULL,
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (borrow_request_id) REFERENCES borrow_requests (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (copy_id) REFERENCES copies (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Bảng lịch sử mượn sách
CREATE TABLE borrow_records
(
    id                       INT AUTO_INCREMENT PRIMARY KEY,
    borrow_request_detail_id INT  NOT NULL,
    borrowed_at              DATE NOT NULL,
    due_date                 DATE NOT NULL,
    returned_at              DATE,
    status                   ENUM('borrowed', 'returned', 'overdue') DEFAULT 'borrowed',
    FOREIGN KEY (borrow_request_detail_id) REFERENCES borrow_request_details (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Bảng theo dõi (follow)
CREATE TABLE follows
(
    user_id     INT NOT NULL,
    target_type ENUM('author', 'publisher') NOT NULL,
    target_id   INT NOT NULL,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, target_id, target_type),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Bảng bình luận sách
CREATE TABLE book_comments
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    user_id           INT  NOT NULL,
    book_id           INT  NOT NULL,
    content           TEXT NOT NULL,
    parent_comment_id INT      DEFAULT NULL,
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (parent_comment_id) REFERENCES book_comments (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Bảng sách yêu thích
CREATE TABLE favorites
(
    user_id    INT NOT NULL,
    book_id    INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, book_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Bảng đánh giá sách
CREATE TABLE ratings
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT NOT NULL,
    book_id    INT NOT NULL,
    rating     INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment    TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Chỉ mục (Indexes)
CREATE INDEX idx_books_title ON books (title);
CREATE INDEX idx_books_author_id ON books (author_id);
CREATE INDEX idx_copies_book_id ON copies (book_id);
CREATE INDEX idx_copies_copy_code ON copies (copy_code);
CREATE INDEX idx_unavail_copy_id ON book_unavailabilities (copy_id);
CREATE INDEX idx_images_book_id ON images (book_id);
CREATE INDEX idx_borrow_requests_user_id ON borrow_requests (user_id);
CREATE INDEX idx_borrow_request_detail_req_id ON borrow_request_details (borrow_request_id);
CREATE INDEX idx_borrow_request_detail_copy_id ON borrow_request_details (copy_id);
CREATE INDEX idx_borrow_records_detail_id ON borrow_records (borrow_request_detail_id);
CREATE INDEX idx_comments_book_id ON book_comments (book_id);
CREATE INDEX idx_comments_user_id ON book_comments (user_id);
CREATE INDEX idx_follows_target ON follows (target_type, target_id);