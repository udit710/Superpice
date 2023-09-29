-- Creating the database
-- CREATE DATABASE superprice;
-- USE superprice;

-- address table
CREATE TABLE IF NOT EXISTS address (
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    address_line1 TEXT NOT NULL,
    address_line2 TEXT,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    country VARCHAR(255) NOT NULL,
    address_type ENUM('USER', 'STORE', 'ORDER') NOT NULL
);

-- category table
CREATE TABLE IF NOT EXISTS category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL UNIQUE
    );

-- sub_category association table
CREATE TABLE IF NOT EXISTS sub_category (
    sub_category_id INT AUTO_INCREMENT PRIMARY KEY,
--     product_id INT, ERROR: Ask if we need to add this or not
    sub_category_name VARCHAR(255) NOT NULL UNIQUE,
    category_id INT,
--     FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
    );

-- product table
CREATE TABLE IF NOT EXISTS product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    sub_category_id INT,
    category_id INT,
    description TEXT,
    allergens TEXT,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sub_category_id) REFERENCES sub_category(sub_category_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- product_image table
CREATE TABLE IF NOT EXISTS product_image (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    image_url VARCHAR(255) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- store table
CREATE TABLE IF NOT EXISTS store (
    store_id INT AUTO_INCREMENT PRIMARY KEY,
    store_name VARCHAR(255) NOT NULL,
    address_id INT,
    contact_details VARCHAR(255),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
);

-- app_user table
CREATE TABLE IF NOT EXISTS app_user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    address_id INT,
    FOREIGN KEY (address_id) REFERENCES address(address_id)
);

-- review table
CREATE TABLE IF NOT EXISTS review (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    product_id INT,
    rating INT NOT NULL,
    comment TEXT,
    FOREIGN KEY (user_id) REFERENCES app_user(user_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- orders table
CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    order_status ENUM('PENDING','SHIPPED','IN_PROGRESS','CANCELLED','DELIVERED'),
    total_amount DECIMAL(10, 2) NOT NULL,
    order_address_id INT,
    payment_method ENUM('CREDIT_CARD','DEBIT_CARD','PAYPAL','WALLET'),
    FOREIGN KEY (user_id) REFERENCES app_user(user_id),
    FOREIGN KEY (order_address_id) REFERENCES address(address_id)
);

-- orders_item table
CREATE TABLE IF NOT EXISTS orders_item (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    sub_total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- notification table
CREATE TABLE IF NOT EXISTS notification (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    message TEXT NOT NULL,
    type ENUM('OFFERS', 'ORDER', 'ERROR'),
    date_sent TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES app_user(user_id)
);

-- product_details association table
CREATE TABLE IF NOT EXISTS product_details (
    product_details_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    store_id INT,
    original_price DECIMAL(10, 2) NOT NULL,
    discount INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    available INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (store_id) REFERENCES store(store_id),
    CHECK (discount>=0 AND discount<=100)
);

-- cart_item table
CREATE TABLE IF NOT EXISTS cart_item (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    product_details_id INT,
    quantity INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES app_user(user_id),
    FOREIGN KEY (product_details_id) REFERENCES product_details(product_details_id)
);


