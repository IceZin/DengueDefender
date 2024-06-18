CREATE TABLE users (
    id serial PRIMARY KEY,
    document VARCHAR(20) NOT NULL,
    password VARCHAR(32) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'viewer'
);

CREATE TABLE import_data (
    id serial PRIMARY KEY,
    drs VARCHAR(100) NOT NULL,
    gve VARCHAR(100) NOT NULL,
    rs VARCHAR(100) NOT NULL,
    mun VARCHAR(100) NOT NULL,
    s1 int,
    s2 int,
    s3 int,
    s4 int,
    s5 int,
    s6 int,
    s7 int,
    s8 int,
    s9 int,
    s10 int,
    s11 int,
    s12 int,
    s13 int,
    total bigint
);

CREATE TABLE reports (
    id serial PRIMARY KEY,
    type INT,
    city VARCHAR(40) NOT NULL,
    neighborhood VARCHAR(40) NOT NULL,
    address VARCHAR(60),
    address_number INT,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    user_id INT NOT NULL references users(id),
    date DATE NOT NULL
);

INSERT INTO users (document, password, role) VALUES ('12345678900', 'admin', 'admin');

copy import_data (drs, gve, rs, mun, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, total) FROM '/dengue-data.csv' DELIMITER ';' CSV HEADER ENCODING 'UTF8' QUOTE '\' ESCAPE '''';