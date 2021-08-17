CREATE TABLE IF NOT EXISTS user
(
    id       INT                NOT NULL AUTO_INCREMENT,
    email    VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100)       NOT NULL,
    PRIMARY KEY (id)
);

