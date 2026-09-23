CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    mobilenumber VARCHAR(20) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',

    PRIMARY KEY (id),
    UNIQUE KEY uk_users_email (email)
);