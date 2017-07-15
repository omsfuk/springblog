drop database noteit;

create database noteit default character set utf8;

use noteit;

create table user (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  name varchar(20) unique,
  password varchar(20) not null,
  email varchar(20) not null,
  roleid int(11)
);

create table directory (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  userid int(11),
  path varchar(200),
  UNIQUE(userid, path)
);

create table role (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  name varchar(20) unique
);

create table privilege (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  name varchar(50) unique
);

create table role_privilege (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  roleid int(11),
  privilegeid int(11),
  FOREIGN KEY(roleid) REFERENCES role(id),
  FOREIGN KEY(privilegeid) REFERENCES privilege(id)
);

create table note (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  userid int(11),
  title varchar(50) not null,
  tags varchar(100),
  directoryid int(11),
  content text not null,
  last_modify date not null,
  views int(11) DEFAULT 0,
  url varchar(50),
  UNIQUE (userid, url),
  FOREIGN KEY(userid) REFERENCES user(id),
  FOREIGN KEY(directoryid) REFERENCES directory(id)
);

create table tag (
  id int AUTO_INCREMENT PRIMARY KEY,
  userid int(20) not null,
  name varchar(20) not null,
  UNIQUE(userid, name),
  FOREIGN KEY(userid) REFERENCES user(id)
);

insert into privilege(name) values("VIEW_PUBLIC_NOTE");

insert into privilege(name) values("READ_ANY_PUBLIC_NOTE");

insert into privilege(name) values("READ_SELF_NOTE");

insert into privilege(name) values("WRITE_SELF_NOTE");

insert into role(name) values("Administrator");

insert into role(name) values("anonymous");

insert into role(name) values("user");

insert into user(name, password, email, roleid) values("omsfuk", "admin", "2441707419@qq.com", 1);

insert into directory(userid, path) values(1, "/Java/");
insert into directory(userid, path) values(1, "/Java/Spring/");
insert into directory(userid, path) values(1, "/Java/Spring/mvc/");
insert into directory(userid, path) values(1, "/Java/MyBatis/");
insert into directory(userid, path) values(1, "/Java/MyBatis/init/");
insert into directory(userid, path) values(1, "/C++/");
insert into directory(userid, path) values(1, "/C/");
insert into directory(userid, path) values(1, "/Python/");
insert into directory(userid, path) values(1, "/Python/Django/");
