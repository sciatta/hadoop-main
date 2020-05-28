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

drop table if exists tmp_user_stay;

create table if not exists tmp_user_stay as
select t.channel_id,t.plat_id,t.server_id,t.user_id,t.device_brand,t.device_type,
t.reg_date,t.reg_time,t.role_id,t.role_name,t.role_create_date,t.role_create_time,t1.login_date 
from dw_user_basic_info t 
left outer join 
(
  select channel_id,plat_id,user_id,server_id, from_unixtime(event_time,'yyyy-MM-dd') as login_date ,role_id
  from ods_user_login 
  group by channel_id,plat_id,user_id,server_id, from_unixtime(event_time,'yyyy-MM-dd'),role_id
) t1 on (t1.plat_id=t.plat_id and t1.server_id=t.server_id and t1.user_id=t.user_id and t1.role_id=t.role_id);


drop table if exists res_user_stay;

create table res_user_stay as
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
