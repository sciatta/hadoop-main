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

drop table if exists dw_user_behavior_login;

create table if not exists dw_user_behavior_login (
login_date      date    comment  '登录日期yyyy-MM-dd',
plat_id         string  comment  '游戏',
server_id       int     comment  '区服',
user_id         string  comment  'user_id',
role_id         string  comment  '角色ID',
role_name       string  comment  '角色名称',
online_times    int     comment  '在线时长',
login_times     int     comment  '登陆次数' ,
source          int     comment  '整合来源1登录, 2角色充值，3战斗力，4升级日志'
)
comment '整合登录日志，整合最全的登陆操作日志'
PARTITIONED BY(part_date date)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'  
STORED AS orc tblproperties ('orc.compress'='SNAPPY'); 

insert overwrite table dw_user_behavior_login partition(part_date) 
select part_date as login_date, plat_id, server_id, user_id, role_id, role_name, online_times, login_times, source,part_date
from (
select plat_id, server_id, user_id, role_id, role_name, online_times, source, login_times,
ROW_NUMBER() OVER(PARTITION BY plat_id, server_id, user_id, role_id, role_name ORDER BY source asc) AS rn ,part_date
from 
(
  select plat_id, server_id, user_id, role_id, role_name, 
  sum(case when online_time>86400 then 0 else online_time end) as online_times, 1 as source, sum(case when op_type=1 then 1 else 0 end) as login_times,part_date
  from 
  (
    select event_time, plat_id, server_id, user_id, role_id, role_name, online_time,op_type,part_date
    from ods_user_login  
    group by event_time, plat_id, server_id, user_id, role_id, role_name, online_time,op_type,part_date
  ) t 
  group by plat_id, server_id, user_id, role_id, role_name,part_date
  union all 
  select plat_id, server_id, user_id, role_id, role_name, 86400 as online_times, 2 as source, 1 as login_times,part_date
  from ods_role_recharge 
  union all 
  select plat_id, server_id,  user_id, role_id, role_name, 86400 as online_times, 3 as source, 1 as login_times,part_date
  from ods_fight_power 
  union all 
  select plat_id, server_id, user_id, role_id, role_name, 86400 as online_times, 4 as source, 1 as login_times,part_date
  from ods_role_level_up 
) t 
) t where rn=1;
"

$HIVEBIN -e "$sql"