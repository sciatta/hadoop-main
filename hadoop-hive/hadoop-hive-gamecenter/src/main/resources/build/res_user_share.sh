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

drop table if exists res_user_share;

create table if not exists res_user_share as
select t.ddate,t.plat_id,t.channel_id,
share_users, share_times,
case when share_newusers  is null then 0 else share_newusers  end as share_newusers,
case when stay_2  is null then 0 else stay_2  end as stay_2,
case when stay_3  is null then 0 else stay_3  end as stay_3,
case when stay_4  is null then 0 else stay_4  end as stay_4,
case when stay_5  is null then 0 else stay_5  end as stay_5,
case when stay_6  is null then 0 else stay_6  end as stay_6,
case when stay_7  is null then 0 else stay_7  end as stay_7,
case when stay_10 is null then 0 else stay_10 end as stay_10,
case when stay_15 is null then 0 else stay_15 end as stay_15,
case when stay_30 is null then 0 else stay_30 end as stay_30
from 
(
  select from_unixtime(event_time,'yyyy-MM-dd') as ddate,plat_id,channel_id,
  count(distinct user_id) as share_users, count(distinct event_time,log_id) as share_times
  from ods_user_share where part_date>='2019-11-01' and part_date<='2019-11-05'  
  group by from_unixtime(event_time,'yyyy-MM-dd'),plat_id,channel_id
) t 
left outer join 
(
  select t.ddate,t.plat_id,t.channel_id,
  count(distinct t.user_id) as share_newusers,
  count(distinct case when datediff(t1.ddate,t.ddate)=1  then t.user_id end) as stay_2,
  count(distinct case when datediff(t1.ddate,t.ddate)=2  then t.user_id end) as stay_3,
  count(distinct case when datediff(t1.ddate,t.ddate)=3  then t.user_id end) as stay_4,
  count(distinct case when datediff(t1.ddate,t.ddate)=4  then t.user_id end) as stay_5,
  count(distinct case when datediff(t1.ddate,t.ddate)=5  then t.user_id end) as stay_6,
  count(distinct case when datediff(t1.ddate,t.ddate)=6  then t.user_id end) as stay_7,
  count(distinct case when datediff(t1.ddate,t.ddate)=9  then t.user_id end) as stay_10,
  count(distinct case when datediff(t1.ddate,t.ddate)=14 then t.user_id end) as stay_15,
  count(distinct case when datediff(t1.ddate,t.ddate)=29 then t.user_id end) as stay_30
  from 
  (
    select t.plat_id,channel_id,t.user_id,t.ddate
    from 
    (
      select plat_id,channel_id,user_id,from_unixtime(event_time,'yyyy-MM-dd') as ddate
      from ods_game_create
      where part_date>='2019-10-01' and part_date<='2019-10-05'
    ) t 
    join 
    (
      select plat_id, user_id,from_unixtime(event_time,'yyyy-MM-dd') as ddate
      from ods_user_share where part_date>='2019-10-01' and part_date<='2019-10-05' and share_id is not null 
      group by plat_id, user_id,from_unixtime(event_time,'yyyy-MM-dd')
    ) t1 on (t1.plat_id=t.plat_id and t1.ddate=t.ddate and t1.user_id=t.user_id)
  ) t 
  left outer join 
  (
    select user_id,from_unixtime(event_time,'yyyy-MM-dd') as ddate
    from ods_user_login where part_date>='2019-10-01' and part_date<='2019-10-05'
    group by user_id,from_unixtime(event_time,'yyyy-MM-dd')
  ) t1 on (t1.user_id=t.user_id)
  group by t.ddate,t.plat_id,t.channel_id
) t1 on (t.ddate=t1.ddate and t.plat_id=t1.plat_id and t.channel_id=t1.channel_id);

"

$HIVEBIN -e "$sql"
