create database if not exists switch;
use switch;

drop table if exists users;
create table users
(
    id   integer     not null auto_increment,
    name varchar(20) not null,
    description varchar(40) not null,
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
