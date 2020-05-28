#!/bin/bash

source public.sh

# 如果是输入的日期按照取输入日期；如果没输入日期取当前时间的前一天
if [ -n "$1" ]; then
  part_date=$1
else
  part_date=$(date -d "-1 day" +%F)
fi
echo "===日志日期为 $part_date==="

sql="

set hive.exec.mode.local.auto=true;  
set hive.exec.mode.local.auto.inputbytes.max=268435456;
set hive.exec.mode.local.auto.input.files.max=5;

create database if not exists $DBNAME;
use $DBNAME;

drop table if exists res_new_users;

create table if not exists res_new_users(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  reg_users    int  COMMENT '注册人数',
  reg_ips      int  COMMENT '注册ip数'
)
comment '新增玩家'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_new_users
select plat_id,channel_id, reg_date as event_date,
count(distinct user_id) as reg_users,count(distinct client_ip) as reg_ips
from dw_user_basic_info 
group by plat_id,channel_id,reg_date;


drop table if exists res_active_users;

create table if not exists res_active_users(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  new_users    int  COMMENT '新玩家',
  old_users    int  COMMENT '老玩家',
  all_users    int  COMMENT '总计'
)
comment '活跃玩家'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_active_users
select plat_id,channel_id,part_date as event_date,
count(distinct case when reg_date=login_date then user_id end) as new_users,
count(distinct case when reg_date<login_date then user_id end) as old_users,
count(distinct user_id) as all_users
from dw_user_behavior_info 
group by plat_id,channel_id,part_date;


drop table if exists res_play_habit;

create table if not exists res_play_habit(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  online_times    int  COMMENT '在线时长',
  login_times    int  COMMENT '登录次数即游戏次数'
)
comment '游戏习惯'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_play_habit
select plat_id,channel_id,part_date as event_date,
sum(online_times) as online_times,
sum(login_times) as login_times
from dw_user_behavior_info 
group by plat_id,channel_id,part_date;


drop table if exists res_level_info;

create table if not exists res_level_info(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  role_level_after    int COMMENT '等级',
  online_times    int  COMMENT '等级在线时长',
  pay_times   int  COMMENT '充值次数',
  recharge_amount   double   COMMENT '充值金额'
)
comment '游戏习惯'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_level_info
select plat_id,channel_id,part_date as event_date,role_level_after,
sum(online_times) as online_times,
sum(pay_times) as pay_times,
sum(recharge_amount) as recharge_amount
from dw_user_behavior_info 
group by plat_id,channel_id,part_date,role_level_after;


drop table if exists res_level_users;

create table if not exists res_level_users(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  role_level_after    int COMMENT '等级',
  users    int  COMMENT '人数'
)
comment '等级分布'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_level_users
select plat_id,channel_id,part_date as event_date,role_level_after,
count(distinct user_id) as users
from dw_user_behavior_info 
group by plat_id,channel_id,part_date,role_level_after;


drop table if exists res_newuser_level;

create table if not exists res_newuser_level(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  role_level_after    int COMMENT '等级',
  users    int  COMMENT '人数'
)
comment '等级分布'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_newuser_level
select plat_id,channel_id,part_date as event_date,role_level_after,
count(distinct user_id) as users
from dw_user_behavior_info 
where reg_date=login_date
group by plat_id,channel_id,part_date,role_level_after;


drop table if exists res_device_info;

create table if not exists res_device_info(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  device_brand   string COMMENT '设备品牌',
  device_type    string COMMENT '设备型号',
  users    int  COMMENT '人数'
)
comment '等级分布'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_device_info
select plat_id,channel_id,reg_date as event_date,device_brand,device_type,
count(distinct user_id) as users
from dw_user_basic_info 
group by plat_id,channel_id,reg_date,device_brand,device_type;

"

$HIVEBIN -e "$sql"
