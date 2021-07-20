#!/bin/bash

source public.sh

sql="
create database if not exists $DBNAME;
use $DBNAME;

CREATE TABLE ods_user_login(
plat_id            string     comment '平台id',
server_id          int        comment '区服id',
channel_id         string     comment '渠道',
user_id            string     comment '用户ID',
role_id            string     comment '角色ID',
role_name          string     comment '角色名称',
client_ip          string     comment '客户端IP',
event_time         int        comment '事件时间',
op_type            string     comment '操作类型(1:登录,-1登出)',
online_time        int        comment '在线时长(s)',
operating_system   string     comment '操作系统名称',
operating_version  string     comment '操作系统版本',
device_brand       string     comment '设备型号',
device_type        string     comment '设备品牌'
)
comment '游戏登录登出'
PARTITIONED BY(part_date date)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

CREATE TABLE tmp_ods_user_login(
plat_id            string     comment '平台id',
server_id          int        comment '区服id',
channel_id         string     comment '渠道',
user_id            string     comment '用户ID',
role_id            string     comment '角色ID',
role_name          string     comment '角色名称',
client_ip          string     comment '客户端IP',
event_time         int        comment '事件时间',
op_type            string     comment '操作类型(1:登录,-1登出)',
online_time        int        comment '在线时长(s)',
operating_system   string     comment '操作系统名称',
operating_version  string     comment '操作系统版本',
device_brand       string     comment '设备型号',
device_type        string     comment '设备品牌'
)
comment '游戏登录登出-临时表，用于将数据通过动态分区载入ods_user_login中'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

load data local inpath '${DATAPATH}ods_user_login.txt' overwrite into table tmp_ods_user_login;

set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nostrict;
set hive.exec.max.dynamic.partitions.pernode=1000;

insert overwrite table ods_user_login partition(part_date)
select plat_id,server_id,channel_id,user_id,role_id,role_name,client_ip,event_time,op_type,online_time,operating_system,operating_version,device_brand,device_type,from_unixtime(event_time,'yyyy-MM-dd') as part_date 
from tmp_ods_user_login;

"

$HIVEBIN -e "$sql"