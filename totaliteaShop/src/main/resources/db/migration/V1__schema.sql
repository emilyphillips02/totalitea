-- Users table
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR (100) NOT NULL,
                       email VARCHAR(150) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       role VARCHAR (20) DEFAULT 'CUSTOMER',
                       date_registered TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Products table
CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR (50) NOT NULL,
                          supplier VARCHAR (100) NOT NULL,
                          type VARCHAR (50) NOT NULL, -- latte cappuccino, english breakfast tea etc
                          weight_grams DECIMAL (6,2) NOT NULL,
                          price_gbp DECIMAL (10,2) NOT NULL,
                          caffeine_content_mg_per_g DECIMAL (5,2), -- NULL for herbal tea
                          category VARCHAR (20) NOT NULL CHECK (category IN ('TEA', 'COFFEE')),
                          created_at TIMESTAMP DEFAULT  CURRENT_TIMESTAMP
);

-- Order table
CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        user_id INT REFERENCES users(id) ON DELETE CASCADE,
                        total_price DECIMAL (10,2) NOT NULL,
                        shipping_cost DECIMAL (10,2) NOT NULL,
                        status VARCHAR (20) DEFAULT 'PENDING',
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Order item table
CREATE TABLE order_items(
                            id BIGSERIAL PRIMARY KEY ,
                            order_id INT REFERENCES orders(id) ON DELETE CASCADE,
                            product_id INT REFERENCES products(id) ON DELETE CASCADE,
                            quantity INT NOT NULL,
                            sub_total DECIMAL (10,2) NOT NULL
);
-- Shipping rules
CREATE TABLE shipping_rules(
                               id BIGSERIAL PRIMARY KEY,
                               min_weight DECIMAL(6,2) NOT NULL,
                               max_weight DECIMAL(6,2),
                               cost DECIMAL (10,2) NOT NULL,
                               free_shipping_threshold DECIMAL (10,2)
);

CREATE INDEX  idx_products_name_supplier_type
    ON products (name, supplier , type);

CREATE INDEX idx_orders_user
    ON orders (user_id);

CREATE INDEX idx_order_items_order
    ON order_items (order_id)

