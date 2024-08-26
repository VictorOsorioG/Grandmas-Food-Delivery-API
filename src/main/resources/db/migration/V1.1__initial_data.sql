INSERT INTO customers
(id, document_number, full_name, email, phone_number, shipping_address, created_at)
VALUES (1, 'CC-1234567890', 'John Doe', 'johndoe@example.com', '1234567890', '123 Main St, Anytown, USA', now());

INSERT INTO products
(id, uuid, category, combo_name, description, price, is_available, created_at)
VALUES
(1, 'f47ac10b-58cc-4372-a567-0e02b2c3d479', 'HAMBURGERS_AND_HOTDOGS', 'CLASSIC BURGER', 'Delicious classic burger with lettuce, tomato, and cheese', 9.99, true, now());

INSERT INTO orders
(id, uuid, customer_id, order_date, subtotal, iva, total, is_delivered, delivered_at)
VALUES
(1, 'f47ac10b-58cc-4372-a567-0e02b2c3d481', 1, '2021-01-03 11:30:00', 19.98, 3.99, 23.97, true, '2021-01-03 12:00:00');

INSERT INTO order_items
(id, order_id, product_id, quantity, additional_info, price)
VALUES
(1, 1, 1, 2, 'No onions', 19.98);