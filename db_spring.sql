CREATE DATABASE spring_mvc_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE spring_mvc_db;

CREATE TABLE product (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  price DOUBLE
);

INSERT INTO product(name, price) VALUES
('Màn hình Samsung 24 inch', 3500000),
('Tai nghe Sony', 1200000),
('Loa Bluetooth JBL', 900000),
('USB Kingston 64GB', 250000),
('Ổ cứng SSD 1TB', 2200000),
('Laptop HP Pavilion', 14000000),
('Laptop MacBook Air', 32000000),
('Chuột không dây Microsoft', 450000),
('Bàn phím Logitech K120', 400000);

