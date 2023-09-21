-- Populating address table
INSERT INTO address (address_line1, address_line2, city, state, postal_code, country, address_type)
VALUES ('123 Main St', 'Apt 4B', 'Melbourne', 'VIC', '3000', 'Australia', 'STORE'),
       ('456 Elm St', 'Suite 12A', 'Sydney', 'NSW', '2000', 'Australia', 'USER'),
       ('789 Maple St', 'Floor 3', 'Brisbane', 'QLD', '4000', 'Australia', 'ORDER'),
       ('101 Pine St', NULL, 'Adelaide', 'SA', '5000', 'Australia', 'STORE'),
       ('202 Oak St', 'Apt 2C', 'Perth', 'WA', '6000', 'Australia', 'USER');

-- Populating category table
INSERT INTO category (category_name)
VALUES ('Dairy'),
       ('Beverages'),
       ('Fruits'),
       ('Vegetables'),
       ('Bakery');

-- Populating sub_category association table
INSERT INTO sub_category (sub_category_name, category_id)
VALUES ('Almond Milk', 1),
       ('Oat Milk', 1),
       ('Soy Milk', 1),
       ('Full-Cream Milk', 1),
       ('Lite Milk', 1),
       ('Sugar Juice', 2),
       ('No Sugar Juice', 2),
       ('Packaged Fruits', 3),
       ('Non-Packaged Fruits', 3),
       ('Fresh Baker', 5),
       ('Packaged Baker', 5);

-- Populating store table
INSERT INTO store (store_name, address_id)
VALUES ('Coles', 1),
       ('Woolworths', 4),
       ('Aldi', 1),
       ('IGA', 4),
       ('Foodland', 4);

-- Populating product table
INSERT INTO product (product_name, description, allergens, sub_category_id, category_id)
VALUES ('Skim Milk', 'Low fat milk', 'Lactose', 5, 1),
       ('Full Cream Milk', 'Rich and creamy milk', 'Lactose', 4, 1),
       ('Orange Juice', 'Freshly squeezed orange juice', NULL, 6, 2),
       ('Apples', 'Crisp and sweet', NULL, 9, 3),
       ('Bread', 'Freshly baked', 'Gluten', 10, 5);

-- Populating product_image table
INSERT INTO product_image (product_id, image_url)
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

-- Populating app_user table
INSERT INTO app_user (username, password, email, first_name, last_name, phone, address_id)
VALUES ('john_doe', 'password123', 'john.doe@example.com', 'John', 'Doe', '+1234567890', 2),
       ('jane_smith', 'securepass456', 'jane.smith@example.com', 'Jane', 'Smith', '+0987654321', 5);

-- Populating review table
INSERT INTO review (user_id, product_id, rating, comment)
VALUES (1, 1, 5, 'Great milk!'),
       (1, 2, 4, 'Tastes good.'),
       (2, 3, 5, 'Refreshing!'),
       (2, 4, 4, 'Crisp apples.'),
       (2, 1, 5, 'Really tasty milk!');

-- Populating orders table
INSERT INTO orders (user_id,order_status,total_amount,order_address_id,payment_method)
VALUES (1,'PENDING',7.00,3,'CREDIT_CARD'),
       (2,'SHIPPED',6.00,3,'CREDIT_CARD');


-- Populating orders_item table
INSERT INTO orders_item (order_id, product_id, quantity, sub_total)
VALUES (1, 1, 2, 3.00),
       (1, 2, 2, 4.00),
       (2, 3, 1, 3.00),
       (2, 4, 3, 1.50);

-- Populating notification table
INSERT INTO notification (user_id, message, type)
VALUES (1, 'Your order has been shipped!', 'INFO'),
       (2, 'Your order is on its way!', 'INFO');

-- Populating cart_item table
INSERT INTO cart_item (user_id, product_id, quantity)
VALUES (1, 1, 2),
       (1, 2, 1),
       (2, 3, 1),
       (2, 4, 3),
       (2, 5, 1);

-- Populating product_details association table
INSERT INTO product_details (product_id, store_id, original_price, discount, price, available)
VALUES (1, 1, 1.50, 30, 1.05, 10),
       (1, 2, 1.60, 45, 0.88, 5),
       (1, 3, 1.55, 20, 1.24, 8),
       (2, 1, 2.00, 65, 0.7, 2),
       (2, 2, 2.10, 0, 2.10, 4),
       (2, 3, 2.05, 0, 2.05, 8),
       (3, 1, 3.10, 0, 3.10, 3),
       (3, 2, 3.20, 0, 3.20, 6),
       (4, 1, 0.55, 90, 0.055, 8),
       (4, 2, 0.60, 85, 0.09, 4);
