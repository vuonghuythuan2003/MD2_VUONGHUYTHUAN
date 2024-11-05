CREATE DATABASE quanlybanhang;
USE quanlybanhang;

CREATE TABLE Categories
(
    category_id     int PRIMARY KEY AUTO_INCREMENT,
    category_name   varchar(50) NOT NULL UNIQUE,
    category_status Bit Default 1
);

CREATE TABLE Products
(
    product_id    int PRIMARY KEY AUTO_INCREMENT,
    product_name  varchar(20) NOT NULL UNIQUE,
    stock         Int         Not null,
    cost_price    double      NOT NULL CHECK ( cost_price > 0 ),
    selling_price double      NOT NULL CHECK ( selling_price > 0),
    created_at    Datetime,
    category_id   int         NOT NULL,
    FOREIGN KEY (category_id) references Categories (category_id)
);

DELIMITER &&
CREATE PROCEDURE Proc_FindAllCategories()
BEGIN
    SELECT * FROM Categories;
end &&
DELIMITER &&;

drop PROCEDURE Proc_FindAllCategories;
DELIMITER &&
CREATE PROCEDURE create_categories(
    IN in_categories_name varchar(50),
    IN in_categories_status BIT
)
BEGIN
    INSERT INTO Categories(category_name, category_status)
    VALUES (in_categories_name,
            in_categories_status);
end &&
DELIMITER &&;

DELIMITER &&
CREATE PROCEDURE update_categories(
    IN in_categories_id INT,
    IN in_categories_name varchar(50),
    IN in_categories_status BIT
)
BEGIN
    UPDATE Categories
    SET category_name   = in_categories_name,
        category_status = in_categories_status
    where category_id = in_categories_id;
end &&
DELIMITER &&;

DELIMITER &&
CREATE PROCEDURE find_id(
    IN id_categories int
)
begin
    select * from Categories WHERE category_id = id_categories;
end &&
DELIMITER &&;

DELIMITER &&
CREATE PROCEDURE delete_category(
    IN in_category_id INT
)
BEGIN
    UPDATE Categories
    SET category_status = 1
    WHERE category_id = in_category_id;
end &&
DELIMITER &&;

DELIMITER &&
CREATE PROCEDURE Proc_CountByCategoryes()
BEGIN
    SELECT category_id, COUNT(*) AS product_count
    From Products
    GROUP BY category_id;
end &&
DELIMITER &&;

DELIMITER &&
CREATE PROCEDURE find_all_product()
BEGIN
    SELECT * FROM Products;
end &&
DELIMITER &&;

DELIMITER &&
CREATE PROCEDURE create_product(
    IN in_product_name varchar(50),
    IN in_stock INT,
    IN in_cost_price DOUBLE,
    IN in_selling_price DOUBLE,
    IN in_created_at Datetime,
    IN in_category_id INT
)
begin
    INSERT INTO Products( product_name, stock, cost_price, selling_price, created_at
                        , category_id) VALUES (in_product_name, in_stock
                        , in_cost_price,
                        in_selling_price, in_created_at, in_category_id);
end &&
DELIMITER &&;