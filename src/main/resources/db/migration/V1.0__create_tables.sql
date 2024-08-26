CREATE TABLE customers (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  document_number varchar(20) UNIQUE NOT NULL,
  full_name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  phone_number varchar(10) NOT NULL,
  shipping_address varchar(500) NOT NULL,
  created_at timestamp NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE products (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  uuid UUID NOT NULL,
  category enum('HAMBURGERS_AND_HOTDOGS','CHICKEN,FISH','MEATS','DESSERTS','VEGAN_FOOD','KIDS_MEALS') NOT NULL,
  combo_name varchar(255) UNIQUE COMMENT 'Stored in uppercase' NOT NULL,
  description varchar(511) NOT NULL,
  price decimal(10,2) NOT NULL,
  is_available boolean NOT NULL,
  created_at timestamp NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE orders (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  uuid UUID NOT NULL,
  customer_id bigint(20) NOT NULL,
  order_date timestamp NOT NULL,
  subtotal decimal(10,2) NOT NULL,
  iva decimal(10,2) NOT NULL,
  total decimal(10,2) NOT NULL,
  is_delivered boolean NOT NULL DEFAULT FALSE,
  delivered_at timestamp DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE order_items (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  order_id bigint(20) NOT NULL,
  product_id bigint(20) NOT NULL,
  quantity int NOT NULL,
  additional_info varchar(511) NOT NULL,
  price decimal(10,2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders (id),
  FOREIGN KEY (product_id) REFERENCES products (id)
);

-- REVERT
-- DROP TABLE IF EXISTS customers;
-- DROP TABLE IF EXISTS products;
-- DROP TABLE IF EXISTS orders;
-- DROP TABLE IF EXISTS order_items;
-- DELETE FROM flyway_schema_history WHERE version = '1.0';