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

drop table if exists res_user_die_channel;

create table if not exists res_user_die_channel as
select t.event_date, t.plat_id,t.channel_id,t.map_id,
count(distinct t.user_id) as death_users, sum(death_times) as death_times,
count(distinct t1.user_id) as death_new_users
from 
(
  select from_unixtime(event_time,'yyyy-MM-dd') as event_date,plat_id,channel_id,user_id,map_id,
  count(distinct log_id) as death_times
  from ods_death_log
  where part_date='2019-10-03'
  group by from_unixtime(event_time,'yyyy-MM-dd'),plat_id,channel_id,user_id,map_id
) t 
left outer join 
(
  select plat_id,user_id,from_unixtime(event_time,'yyyy-MM-dd') as reg_date
  from ods_game_create
  where part_date='2019-10-01'
) t1 on (t1.plat_id=t.plat_id and t1.user_id=t.user_id)
group by t.event_date, t.plat_id,t.channel_id,t.map_id;

"

$HIVEBIN -e "$sql"
