ALTER TABLE customer
    ADD COLUMN user_id INT;

ALTER TABLE sys_user
    ADD COLUMN customer_id INT;

ALTER TABLE customer
    ADD CONSTRAINT customer_fk FOREIGN KEY (user_id) REFERENCES sys_user (id);

ALTER TABLE sys_user
    ADD CONSTRAINT customer_fk FOREIGN KEY (customer_id) REFERENCES customer (id);
