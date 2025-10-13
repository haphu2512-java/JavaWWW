CREATE DATABASE IF NOT EXISTS shoppingdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE shoppingdb;
CREATE TABLE `category` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `name` VARCHAR(255) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB;
CREATE TABLE `customer` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `name` VARCHAR(255) NOT NULL,
                            `customer_since` DATE DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB;
CREATE TABLE `product` (
                           `id` INT NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(255) NOT NULL,
                           `price` DECIMAL(10, 2) NOT NULL,
                           `in_stock` BOOLEAN DEFAULT NULL,
                           `category_id` INT DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB;

-- Tạo bảng 'comment' sau khi 'product' đã được tạo.
CREATE TABLE `comment` (
                           `id` INT NOT NULL AUTO_INCREMENT,
                           `text` TEXT,
                           `product_id` INT DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           CONSTRAINT `fk_comment_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB;

-- Tạo bảng 'orders' sau khi 'customer' đã được tạo.
-- Lưu ý: 'orders' thay vì 'order' vì ORDER là một từ khóa trong SQL.
CREATE TABLE `orders` (
                          `id` INT NOT NULL AUTO_INCREMENT,
                          `date` DATE NOT NULL,
                          `customer_id` INT NOT NULL,
                          PRIMARY KEY (`id`),
                          CONSTRAINT `fk_orders_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB;

-- Tạo bảng 'order_line' sau cùng, vì nó phụ thuộc vào cả 'orders' và 'product'.
CREATE TABLE `order_line` (
                              `id` INT NOT NULL AUTO_INCREMENT,
                              `amount` INT NOT NULL,
                              `purchase_price` DECIMAL(10, 2) NOT NULL,
                              `order_id` INT NOT NULL,
                              `product_id` INT NOT NULL,
                              PRIMARY KEY (`id`),
                              CONSTRAINT `fk_orderline_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
                              CONSTRAINT `fk_orderline_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB;


INSERT INTO `category` (`id`, `name`) VALUES
                                          (1, 'Điện tử & Công nghệ'),
                                          (2, 'Sách & Văn phòng phẩm'),
                                          (3, 'Thời trang & Phụ kiện');

-- 2. Chèn dữ liệu cho bảng 'customer'
INSERT INTO `customer` (`id`, `name`, `customer_since`) VALUES
                                                            (1, 'Nguyễn Văn An', '2023-01-15'),
                                                            (2, 'Trần Thị Bình', '2022-11-20'),
                                                            (3, 'Lê Minh Cường', '2024-05-10');

-- 3. Chèn dữ liệu cho bảng 'product' (sử dụng category_id từ trên)
INSERT INTO `product` (`id`, `name`, `price`, `in_stock`, `category_id`) VALUES
                                                                             (1, 'Laptop Dell XPS 15', 25000000.00, 1, 1),
                                                                             (2, 'iPhone 15 Pro Max 256GB', 30500000.00, 1, 1),
                                                                             (3, 'Sách "Nhà Giả Kim"', 85000.00, 1, 2),
                                                                             (4, 'Áo Thun Cotton Trơn', 150000.00, 1, 3),
                                                                             (5, 'Quần Jeans Levi\'s 501', 800000.00, 0, 3),
                                                                             (6, 'Bàn phím cơ Keychron K2', 2100000.00, 1, 1);

-- 4. Chèn dữ liệu cho bảng 'comment' (sử dụng product_id từ trên)
INSERT INTO `comment` (`id`, `text`, `product_id`) VALUES
                                                       (1, 'Máy dùng rất tốt, pin trâu, hiệu năng mạnh mẽ.', 1),
                                                       (2, 'Camera chụp ảnh siêu nét, rất đáng tiền!', 2),
                                                       (3, 'Một cuốn sách rất ý nghĩa và đáng đọc.', 3),
                                                       (4, 'Chất lượng bàn phím tuyệt vời, gõ rất sướng tay.', 6);

-- 5. Chèn dữ liệu cho bảng 'orders' (sử dụng customer_id từ trên)
INSERT INTO `orders` (`id`, `date`, `customer_id`) VALUES
                                                       (1, '2024-10-01', 1), -- Đơn hàng của Nguyễn Văn An
                                                       (2, '2024-10-05', 2), -- Đơn hàng của Trần Thị Bình
                                                       (3, '2024-10-11', 1); -- Một đơn hàng khác của Nguyễn Văn An

-- 6. Chèn dữ liệu cho bảng 'order_line' (chi tiết đơn hàng)
-- Liên kết 'orders' và 'product'
INSERT INTO `order_line` (`id`, `amount`, `purchase_price`, `order_id`, `product_id`) VALUES
-- Chi tiết cho Đơn hàng 1
(1, 1, 25000000.00, 1, 1),
(2, 2, 150000.00, 1, 4),

-- Chi tiết cho Đơn hàng 2
(3, 1, 85000.00, 2, 3),
(4, 5, 150000.00, 2, 4),

-- Chi tiết cho Đơn hàng 3
(5, 1, 30500000.00, 3, 2),
(6, 1, 2100000.00, 3, 6);
