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

drop table if exists tmp_user_lost;

create table if not exists tmp_user_lost as
select t.plat_id,t.channel_id,t.user_id,login_date,
ROW_NUMBER() OVER(PARTITION BY t.channel_id,t.plat_id,t.user_id ORDER BY login_date desc) AS rn
from 
(
  select plat_id,channel_id,user_id,from_unixtime(event_time,'yyyy-MM-dd') as login_date
  from ods_user_login
  group by plat_id,channel_id,user_id,from_unixtime(event_time,'yyyy-MM-dd')
) t ;


drop table if exists res_user_lost;

create table if not exists res_user_lost as
select plat_id,channel_id,login_date as event_date, 
count(distinct user_id) as users,
count(distinct case when num>=1 then user_id end) as login1,
count(distinct case when num>=3 then user_id end) as login3,
count(distinct case when num>=7 then user_id end) as login7,
count(distinct case when num>=15 then user_id end) as login15,
count(distinct case when num>=30 then user_id end) as login30,
count(distinct case when num>=60 then user_id end) as login60
from 
(
  select plat_id,channel_id,user_id,login_date,datediff(login_date_2,login_date)-1 as num
  from 
  (
    select t.plat_id,t.channel_id,t.user_id,t.login_date,
    case when t1.login_date is null then from_unixtime(unix_timestamp(),'yyyy-MM-dd') else t1.login_date end as login_date_2
    from tmp_user_lost t 
    left outer join tmp_user_lost t1 on (t1.plat_id=t.plat_id and t1.channel_id=t.channel_id and t1.user_id=t.user_id and t1.rn+1=t.rn)
  ) t 
) t 
group by plat_id,channel_id,login_date;

"

$HIVEBIN -e "$sql"
