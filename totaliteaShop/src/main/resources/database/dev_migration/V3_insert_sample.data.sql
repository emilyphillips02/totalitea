
INSERT INTO users (name, email, password_hash, role)
VALUES
    ('Charlie Davis', 'charlie@example.com', '$2a$10$abc123charliehash', 'CUSTOMER'),
    ('Dana Lee', 'dana@example.com', '$2a$10$abc123danahash', 'CUSTOMER'),
    ('Evan Wright', 'evan@example.com', '$2a$10$abc123evanhash', 'ADMIN');


INSERT INTO products (supplier, name, type, weight_grams, price_gbp, caffeine_content_mg_per_g, category)
VALUES
    ('PG Tips', 'English Breakfast', 'Black', 300, 5.75, 3.0, 'TEA'),
    ('Clipper', 'Lemon & Ginger', 'Herbal', 200, 4.25, NULL, 'TEA');


INSERT INTO products (supplier, name, type, weight_grams, price_gbp, caffeine_content_mg_per_g, category)
VALUES
    ('Nescafe', 'Gold Blend', 'Instant', 400, 7.50, 8.0, 'COFFEE'),
    ('Costa', 'Mocha Italia', 'Ground', 500, 9.00, 10.5, 'COFFEE');


INSERT INTO orders (user_id, total_price, shipping_cost, status)
VALUES
    (2, 14.25, 1.50, 'SHIPPED'),
    (3, 16.50, 1.50, 'PENDING');


INSERT INTO order_items (order_id, product_id, quantity, sub_total)
VALUES
    (2, 4, 1, 8.00),
    (2, 5, 1, 6.25),
    (3, 6, 1, 9.00),
    (3, 7, 1, 7.50);
