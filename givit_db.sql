DROP DATABASE IF EXISTS givit_db;

CREATE DATABASE givit_db;

USE givit_db;

CREATE TABLE IF NOT EXISTS user (
 userId int(11) unsigned Not NULL AUTO_INCREMENT primary key,
 username varchar(40) Not NULL,
 password varchar(255) Not NULL,
 id_type varchar(10),
 id_name varchar(32),
 last_login_time timestamp
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user_session (
 userId int(11) PRIMARY KEY,
 token varchar(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS userProfile (
 userId int(11) primary key,
 nick_name varchar(64),
 first_name varchar(32),
 last_name varchar(32),
 address_line_1 varchar(60),
 address_line_2 varchar(60),
 zip_code varchar(12),
 phone varchar(20),
 icon char(16),
 prime_memebr char(1)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;