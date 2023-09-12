-- Creating the database
-- CREATE DATABASE superprice;
-- USE superprice;

-- ADDRESS table
CREATE TABLE IF NOT EXISTS ADDRESS (
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    address_line1 TEXT NOT NULL,
    address_line2 TEXT,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    country VARCHAR(255) NOT NULL,
    address_type ENUM('USER', 'STORE', 'ORDER') NOT NULL
);

-- PRODUCT table
CREATE TABLE IF NOT EXISTS PRODUCT (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    description TEXT,
    allergens TEXT,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PRODUCT_IMAGE table
CREATE TABLE IF NOT EXISTS PRODUCT_IMAGE (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    image_url VARCHAR(255) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id)
);

-- CATEGORY table
CREATE TABLE IF NOT EXISTS CATEGORY (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL UNIQUE
);

-- STORE table
CREATE TABLE IF NOT EXISTS STORE (
    store_id INT AUTO_INCREMENT PRIMARY KEY,
    store_name VARCHAR(255) NOT NULL,
    address_id INT,
    contact_details VARCHAR(255),
    FOREIGN KEY (address_id) REFERENCES ADDRESS(address_id)
);

-- APP_USER table
CREATE TABLE IF NOT EXISTS APP_USER (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    address_id INT,
    FOREIGN KEY (address_id) REFERENCES ADDRESS(address_id)
);

-- REVIEW table
CREATE TABLE IF NOT EXISTS REVIEW (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    product_id INT,
    rating INT NOT NULL,
    comment TEXT,
    FOREIGN KEY (user_id) REFERENCES APP_USER(user_id),
    FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id)
);

-- TRANSACTION table
CREATE TABLE IF NOT EXISTS TRANSACTION (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    order_address_id INT,
    FOREIGN KEY (user_id) REFERENCES APP_USER(user_id),
    FOREIGN KEY (order_address_id) REFERENCES ADDRESS(address_id)
);

-- TRANSACTION_ITEM table
CREATE TABLE IF NOT EXISTS TRANSACTION_ITEM (
    transaction_item_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id INT,
    product_id INT,
    quantity INT NOT NULL,
    sub_total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (transaction_id) REFERENCES TRANSACTION(transaction_id),
    FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id)
);

-- NOTIFICATION table
CREATE TABLE IF NOT EXISTS NOTIFICATION (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    message TEXT NOT NULL,
    type ENUM('INFO', 'WARNING', 'ERROR'),
    date_sent TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES APP_USER(user_id)
);

-- CART_ITEM table
CREATE TABLE IF NOT EXISTS CART_ITEM (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    product_id INT,
    quantity INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES APP_USER(user_id),
    FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id)
);

-- PRODUCT_CATEGORY association table
CREATE TABLE IF NOT EXISTS PRODUCT_CATEGORY (
    product_category_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    category_id INT,
    FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id),
    FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id)
);

-- PRODUCT_DETAILS association table
CREATE TABLE IF NOT EXISTS PRODUCT_DETAILS (
    product_details_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    store_id INT,
    price DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(3, 2) NOT NULL,
    available INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id),
    FOREIGN KEY (store_id) REFERENCES STORE(store_id),
    CHECK (discount>=0.00 AND discount<=1.00)
);
