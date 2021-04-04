CREATE USER wsadmin WITH PASSWORD 'wspass';
CREATE DATABASE wslab;
GRANT ALL PRIVILEGES ON DATABASE wslab TO wsadmin;

\c wslab;

CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    manufacturer VARCHAR(256) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    description VARCHAR(256)
);

INSERT INTO products VALUES
    (DEFAULT, 'IPhone X', 'Apple', 999.99, 'Designed in California'),
    (DEFAULT, 'Huawei P30 Pro', 'Huawei', 666.66, 'Designed in Beijing'),
    (DEFAULT, 'Vodka', 'Mother Russia', 2.99, NULL),
    (DEFAULT, 'Matreshka', 'USSSR', 0.99, NULL),
    (DEFAULT, 'Book of Dead', 'Egypt', 666.13, 'Hail to Satan');

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO wsadmin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO wsadmin;