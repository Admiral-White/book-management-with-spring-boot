drop user if exists 'library'@'localhost';
Create user 'library'@'localhost' identified by 'library123';
grant all privileges on library_db.* to 'library'@'localhost';
flush privileges;



drop database if exists library_db;
create database library_db;

