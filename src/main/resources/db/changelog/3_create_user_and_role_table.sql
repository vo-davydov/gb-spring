CREATE TABLE IF NOT EXISTS sys_user
(
    id        SERIAL PRIMARY KEY,
    user_name VARCHAR(128) UNIQUE,
    email     VARCHAR(128) UNIQUE,
    password  VARCHAR(128)
    );

CREATE TABLE IF NOT EXISTS sys_role
(
    id      SERIAL PRIMARY KEY,
    role    varchar(128),
    user_id INT NOT NULL,
    CONSTRAINT user_fk
    FOREIGN KEY (user_id) REFERENCES sys_user (id)
    );

