create database category;
create user 'categoryAdmin'@'%' identified by 'Paul';
grant all on category.* to 'categoryAdmin'@'%';