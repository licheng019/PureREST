DROP DATABASE IF EXISTS my_play;

CREATE DATABASE my_play;

GRANT ALL PRIVILEGES ON my_play.* TO my_play_dbuser@localhost IDENTIFIED BY 'workhardplayhard';

USE my_play;


CREATE TABLE country (
  id int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(4) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS company (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL DEFAULT '',
  country varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS Customer (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  fname varchar(20) NOT NULL DEFAULT '',
  lname varchar(20) NOT NULL DEFAULT '',
  description TEXT,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

# -------------------------------------------------#

