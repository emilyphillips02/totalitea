CREATE SCHEMA IF NOT EXISTS totalitea;
SET search_path TO totalitea;

-- Insert dummy users
INSERT INTO users (name, email, password_hash, role)
VALUES
    ('Alice Brown', 'alice@example.com', 'hashed_password1', 'CUSTOMER'),
    ('Bob Smith', 'bob@example.com', 'hashed_password2', 'CUSTOMER'),
    ('Admin User', 'admin@example.com', 'hashed_password3', 'ADMIN');

-- Insert dummy tea products
INSERT INTO products (supplier, name, type, weight_grams, price_gbp, caffeine_content_mg_per_g, category)
VALUES
    ('Twinings', 'Earl Grey', 'Earl Grey', 250, 5.50, 2.5, 'TEA'),
    ('Whittard of Chelsea', 'Chamomile', 'Herbal', 200, 4.50, NULL, 'TEA'),
    ('Twinings', 'Darjeeling', 'Darjeeling', 250, 6.00, 2.8, 'TEA');

-- Insert dummy coffee products
INSERT INTO products (supplier, name, type, weight_grams, price_gbp, caffeine_content_mg_per_g, category)
VALUES
    ('Jacobs', 'Espresso Roast', 'Espresso', 500, 8.00, 12.0, 'COFFEE'),
    ('Lavazza', 'Cappuccino Blend', 'Cappuccino', 500, 9.50, 10.0, 'COFFEE'),
    ('Starbucks', 'House Blend', 'Filter', 500, 8.50, 9.5, 'COFFEE');

-- Insert dummy shipping rules
INSERT INTO shipping_rules (min_weight, max_weight, cost, free_shipping_threshold)
VALUES
    (0, 5, 1.50, 100.00),
    (6, 10, 10.00, 100.00),
    (11, NULL, 15.00, 100.00);

-- Insert dummy order
INSERT INTO orders (user_id, total_price, shipping_cost, status)
VALUES
    (1, 13.50, 1.50, 'PENDING');

-- Insert dummy order items
INSERT INTO order_items (order_id, product_id, quantity, sub_total)
VALUES
    (1, 1, 2, 11.00),
    (1, 2, 1, 4.50);
