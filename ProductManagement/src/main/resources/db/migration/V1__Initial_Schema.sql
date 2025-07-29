CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at DATETIME(6),
    last_modified_at DATETIME(6)
);

CREATE TABLE product_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description TEXT,
    created_at DATETIME(6),
    last_modified_at DATETIME(6)
);

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    version BIGINT,
    category_id BIGINT,
    details_id BIGINT UNIQUE,
    created_at DATETIME(6),
    last_modified_at DATETIME(6),
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (details_id) REFERENCES product_details(id)
);

CREATE TABLE tag (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at DATETIME(6),
    last_modified_at DATETIME(6)
);

CREATE TABLE product_tag (
    product_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, tag_id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (tag_id) REFERENCES tag(id)
);