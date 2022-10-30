CREATE TABLE request_data
(
    id           SERIAL PRIMARY KEY,
    url  TEXT NOT NULL,
    method_type  VARCHAR(6) NOT NULL,
    created_time TIMESTAMP
);
