-- DROP TABLE products IF EXISTS ;
CREATE TABLE IF NOT EXISTS products (id bigserial, name VARCHAR(255), price integer , diler VARCHAR(255), PRIMARY KEY (id));
INSERT INTO products (name,price) VALUES ('Apple',99),('Sony',100),('Sony',30),('Pony',40);

-- DROP TABLE users IF EXISTS ;
CREATE TABLE IF NOT EXISTS users (id serial, user_name VARCHAR(255), password VARCHAR(255), email VARCHAR(255),PRIMARY KEY (id));
INSERT INTO users (user_name,password,email) VALUES ('Jon','$2y$10$e.jabMU7fAok6Zg7j2YyWuMubakRavm.NEn9zh3DZwUlHcIF0Wdqm','jon@gmail.com'),('Billy','$2y$10$2fP.ZgWDOvUJVRZSvjt0c.JfMtqeQWWFYMxmt8wDDjObd/I9Qkt7u','billy@gmail.com'),('Kody','$2y$10$PQ8v8T4ep7sfre4MWHIruusrwXovaJ52pBEsmCLKVp4t37CDGDwAW','kody@gmail.com');

-- DROP TABLE roles IF EXISTS ;
-- CREATE TABLE IF NOT EXISTS roles (id serial, name VARCHAR(255),PRIMARY KEY (id));
-- INSERT INTO roles (name) VALUES ('ROLE_USER'),('ROLE_ADMIN'),('ROLE_SUPERADMIN');

CREATE TABLE IF NOT EXISTS authority (id serial, name VARCHAR(255),PRIMARY KEY (id));
INSERT INTO authority (name) VALUES ('AUTHORITY_WATCH'),('AUTHORITY_BUY'),('AUTHORITY_WORK_WITH_PRODUCTS'),('AUTHORITY_WORK_WITH_USERS');

-- DROP TABLE users_roles IF EXISTS ;
CREATE TABLE IF NOT EXISTS users_authority (user_id bigint,authority_id int,primary key (user_id,authority_id),foreign key (authority_id) references authority (id),foreign key (user_id) references users (id));
INSERT INTO users_authority (user_id,authority_id) VALUES (1,1),(2,2),(3,3);