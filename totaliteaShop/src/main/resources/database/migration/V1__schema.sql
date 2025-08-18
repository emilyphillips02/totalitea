-- Create schema if it doesn't exist
CREATE SCHEMA IF NOT EXISTS totalitea;

-- Users table
CREATE TABLE totalitea.users (
                                 id BIGSERIAL PRIMARY KEY,
                                 name VARCHAR(100) NOT NULL,
                                 email VARCHAR(150) UNIQUE NOT NULL,
                                 password_hash VARCHAR(255) NOT NULL,
                                 role VARCHAR(20) DEFAULT 'CUSTOMER',
                                 date_registered TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Products table
CREATE TABLE totalitea.products (
                                    id BIGSERIAL PRIMARY KEY,
                                    name VARCHAR(50) NOT NULL,
                                    supplier VARCHAR(100) NOT NULL,
                                    type VARCHAR(50) NOT NULL,
                                    weight_grams DECIMAL(6,2) NOT NULL,
                                    price_gbp DECIMAL(10,2) NOT NULL,
                                    caffeine_content_mg_per_g DECIMAL(5,2),
                                    category VARCHAR(20) NOT NULL CHECK (category IN ('TEA', 'COFFEE')),
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Orders table
CREATE TABLE totalitea.orders (
                                  id BIGSERIAL PRIMARY KEY,
                                  user_id BIGINT REFERENCES totalitea.users(id) ON DELETE CASCADE,
                                  total_price DECIMAL(10,2) NOT NULL,
                                  shipping_cost DECIMAL(10,2) NOT NULL,
                                  status VARCHAR(20) DEFAULT 'PENDING',
                                  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Order items table
CREATE TABLE totalitea.order_items (
                                       id BIGSERIAL PRIMARY KEY,
                                       order_id BIGINT REFERENCES totalitea.orders(id) ON DELETE CASCADE,
                                       product_id BIGINT REFERENCES totalitea.products(id) ON DELETE CASCADE,
                                       quantity INT NOT NULL,
                                       sub_total DECIMAL(10,2) NOT NULL
);

-- Shipping rules table
CREATE TABLE totalitea.shipping_rules (
                                          id BIGSERIAL PRIMARY KEY,
                                          min_weight DECIMAL(6,2) NOT NULL,
                                          max_weight DECIMAL(6,2),
                                          cost DECIMAL(10,2) NOT NULL,
                                          free_shipping_threshold DECIMAL(10,2)
);

-- Indexes
CREATE INDEX idx_products_name_supplier_type
    ON totalitea.products (name, supplier, type);

CREATE INDEX idx_orders_user
    ON totalitea.orders (user_id);

CREATE INDEX idx_order_items_order
    ON totalitea.order_items (order_id);