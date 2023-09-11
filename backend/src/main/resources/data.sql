-- Populating ADDRESS table
INSERT INTO ADDRESS (address_line1, address_line2, city, state, postal_code, country, address_type)
VALUES ('123 Main St', 'Apt 4B', 'Melbourne', 'VIC', '3000', 'Australia', 'STORE'),
       ('456 Elm St', 'Suite 12A', 'Sydney', 'NSW', '2000', 'Australia', 'USER'),
       ('789 Maple St', 'Floor 3', 'Brisbane', 'QLD', '4000', 'Australia', 'ORDER'),
       ('101 Pine St', NULL, 'Adelaide', 'SA', '5000', 'Australia', 'STORE'),
       ('202 Oak St', 'Apt 2C', 'Perth', 'WA', '6000', 'Australia', 'USER');

-- Populating CATEGORY table
INSERT INTO CATEGORY (category_name)
VALUES ('Dairy'),
       ('Beverages'),
       ('Fruits'),
       ('Vegetables'),
       ('Bakery');

-- Populating STORE table
INSERT INTO STORE (store_name, address_id)
VALUES ('Coles', 1),
       ('Woolworths', 4),
       ('Aldi', 1),
       ('IGA', 4),
       ('Foodland', 4);

-- Populating PRODUCT table
INSERT INTO PRODUCT (product_name, description, allergens)
VALUES ('Skim Milk', 'Low fat milk', 'Lactose'),
       ('Full Cream Milk', 'Rich and creamy milk', 'Lactose'),
       ('Orange Juice', 'Freshly squeezed orange juice', NULL),
       ('Apples', 'Crisp and sweet', NULL),
       ('Bread', 'Freshly baked', 'Gluten');

-- Populating PRODUCT_IMAGE table
INSERT INTO PRODUCT_IMAGE (product_id, image_url)
VALUES (1, 'https://source.unsplash.com/random?milk'),
       (1, 'https://source.unsplash.com/random?milk'),
       (1, 'https://source.unsplash.com/random?milk'),
       (2, 'https://source.unsplash.com/random?milk'),
       (2, 'https://source.unsplash.com/random?milk'),
       (2, 'https://source.unsplash.com/random?milk'),
       (3, 'https://source.unsplash.com/random?orange-juice'),
       (4, 'https://source.unsplash.com/random?apples'),
       (4, 'https://source.unsplash.com/random?apples'),
       (4, 'https://source.unsplash.com/random?apples'),
       (5, 'https://source.unsplash.com/random?bread'),
       (5, 'https://source.unsplash.com/random?bread');

-- Populating APP_USER table
INSERT INTO APP_USER (username, password, email, first_name, last_name, phone, address_id)
VALUES ('john_doe', 'password123', 'john.doe@example.com', 'John', 'Doe', '+1234567890', 2),
       ('jane_smith', 'securepass456', 'jane.smith@example.com', 'Jane', 'Smith', '+0987654321', 5);

-- Populating REVIEW table
INSERT INTO REVIEW (user_id, product_id, rating, comment)
VALUES (1, 1, 5, 'Great milk!'),
       (1, 2, 4, 'Tastes good.'),
       (2, 3, 5, 'Refreshing!'),
       (2, 4, 4, 'Crisp apples.'),
       (2, 1, 5, 'Really tasty milk!');

-- Populating TRANSACTION table
INSERT INTO TRANSACTION (user_id, total_amount, order_address_id)
VALUES (1, 7.00, 3),
       (2, 6.00, 3);

-- Populating TRANSACTION_ITEM table
INSERT INTO TRANSACTION_ITEM (transaction_id, product_id, quantity, sub_total)
VALUES (1, 1, 2, 3.00),
       (1, 2, 2, 4.00),
       (2, 3, 1, 3.00),
       (2, 4, 3, 1.50);

-- Populating NOTIFICATION table
INSERT INTO NOTIFICATION (user_id, message, type)
VALUES (1, 'Your order has been shipped!', 'INFO'),
       (2, 'Your order is on its way!', 'INFO');

-- Populating CART_ITEM table
INSERT INTO CART_ITEM (user_id, product_id, quantity)
VALUES (1, 1, 2),
       (1, 2, 1),
       (2, 3, 1),
       (2, 4, 3),
       (2, 5, 1);

-- Populating PRODUCT_CATEGORY association table
INSERT INTO PRODUCT_CATEGORY (product_id, category_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 3),
       (5, 5);

-- Populating PRODUCT_STORE association table
INSERT INTO PRODUCT_STORE (product_id, store_id, price, available)
VALUES (1, 1, 1.50, 10),
       (1, 2, 1.60, 5),
       (1, 3, 1.55, 8),
       (2, 1, 2.00, 2),
       (2, 2, 2.10, 4),
       (2, 3, 2.05, 8),
       (3, 1, 3.10, 3),
       (3, 2, 3.20, 6),
       (4, 1, 0.55, 8),
       (4, 2, 0.60, 4);
