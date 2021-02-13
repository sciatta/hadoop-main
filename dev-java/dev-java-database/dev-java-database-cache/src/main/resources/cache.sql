create database if not exists cache;
use cache;

drop table if exists cache.users;
create table if not exists cache.users
(
    id   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(20)  NOT NULL DEFAULT '',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into cache.users(name) values ('rain');
insert into cache.users(name) values ('lucky');
insert into cache.users(name) values ('yoyo');