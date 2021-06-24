CREATE TABLE book(
    id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    gender VARCHAR(50) NOT NULL
);