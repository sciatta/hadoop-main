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

drop table if exists res_basic_count;
create table if not exists res_basic_count as
select t.reg_date,t.server_id, reg_users, login_users, recharge_amount, login_times
from 
(
  select reg_date,server_id,count(distinct user_id) as reg_users
  from dw_user_basic_info  where reg_date = '$part_date'
  group by reg_date,server_id
) t 
left outer join 
(
  select login_date,server_id,count(distinct user_id) as login_users,sum(recharge_amount) as recharge_amount,sum(login_times) as login_times
  from dw_user_behavior_info  where part_date = '$part_date'
  group by login_date,server_id
) t1 on (t1.login_date=t.reg_date and t1.server_id=t.server_id);


drop table if exists res_date_brand_type;
create table if not exists res_date_brand_type as
select t.reg_date,t.device_brand,t.device_type, reg_users, login_users, recharge_amount, login_times
from 
(
  select reg_date,device_brand,device_type,count(distinct user_id) as reg_users
  from dw_user_basic_info where reg_date = '$part_date'
  group by reg_date,device_brand,device_type
) t 
left outer join 
(
  select login_date,device_brand,device_type,count(distinct user_id) as login_users,sum(recharge_amount) as recharge_amount,sum(login_times) as login_times
  from dw_user_behavior_info where part_date = '$part_date'
  group by login_date,device_brand,device_type
) t1 on (t1.login_date =t.reg_date and t1.device_brand=t.device_brand and t1.device_type=t.device_type);
"

$HIVEBIN -e "$sql"
