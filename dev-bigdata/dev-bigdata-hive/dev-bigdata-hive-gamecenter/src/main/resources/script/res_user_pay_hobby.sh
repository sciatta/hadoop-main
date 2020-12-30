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

drop table if exists res_pay_trans;

create table if not exists res_pay_trans(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  new_pay_users    int COMMENT '新增付费玩家',
  all_pay_users    int  COMMENT '总付费玩家'
)
comment '付费转化'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_pay_trans
select plat_id,channel_id,event_date,new_pay_users,
SUM(new_pay_users) OVER(PARTITION BY plat_id,channel_id ORDER BY event_date) AS all_pay_users
from 
(
  select plat_id,channel_id,part_date as event_date,count(distinct user_id) as new_pay_users
  from dw_user_behavior_info 
  where recharge_amount>0
  group by plat_id,channel_id,part_date
) t ;


drop table if exists res_pay_info;

create table if not exists res_pay_info(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  pay_money    double COMMENT '充值金额',
  pay_users    int  COMMENT '充值人数',
  pay_times    int  COMMENT '充值次数'
)
comment '收入分析'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_pay_info
select plat_id,channel_id,part_date as event_date,
sum(recharge_amount) as pay_money,
count(distinct user_id) as pay_users,
sum(pay_times) as pay_times
from dw_user_behavior_info 
where recharge_amount>0
group by plat_id,channel_id,part_date;


drop table if exists res_pay_habit;

create table if not exists res_pay_habit(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  pay_times_id int COMMENT '充值次数id,用于区间排序',
  pay_times_area    string  COMMENT '充值次数',
  pay_users    int  COMMENT '充值人数'
)
comment '付费习惯'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_pay_habit
select plat_id,channel_id, event_date,pay_times_id,pay_times_area,
count(1) as pay_users
from 
(
  select plat_id,channel_id, event_date,user_id,
  case 
  when pay_times=0 or pay_times is null then 0
  when pay_times=1 then  1
  when pay_times=2 then  2
  when pay_times=3 then  3
  when pay_times=4 then  4
  when pay_times=5 then  5
  when pay_times>=6 and pay_times<=10 then 6
  when pay_times>=11 and pay_times<=20 then 7
  when pay_times>=21 and pay_times<=30 then 8
  when pay_times>=31 and pay_times<=40 then 9
  else 10 end as pay_times_id,
  case 
  when pay_times=0 or pay_times is null then '未付费'
  when pay_times=1 then  '1次'
  when pay_times=2 then  '2次'
  when pay_times=3 then  '3次'
  when pay_times=4 then  '4次'
  when pay_times=5 then  '5次'
  when pay_times>=6 and pay_times<=10 then '6~10次'
  when pay_times>=11 and pay_times<=20 then '11~20次'
  when pay_times>=21 and pay_times<=30 then '21~30次'
  when pay_times>=31 and pay_times<=40 then '31~40次'
  else '40次以上' end as pay_times_area
  from 
  (
    select plat_id,channel_id,part_date as event_date,user_id,
    sum(pay_times) as pay_times
    from dw_user_behavior_info 
    where recharge_amount>0
    group by plat_id,channel_id,part_date,user_id
  ) t 
) t 
group by plat_id,channel_id, event_date,pay_times_id,pay_times_area;


drop table if exists res_pay_seep;

create table if not exists res_pay_seep(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  active_user  int  COMMENT '活跃人数',
  pay_users    int  COMMENT '付费人数'
)
comment '等级分布'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_pay_seep
select plat_id,channel_id,part_date as event_date,
count(distinct user_id) as active_user,
count(distinct case when recharge_amount>0 then user_id end) as active_user
from dw_user_behavior_info 
group by plat_id,channel_id,part_date;


drop table if exists res_newuser_value;

create table if not exists res_newuser_value(
  plat_id      int  COMMENT '平台ID',
  channel_id   int  COMMENT '渠道id',
  event_date   date COMMENT '时间',
  pay_money7   double   COMMENT '7日贡献',
  pay_money14  double   COMMENT '14日贡献',
  pay_money30  double   COMMENT '30日贡献',
  pay_money60  double   COMMENT '60日贡献',
  pay_money90  double   COMMENT '90日贡献'
)
comment '新玩家价值'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

insert overwrite table res_newuser_value
select plat_id,channel_id,reg_date as event_date,
sum(case when recharge_amount>0 and datediff(part_date,reg_date)<=7 then recharge_amount else 0 end) as pay_money7,
sum(case when recharge_amount>0 and datediff(part_date,reg_date)<=14 then recharge_amount else 0 end) as pay_money14,
sum(case when recharge_amount>0 and datediff(part_date,reg_date)<=30 then recharge_amount else 0 end) as pay_money30,
sum(case when recharge_amount>0 and datediff(part_date,reg_date)<=60 then recharge_amount else 0 end) as pay_money60,
sum(case when recharge_amount>0 and datediff(part_date,reg_date)<=90 then recharge_amount else 0 end) as pay_money90
from dw_user_behavior_info 
group by plat_id,channel_id,reg_date;

"

$HIVEBIN -e "$sql"
