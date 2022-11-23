DROP TABLE products IF EXISTS ;
CREATE TABLE IF NOT EXISTS products (id bigserial, name VARCHAR(255), price integer , PRIMARY KEY (id));
INSERT INTO products (name,price) VALUES ('Apple',99),('Sony',100),('JBL',101);

