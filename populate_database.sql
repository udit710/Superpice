USE superprice;

-- Inserting data into PRODUCT table
INSERT INTO PRODUCT (ProductName, Description, Price, Availability, ImageURL) VALUES 
('Apple', 'Fresh red apples', 0.50, TRUE, 'apple.jpg'),
('Banana', 'Yellow ripe bananas', 0.30, TRUE, 'banana.jpg');

-- Inserting data into CATEGORY table
INSERT INTO CATEGORY (CategoryName) VALUES 
('Fruits'),
('Vegetables');

-- Inserting data into STORE table
INSERT INTO STORE (StoreName, StoreLocation, ContactDetails) VALUES 
('SuperMart', '123 Main St', '123-456-7890'),
('FreshGrocery', '456 Elm St', '987-654-3210');

-- Inserting data into USER table
INSERT INTO USER (Username, Password, Email, Address) VALUES 
('johnDoe', 'password123', 'john@example.com', '789 Oak St'),
('janeSmith', 'password456', 'jane@example.com', '101 Pine St');

-- Inserting data into REVIEW table
INSERT INTO REVIEW (UserID, ProductID, Rating, Comment) VALUES 
(1, 1, 5, 'Great apples!'),
(2, 2, 4, 'Good bananas, but not the best.');

-- Inserting data into TRANSACTION table
INSERT INTO TRANSACTION (UserID, TotalAmount) VALUES 
(1, 5.00),
(2, 3.00);

-- Inserting data into TRANSACTION_ITEM table
INSERT INTO TRANSACTION_ITEM (TransactionID, ProductID, Quantity, SubTotal) VALUES 
(1, 1, 5, 2.50),
(2, 2, 4, 1.20);

-- Inserting data into NOTIFICATION table
INSERT INTO NOTIFICATION (UserID, Message) VALUES 
(1, 'Your order has been shipped!'),
(2, 'Your order is ready for pickup!');

-- Inserting data into CART_ITEM table
INSERT INTO CART_ITEM (UserID, ProductID, Quantity) VALUES 
(1, 2, 3),
(2, 1, 2);

-- Inserting data into PRODUCT_CATEGORY association table
INSERT INTO PRODUCT_CATEGORY (ProductID, CategoryID) VALUES 
(1, 1),
(2, 1);

-- Inserting data into PRODUCT_STORE association table
INSERT INTO PRODUCT_STORE (ProductID, StoreID) VALUES 
(1, 1),
(2, 2);
