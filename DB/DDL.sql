CREATE TYPE ROLE AS ENUM ('ADMIN', 'USER');

CREATE TABLE _user
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     ROLE         NOT NULL
);

CREATE TYPE TokenType AS ENUM ('BEARER');

CREATE TABLE token
(
    id         SERIAL PRIMARY KEY,
    token      TEXT UNIQUE,
    token_type TokenType NOT NULL,
    revoked    BOOLEAN DEFAULT false,
    expired    BOOLEAN DEFAULT false,
    user_id    INTEGER REFERENCES _user (id)
);

CREATE TABLE vote_items
(
    item_id   SERIAL PRIMARY KEY,
    item_name TEXT NOT NULL
);

CREATE TABLE vote_record
(
    id           SERIAL PRIMARY KEY,
    user_id      INTEGER,
    username     VARCHAR(255),
    vote_item_id INTEGER
);
