create database product;
create user 'productAdmin'@'%' identified by 'Paul';
grant all on product.* to 'productAdmin'@'%';