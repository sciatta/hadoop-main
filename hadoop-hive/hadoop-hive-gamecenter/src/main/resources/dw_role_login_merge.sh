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

drop table if exists dw_role_login_merge;

create table if not exists dw_role_login_merge(
plat_id                 int        comment '游戏',
server_id               int        comment '服区',
channel_id              int        comment '渠道',
user_id                 string     comment 'user_id',
role_id                 string     comment '角色ID',
role_name               string     comment '角色名称',
job_name                string     comment '角色名称',
role_create_date        date       comment '角色创建日期yyyy-MM-dd',
role_create_time        int        comment '角色创建时间戳'
) 
comment '整合角色创建与角色登陆'
PARTITIONED BY(part_date date)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS orc tblproperties ('orc.compress'='SNAPPY');

insert overwrite table dw_role_login_merge partition(part_date) 
select plat_id,server_id,channel_id,user_id,role_id,role_name,job_name,role_create_date,
min(role_create_time) as role_create_time,part_date
from  
(
  select 
  case when t1.channel_id is null then t.channel_id else t1.channel_id end as channel_id,
  case when t1.plat_id is null then t.plat_id else t1.plat_id end as plat_id,
  case when t1.server_id is null then t.server_id else t1.server_id end as server_id,
  case when t1.user_id is null then t.user_id else t1.user_id end as user_id,
  case when t1.role_id is null then t.role_id else t1.role_id end as role_id,
  case when t1.role_name is null then t.role_name else t1.role_name end as role_name,
  case when t1.job_name is null then '' else t1.job_name end as job_name,
  case when from_unixtime(t1.event_time,'yyyy-MM-dd') is null then '2019-01-01' else from_unixtime(t1.event_time,'yyyy-MM-dd') end as role_create_date,
  case when t1.event_time is null then 1546300800 else t1.event_time  end as role_create_time,
  t.part_date
  from
  (
    select channel_id,plat_id,server_id,user_id,role_id,role_name,part_date
    from ods_user_login 
    group by channel_id,plat_id,server_id,user_id,role_id,role_name,part_date
  ) t 
  full outer join ods_role_create t1 on (t1.plat_id=t.plat_id and t1.user_id=t.user_id and t1.role_id=t.role_id and t.part_date = t1.part_date)
) t
group by channel_id,plat_id,server_id,user_id,role_id,role_name,job_name,role_create_date,part_date;
"

$HIVEBIN -e "$sql"