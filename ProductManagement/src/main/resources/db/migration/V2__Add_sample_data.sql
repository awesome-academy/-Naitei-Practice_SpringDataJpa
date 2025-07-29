-- Dữ liệu mẫu cho bảng 'category'
INSERT INTO category(id, name, created_at, last_modified_at) VALUES
(1, 'Electronics', NOW(), NOW()),
(2, 'Books', NOW(), NOW()),
(3, 'Clothing', NOW(), NOW()),
(4, 'Home & Kitchen', NOW(), NOW());

-- Dữ liệu mẫu cho bảng 'tag'
INSERT INTO tag(id, name, created_at, last_modified_at) VALUES
(1, 'New Arrival', NOW(), NOW()),
(2, 'Bestseller', NOW(), NOW()),
(3, 'Sale', NOW(), NOW()),
(4, 'Eco-friendly', NOW(), NOW()),
(5, 'Limited Edition', NOW(), NOW());

-- Dữ liệu mẫu cho bảng 'product_details'
INSERT INTO product_details(id, description, created_at, last_modified_at) VALUES
(1, 'Powerful laptop with 16GB RAM and 512GB SSD. Latest generation CPU.', NOW(), NOW()),
(2, 'A comprehensive guide to building applications with the Spring Framework.', NOW(), NOW()),
(3, 'Comfortable 100% cotton t-shirt, available in multiple colors.', NOW(), NOW()),
(4, 'Automatic drip coffee maker with a 12-cup capacity and programmable timer.', NOW(), NOW()),
(5, 'Deep dive into Java Persistence API, covering advanced topics and performance tuning.', NOW(), NOW());

-- Dữ liệu mẫu cho bảng 'product'
-- Lưu ý: version mặc định là 0, category_id và details_id tham chiếu đến các bảng trên
INSERT INTO product(id, name, price, version, category_id, details_id, created_at, last_modified_at) VALUES
(1, 'UltraBook Pro X1', 25500000, 0, 1, 1, NOW(), NOW()),
(2, 'Spring 6 in Action', 750000, 0, 2, 2, NOW(), NOW()),
(3, 'Basic Cotton T-Shirt', 250000, 0, 3, 3, NOW(), NOW()),
(4, 'Smart Coffee Maker', 1200000, 0, 4, 4, NOW(), NOW()),
(5, 'Pro JPA 3', 890000, 0, 2, 5, NOW(), NOW());

-- Dữ liệu mẫu cho bảng quan hệ 'product_tag' (Many-to-Many)
INSERT INTO product_tag(product_id, tag_id) VALUES
(1, 1), -- UltraBook Pro X1 -> New Arrival
(1, 2), -- UltraBook Pro X1 -> Bestseller
(2, 2), -- Spring 6 in Action -> Bestseller
(3, 3), -- Basic Cotton T-Shirt -> Sale
(3, 4), -- Basic Cotton T-Shirt -> Eco-friendly
(4, 1), -- Smart Coffee Maker -> New Arrival
(5, 2), -- Pro JPA 3 -> Bestseller
(5, 5); -- Pro JPA 3 -> Limited Edition