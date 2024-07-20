alter table product
add column amount int not null,
add column active boolean not null;

update product set active = true, amount = 0;