
DROP DATABASE IF EXISTS product_manager;
CREATE DATABASE product_manager;
USE product_manager;

CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);
INSERT INTO category (name) VALUES
('Điện thoại'),
('Laptop'),
('Phụ kiện'),
('Gia dụng');
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    price DECIMAL(12,2) NOT NULL,
    quantity INT DEFAULT 0,
    description TEXT,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL
);

-- Thêm dữ liệu mẫu Product
INSERT INTO product (name, price, quantity, description, category_id) VALUES
('iPhone 15 Pro Max', 32990000, 10, 'Điện thoại Apple mới nhất', 1),
('Samsung Galaxy S24 Ultra', 28990000, 15, 'Flagship Samsung', 1),
('Xiaomi 13 Pro', 19990000, 12, 'Điện thoại Xiaomi cao cấp', 1),
('Google Pixel 8 Pro', 21990000, 8, 'Điện thoại Google mới nhất', 1),
('OnePlus 12', 17990000, 10, 'Điện thoại OnePlus flagship', 1),
('MacBook Air M3', 28990000, 5, 'Laptop Apple chip M3', 2),
('Dell XPS 15', 39990000, 7, 'Laptop cao cấp Dell', 2),
('HP Spectre x360', 35990000, 6, 'Laptop HP cao cấp', 2),
('Lenovo ThinkPad X1', 32990000, 4, 'Laptop doanh nhân Lenovo', 2),
('Asus ZenBook 14', 24990000, 9, 'Laptop mỏng nhẹ Asus', 2),
('Logitech MX Master 3S', 2490000, 20, 'Chuột không dây cao cấp', 3),
('Bàn phím cơ Keychron K6', 2200000, 25, 'Bàn phím cơ bluetooth', 3),
('Apple Magic Keyboard', 3500000, 10, 'Bàn phím Apple', 3),
('Razer DeathAdder V3', 1500000, 15, 'Chuột chơi game Razer', 3),
('Corsair K70 RGB', 2800000, 12, 'Bàn phím cơ chơi game', 3),
('Nồi cơm điện Sharp', 1200000, 30, 'Gia dụng tiết kiệm điện', 4),
('Máy hút bụi Xiaomi', 3500000, 18, 'Gia dụng thông minh', 4),
('Máy xay sinh tố Philips', 800000, 20, 'Gia dụng nhà bếp', 4),
('Ấm siêu tốc Electrolux', 700000, 25, 'Gia dụng tiện lợi', 4),
('Quạt điều hòa Kangaroo', 1500000, 10, 'Gia dụng mát mẻ', 4),
('iPad Pro 12.9', 24990000, 8, 'Máy tính bảng Apple', 1),
('Samsung Galaxy Tab S9', 19990000, 6, 'Máy tính bảng Samsung', 1),
('Surface Pro 9', 26990000, 5, 'Máy tính bảng Microsoft', 2),
('MacBook Pro 14 M3', 45990000, 3, 'Laptop Apple cao cấp', 2),
('Dell Inspiron 16', 31990000, 7, 'Laptop Dell phổ thông', 2),
('Logitech G Pro X', 3000000, 15, 'Bàn phím cơ gaming', 3),
('JBL Flip 7', 2000000, 18, 'Loa Bluetooth di động', 3),
('Nồi chiên không dầu Philips', 2500000, 12, 'Gia dụng tiện ích', 4),
('Robot hút bụi Ecovacs', 7200000, 8, 'Gia dụng thông minh', 4),
('Máy pha cà phê Delonghi', 5000000, 6, 'Gia dụng cao cấp', 4);




SELECT c.id AS category_id, c.name AS category_name,
       p.id AS product_id, p.name AS product_name, p.price
FROM category c
LEFT JOIN product p ON c.id = p.category_id
ORDER BY c.name;


SELECT p.id, p.name, p.price, p.quantity, p.description, c.name AS categoryName FROM product p JOIN category c ON p.category_id = c.id WHERE 1=1;

SELECT p.id, p.name, p.price, p.quantity, p.description, c.name AS categoryName FROM product p JOIN category c ON p.category_id = c.id;