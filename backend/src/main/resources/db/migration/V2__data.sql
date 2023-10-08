-- Populating address table
INSERT INTO address (address_line1, address_line2, city, state, postal_code, country, address_type)
VALUES ('123 Main St', 'Apt 4B', 'Melbourne', 'VIC', '3000', 'Australia', 'STORE'),
       ('456 Elm St', 'Suite 12A', 'Sydney', 'NSW', '2000', 'Australia', 'USER'),
       ('789 Maple St', 'Floor 3', 'Brisbane', 'QLD', '4000', 'Australia', 'ORDER'),
       ('101 Pine St', NULL, 'Adelaide', 'SA', '5000', 'Australia', 'STORE'),
       ('202 Oak St', 'Apt 2C', 'Perth', 'WA', '6000', 'Australia', 'USER'),
       ('303 Birch St', 'Apt 7C', 'Hobart', 'TAS', '7000', 'Australia', 'USER'),
       ('606 Cedar St', 'Suite 15B', 'Darwin', 'NT', '0800', 'Australia', 'ORDER'),
       ('909 Redwood St', 'Floor 5', 'Canberra', 'ACT', '2600', 'Australia', 'STORE');

-- Populating category table
INSERT INTO category (category_name)
VALUES ('Dairy'),
       ('Beverages'),
       ('Fruits'),
       ('Vegetables'),
       ('Bakery'),
       ('Meat'),
       ('Seafood'),
       ('Snacks');

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
       ('Packaged Vegetables', 4),
       ('Non-Packaged Vegetables', 4),
       ('Fresh Baker', 5),
       ('Packaged Baker', 5),
       ('Chicken', 6),
       ('Beef', 6),
       ('Pork', 6),
       ('Fish', 7),
       ('Crab', 7),
       ('Lobster', 7),
       ('Chips', 8),
       ('Chocolate', 8),
       ('Candy', 8);

-- Populating store table
INSERT INTO store (store_name, address_id)
VALUES ('Coles', 1),
       ('Aldi', 1),
       ('IGA', 4),
       ('Foodland', 4),
       ('Woolworths', 4),
       ('FreshMart', 8),
       ('GreenGrocer', 8),
       ('SeaFoodShack', 8);

-- Populating product table
INSERT INTO product (product_name, description, allergens, sub_category_id, category_id)
VALUES ('Skim Milk', 'Low fat milk', 'Lactose', 5, 1),
       ('Full Cream Milk', 'Rich and creamy milk', 'Lactose', 4, 1),
       ('Orange Juice', 'Freshly squeezed orange juice', NULL, 6, 2),
       ('Apples', 'Crisp and sweet', NULL, 9, 3),
       ('Bread', 'Freshly baked', 'Gluten', 10, 5),
       ('Chicken Breast', 'Fresh chicken breast', NULL, 14, 6),
       ('Beef Steak', 'Juicy steak cuts', NULL, 15, 6),
       ('Pork Chops', 'Tender pork chops', NULL, 16, 6),
       ('Salmon Fillet', 'Fresh salmon fillets', NULL, 17, 7),
       ('Crab Legs', 'Fresh crab legs', NULL, 18, 7),
       ('Lobster Tail', 'Fresh lobster tails', NULL, 19, 7),
       ('Potato Chips', 'Crunchy and salty', NULL, 20, 8),
       ('Milk Chocolate', 'Creamy milk chocolate', 'Lactose', 21, 8),
       ('Gummy Bears', 'Sweet gummy candy', NULL, 22, 8);


-- Populating product_image table
INSERT INTO product_image (product_id, image_url)
VALUES (1, 'https://upload.wikimedia.org/wikipedia/commons/a/a5/Glass_of_Milk_%2833657535532%29.jpg'),
       (1, 'https://images.unsplash.com/photo-1550583724-b2692b85b150'),
       (1, 'https://images.unsplash.com/photo-1620189507195-68309c04c4d0'),
       (2, 'https://images.unsplash.com/photo-1612117568851-c6f49f8f0e87'),
       (2, 'https://images.unsplash.com/photo-1549203477-cba29f1a7c8f'),
       (2, 'https://images.unsplash.com/photo-1609246280917-339404083c49'),
       (3, 'https://www.alphafoodie.com/wp-content/uploads/2020/11/Orange-Juice-2-of-2.jpeg'),
       (4, 'https://hips.hearstapps.com/hmg-prod/images/red-fresh-apple-isolated-on-white-background-royalty-free-image-1627314996.jpg?crop=1.00xw:0.923xh;0,0.0486xh&resize=980:*'),
       (5, 'https://images.unsplash.com/photo-1612539466296-4ecf1db303e3'),
       (5, 'https://images.unsplash.com/photo-1559811814-e2c57b5e69df'),
       (6, 'https://www.vicsmeat.com.au/cdn/shop/products/chicken-breast-fillet-la-ionica-550-g-943125.jpg?v=1689744963'),
       (7, 'https://images.unsplash.com/photo-1613062136969-ca6f0e490cd8'),
       (8, 'https://images.unsplash.com/photo-1592409713878-33e2015e92b4'),
       (9, 'https://www.bigsams.in/wp-content/uploads/2023/01/Fresh-Norwegian-Salmon-Fillets-Skin-on-1.jpg'),
       (10, 'https://upload.wikimedia.org/wikipedia/commons/0/0a/Kepiting_Saus_Tiram_2.JPG'),
       (11, 'https://images.unsplash.com/photo-1592972161896-0328cddd71a8'),
       (12, 'https://upload.wikimedia.org/wikipedia/commons/6/69/Potato-Chips.jpg'),
       (13, 'https://images.unsplash.com/photo-1602540738621-e7dfebf0af79'),
       (14, 'https://upload.wikimedia.org/wikipedia/commons/a/a6/Oursons_g%C3%A9latine_march%C3%A9_Rouffignac.jpg');

-- Populating app_user table
INSERT INTO app_user (username, password, email, first_name, last_name, phone, address_id)
VALUES ('john_doe', 'password123', 'john.doe@example.com', 'John', 'Doe', '+1234567890', 2),
       ('jane_smith', 'securepass456', 'jane.smith@example.com', 'Jane', 'Smith', '+0987654321', 5),
       ('alice_wonder', 'wonderland789', 'alice.wonder@example.com', 'Alice', 'Wonder', '+1122334455', 6),
       ('bob_builder', 'buildit456', 'bob.builder@example.com', 'Bob', 'Builder', '+5566778899', 7),
       ('charlie_choco', 'chocolate321', 'charlie.choco@example.com', 'Charlie', 'Choco', '+4455667788', 8);

-- Populating review table
INSERT INTO review (user_id, product_id, rating, comment)
VALUES (1, 1, 5, 'Great milk!'),
       (1, 2, 4, 'Tastes good.'),
       (2, 3, 5, 'Refreshing!'),
       (2, 4, 4, 'Crisp apples.'),
       (2, 1, 5, 'Really tasty milk!'),
       (3, 6, 4, 'Tender and juicy!'),
       (3, 7, 5, 'Very fresh.'),
       (4, 8, 3, 'Could be crunchier.'),
       (4, 9, 5, 'Delicious salmon!'),
       (5, 10, 4, 'Love the crab legs.'),
       (5, 11, 5, 'Fresh lobster tails.'),
       (5, 12, 5, 'Crunchy and salty.'),
       (5, 13, 4, 'Creamy and sweet.'),
       (5, 14, 5, 'Yummy gummy bears!');

-- Populating orders table
INSERT INTO orders (user_id,order_status,total_amount,order_address_id,payment_method)
VALUES (1,'PENDING',7.00,3,'CREDIT_CARD'),
       (2,'SHIPPED',6.00,3,'CREDIT_CARD'),
       (3, 'SHIPPED', 20.00, 6, 'DEBIT_CARD'),
       (4, 'PENDING', 25.00, 7, 'PAYPAL'),
       (5, 'IN_PROGRESS', 30.00, 8, 'CREDIT_CARD');


-- Populating orders_item table
INSERT INTO orders_item (order_id, product_id, quantity, sub_total)
VALUES (1, 1, 2, 3.00),
       (1, 2, 2, 4.00),
       (2, 3, 1, 3.00),
       (2, 4, 3, 1.50),
       (4, 6, 1, 7.50),
       (4, 7, 1, 7.50),
       (5, 8, 2, 5.00),
       (5, 9, 1, 10.00);

-- Populating notification table
INSERT INTO notification (user_id, message, type)
VALUES (1, 'Your order has been shipped!', 'ORDER'),
       (2, 'Your order is on its way!', 'ORDER'),
       (3, 'Your order is out for delivery!', 'ORDER'),
       (4, 'Your order has been received and is being processed.', 'ORDER'),
       (5, 'Your order is being prepared.', 'ORDER');


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
       (5, 1, 2.50, 10, 2.25, 15),
       (5, 2, 2.60, 5, 2.47, 10),
       (6, 1, 5.00, 0, 5.00, 20),
       (6, 3, 5.10, 0, 5.10, 18),
       (7, 1, 6.50, 15, 5.53, 12),
       (7, 2, 6.75, 0, 6.75, 14),
       (8, 1, 4.00, 20, 3.20, 25),
       (8, 3, 4.10, 0, 4.10, 22),
       (9, 1, 8.00, 10, 7.20, 10),
       (9, 2, 8.25, 0, 8.25, 8),
       (10, 1, 12.00, 5, 11.40, 5),
       (10, 3, 12.50, 0, 12.50, 6),
       (11, 1, 15.00, 10, 13.50, 3),
       (11, 2, 15.50, 0, 15.50, 4),
       (12, 1, 1.00, 20, 0.80, 30),
       (12, 3, 1.10, 0, 1.10, 28),
       (13, 1, 2.50, 15, 2.13, 20),
       (13, 2, 2.60, 0, 2.60, 18),
       (14, 1, 1.50, 10, 1.35, 25),
       (14, 3, 1.55, 0, 1.55, 23);

-- Populating cart_item table
INSERT INTO cart_item (user_id, product_details_id, product_id, quantity)
VALUES  (1, 1, 1, 2);
--         (1, 2, 1, 2),
--         (2, 3, 1, 1),
--         (2, 4, 1, 3),
--         (3, 6, 1, 1),
--         (3, 7, 1, 1),
--         (4, 8, 1, 2),
--         (4, 9, 1, 1),
--         (5, 10,1, 1),
--         (5, 11,1, 1),
--         (5, 12,1, 1),
--         (5, 13,1, 1),
--         (5, 14,1, 1);
