#!/bin/bash

source public.sh

# 如果是输入的日期按照取输入日期；如果没输入日期取当前时间的前一天
if [ -n "$1" ] ;then
   part_date=$1
else 
   part_date=`date -d "-1 day" +%F`
fi 

echo "===日志日期为 $part_date==="

sql="
create database if not exists $DBNAME;
use $DBNAME;

set hive.exec.dynamic.partition=true; 
set hive.exec.dynamic.partition.mode=nonstrict; 
set hive.exec.mode.local.auto=true;  
set hive.exec.mode.local.auto.inputbytes.max=262144;
set hive.exec.mode.local.auto.input.files.max=5;

drop table if exists dw_level_up;

create table if not exists dw_level_up(
plat_id           string  comment '游戏',
server_id         int     comment '服区',
user_id           string  comment '用户ID',
role_id           string  comment '角色id',
role_name         string  comment '角色名称',
role_level_after  int     comment '升级后的等级'
) 
comment '获取所有注册用户的角色升级后最大level信息'
PARTITIONED BY(part_date date)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS orc tblproperties ('orc.compress'='SNAPPY');

insert overwrite table dw_level_up partition(part_date) 
select plat_id, server_id, user_id, role_id, role_name, max(level_after) as role_level_after, part_date
from ods_role_level_up 
group by plat_id, server_id, user_id, role_id, role_name, part_date;
"

$HIVEBIN -e "$sql"