drop database post;

create database post default character set utf8;

use post;

create table post (
  id int AUTO_INCREMENT PRIMARY KEY,
  title varchar(50) not null,
  content text not null,
  tags varchar(100),
  tdate date not null,
  vtime int DEFAULT 0,
  url varchar(50) unique
);

create table tag (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(20) unique
);

create table picture (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(40) unique;
)