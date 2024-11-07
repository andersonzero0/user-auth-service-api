CREATE TABLE users_tb (
    id VARCHAR PRIMARY KEY NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    username VARCHAR NOT NULL UNIQUE,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    avatar_url VARCHAR,
    password VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);