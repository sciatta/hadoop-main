CREATE DATABASE IF NOT EXISTS mall;
USE mall;

DROP TABLE IF EXISTS mall.users;
CREATE TABLE IF NOT EXISTS mall.users
(
    id        INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name      VARCHAR(20)  NOT NULL DEFAULT '',
    nickname  VARCHAR(20)  NOT NULL DEFAULT '',
    password  VARCHAR(20)  NOT NULL,
    id_number VARCHAR(18)  NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into mall.users(name, nickname, password, id_number) VALUES ('yxy','rain','123456','1234567890');
insert into mall.users(name, nickname, password, id_number) VALUES ('yl','yoyo','67890','0987654321');

DROP TABLE IF EXISTS mall.products;
CREATE TABLE IF NOT EXISTS mall.products
(
    id    INT UNSIGNED   NOT NULL AUTO_INCREMENT,
    name  VARCHAR(20)    NOT NULL DEFAULT '',
    price DECIMAL(20, 2) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into mall.products(name, price) VALUES ('phone','5000');
insert into mall.products(name, price) VALUES ('watch','2000');

DROP TABLE IF EXISTS mall.orders;
CREATE TABLE IF NOT EXISTS mall.orders
(
    order_id     INT UNSIGNED   NOT NULL AUTO_INCREMENT,
    user_id      INT UNSIGNED   NOT NULL,
    total_number INT            NOT NULL,
    total_amount DECIMAL(20, 2) NOT NULL,
    PRIMARY KEY (order_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS mall.order_details;
CREATE TABLE IF NOT EXISTS mall.order_details
(
    order_id   INT UNSIGNED   NOT NULL,
    product_id INT UNSIGNED   NOT NULL,
    price      DECIMAL(20, 2) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;