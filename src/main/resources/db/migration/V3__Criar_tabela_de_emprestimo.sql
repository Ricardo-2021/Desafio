
CREATE TABLE loan(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id),
    aviable boolean NOT NULL,
    dateofloan DATE NOT NULL,
    dateofreturn DATE NOT NULL,
    book_id INTEGER NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id)
);
