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
set hive.exec.mode.local.auto.inputbytes.max=262144;
set hive.exec.mode.local.auto.input.files.max=5;

create database if not exists $DBNAME;
use $DBNAME;

drop table if exists res_channel_num;

create table if not exists res_channel_num as
select login_date,channel_id,
count(distinct client_ip,device_brand,device_type) as device_num,
count(distinct user_id) as reg_user,
count(distinct user_id) as active_users,
count(distinct case when login_times=1 then user_id end) as play_once_users,
count(distinct case when recharge_amount>0 then user_id end) as pay_users,
count(distinct user_id) as pay_user,
sum(recharge_amount) as pay_money 
from dw_user_behavior_info 
where recharge_amount>0
group by login_date,channel_id ;


drop table if exists res_channel_stay;

create table if not exists res_channel_stay as
select plat_id,channel_id, reg_date as event_date,
count(distinct user_id) as reg_users,
count(distinct case when datediff(login_date,reg_date)=1 then user_id end) as stay_s,
count(distinct case when datediff(login_date,reg_date)=2 then user_id end) as stay_3_s,
count(distinct case when datediff(login_date,reg_date)=3 then user_id end) as stay_4_s,
count(distinct case when datediff(login_date,reg_date)=4 then user_id end) as stay_5_s,
count(distinct case when datediff(login_date,reg_date)=5 then user_id end) as stay_6_s,
count(distinct case when datediff(login_date,reg_date)=6 then user_id end) as stay_7_s,
count(distinct case when datediff(login_date,reg_date)=14 then user_id end) as stay_15_s,
count(distinct case when datediff(login_date,reg_date)=29 then user_id end) as stay_30_s
from tmp_user_stay 
group by plat_id,channel_id,reg_date;

"

$HIVEBIN -e "$sql"
