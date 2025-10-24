CREATE DATABASE bt ;
USE bt;


CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    unit VARCHAR(50),
    price DECIMAL(12,2),
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO category (name) VALUES
('Rau'),
('Củ'),
('Quả'),
('Hoa');

INSERT INTO product (code, name, unit, price, category_id) VALUES
('MHH001', 'Rau muống', 'Bó', 12000, 1),
('MHH002', 'Rau cải ngọt', 'Bó', 15000, 1),
('MHH003', 'Cà rốt', 'Kg', 18000, 2),
('MHH004', 'Khoai tây', 'Kg', 25000, 2),
('MHH005', 'Cà chua', 'Kg', 22000, 3),
('MHH006', 'Dưa leo', 'Kg', 20000, 3),
('MHH007', 'Hoa hồng đỏ', 'Bó', 50000, 4),
('MHH008', 'Hoa cúc vàng', 'Bó', 40000, 4);




SELECT p.id, p.code, p.name, p.unit, p.price, c.id as categoryId, c.name as categoryName
                FROM product p JOIN category c ON p.category_id = c.id