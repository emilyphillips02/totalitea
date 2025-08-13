-- minimal fix for Hibernate validation error:
-- convert users.id and orders.user_id from INT to BIGINT.
-- only touch users.id and orders.user_id

-- make orders.user_id BIGINT and drop FK temporarily
ALTER TABLE orders DROP CONSTRAINT IF EXISTS orders_user_id_fkey;

-- change orders.user_id type to BIGINT
ALTER TABLE orders
  ALTER COLUMN user_id TYPE BIGINT
  USING user_id::BIGINT;

-- switch users.id to BIGINT while keeping the existing sequence
ALTER TABLE users
  ALTER COLUMN id DROP DEFAULT,
  ALTER COLUMN id TYPE BIGINT USING id::BIGINT,
  ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);

-- recreate the FK
ALTER TABLE orders
  ADD CONSTRAINT fk_orders_user
  FOREIGN KEY (user_id) REFERENCES users(id);
