INSERT INTO customers
(id, document_number, full_name, email, phone_number, shipping_address, created_at)
VALUES
(2, 'CC-0987654321', 'Jane Smith', 'jane.smith@example.com', '0987654321', '456 Elm St, City, Country', now()),
(3, 'CE-0987654321', 'Alice Johnson', 'alice.johnson@example.com', '1122334455', '789 Oak St, City, Country', now());

INSERT INTO products
(id, uuid, category, combo_name, description, price, is_available, created_at)
VALUES
(2, '550e8400-e29b-41d4-a716-446655440001', 'CHICKEN,FISH', 'GRILLED_CHICKEN', 'Grilled chicken breast with a side of vegetables.', 7.99, true, now()),
(3, '550e8400-e29b-41d4-a716-446655440002', 'MEATS', 'STEAK_DINNER', 'Juicy steak with mashed potatoes and gravy.', 15.99, false, now()),
(4, '550e8400-e29b-41d4-a716-446655440003', 'DESSERTS', 'CHOCOLATE_CAKE', 'Rich chocolate cake with a layer of fudge.', 4.99, true, now()),
(5, '550e8400-e29b-41d4-a716-446655440004', 'VEGAN_FOOD', 'VEGAN_SALAD', 'A healthy salad with mixed greens, nuts, and vinaigrette.', 6.99, true, now()),
(6, '550e8400-e29b-41d4-a716-446655440005', 'KIDS_MEALS', 'KIDS_CHEESEBURGER', 'Cheeseburger with fries for kids.', 4.49, true, now());

INSERT INTO orders
(id, uuid, customer_id, order_date, subtotal, iva, total, is_delivered, delivered_at)
VALUES
(2, '660e8400-e29b-41d4-a716-446655440000', 2, '2021-01-03 11:30:00', 10.98, 1.10, 12.08, true, '2021-01-03 12:00:00'),
(3, '660e8400-e29b-41d4-a716-446655440001', 3, '2021-01-03 11:30:00', 8.99, 0.90, 9.89, false, null),
(4, '660e8400-e29b-41d4-a716-446655440002', 2, '2021-01-03 11:30:00', 4.99, 0.50, 5.49, false, null);

INSERT INTO order_items
(id, order_id, product_id, quantity, additional_info, price)
VALUES
(2, 2, 4, 1, 'Extra fudge', 4.99),
(3, 2, 2, 1, 'No salt', 7.99),
(4, 4, 4, 1, 'No extra sugar', 4.99);


-- REVERT
-- DELETE FROM customers WHERE id IN (2, 3);
-- DELETE FROM products WHERE id IN (2, 3, 4, 5, 6);
-- DELETE FROM orders WHERE id IN (2, 3, 4);
-- DELETE FROM order_items WHERE id IN (2, 3, 4);
-- DELETE FROM flyway_schema_history WHERE version = '1.2';