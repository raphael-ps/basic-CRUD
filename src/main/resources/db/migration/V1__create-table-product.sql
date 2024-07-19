create table product(
    id  varchar(255) primary key unique not null,
    name text NOT NULL,
    price_in_cents INT NOT NULL
);