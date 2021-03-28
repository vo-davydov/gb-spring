CREATE TABLE IF NOT EXISTS product
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR(128),
    price NUMERIC
);

CREATE TABLE IF NOT EXISTS customer
(
    id   SERIAL PRIMARY KEY,
    name varchar(128)
);

CREATE TABLE IF NOT EXISTS customer_product
(
    customer_id INT NOT NULL,
    product_id  INT NOT NULL,
    PRIMARY KEY (customer_id, product_id),
    CONSTRAINT customer_fk
        FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT product_fk
        FOREIGN KEY (product_id) REFERENCES product (id)
);
