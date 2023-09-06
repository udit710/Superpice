USE superprice;

-- Populating ADDRESS table
INSERT INTO ADDRESS (address_line1, city, state, postal_code, country, address_type)
VALUES ('123 Main St', 'Melbourne', 'VIC', '3000', 'Australia', 'STORE'),
       ('456 Elm St', 'Sydney', 'NSW', '2000', 'Australia', 'USER'),
       ('789 Maple St', 'Brisbane', 'QLD', '4000', 'Australia', 'ORDER');

-- Populating PRODUCT table
INSERT INTO PRODUCT (product_name, description, price, availability, allergens, last_updated)
VALUES ('Skim Milk', 'Low fat milk', 1.50, TRUE, 'Lactose', CURRENT_TIMESTAMP),
       ('Full Cream Milk', 'Rich and creamy milk', 2.00, TRUE, 'Lactose', CURRENT_TIMESTAMP);

-- Populating PRODUCT_IMAGE table
INSERT INTO PRODUCT_IMAGE (product_id, image_url)
VALUES (1, 'https://source.unsplash.com/random?milk'),
       (2, 'https://source.unsplash.com/random?milk');

-- Populating CATEGORY table
INSERT INTO CATEGORY (category_name)
VALUES ('Dairy'),
       ('Beverages');

-- Populating STORE table
INSERT INTO STORE (store_name, address_id)
VALUES ('Coles', 1),
       ('Woolworths', 1),
       ('Aldi', 1);

-- Populating USER table
INSERT INTO USER (username, password, email, address_id)
VALUES ('john_doe', 'password123', 'john.doe@example.com', 2);

-- Populating REVIEW table
INSERT INTO REVIEW (user_id, product_id, rating, comment)
VALUES (1, 1, 5, 'Great milk!'),
       (1, 2, 4, 'Tastes good.');

-- Populating TRANSACTION table
INSERT INTO TRANSACTION (user_id, total_amount, order_address_id)
VALUES (1, 3.50, 3);

-- Populating TRANSACTION_ITEM table
INSERT INTO TRANSACTION_ITEM (transaction_id, product_id, quantity, sub_total)
VALUES (1, 1, 2, 3.00),
       (1, 2, 1, 2.00);

-- Populating NOTIFICATION table
INSERT INTO NOTIFICATION (user_id, message, type, date_sent)
VALUES (1, 'Your order has been shipped!', 'INFO', CURRENT_TIMESTAMP);

-- Populating CART_ITEM table
INSERT INTO CART_ITEM (user_id, product_id, quantity)
VALUES (1, 1, 2),
       (1, 2, 1);

-- Populating PRODUCT_CATEGORY association table
INSERT INTO PRODUCT_CATEGORY (product_id, category_id)
VALUES (1, 1),
       (2, 1);

-- Populating PRODUCT_STORE association table
INSERT INTO PRODUCT_STORE (product_id, store_id, price)
VALUES (1, 1, 1.50),
       (1, 2, 1.55),
       (1, 3, 1.45),
       (2, 1, 2.00),
       (2, 2, 2.05),
       (2, 3, 1.95);
