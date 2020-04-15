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

drop table if exists dw_user_basic_info;

create table if not exists dw_user_basic_info(
plat_id             int       comment '游戏',
server_id           int       comment '服区',
channel_id          int       comment '渠道',
user_id             string    comment '用户ID',
device_brand        string    comment '设备品牌',
device_type         string    comment '设备型号',
client_ip          string    comment '注册ip',
role_id             string    comment '角色id',
role_name           string    comment '角色名称',
job_name            string    comment '角色职业',
role_create_date    date      comment '角色创建日期yyyy-MM-dd',
role_create_time    int       comment '角色创建时间戳',
reg_date            date      comment '注册日期',
reg_time            int       comment '注册日期',
operating_version   String    comment '操作系统(小写)'
) comment '获取所有注册用户的创建角色信息'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'  
STORED AS orc tblproperties ('orc.compress'='SNAPPY');

insert overwrite table dw_user_basic_info
select plat_id, server_id, channel_id, user_id, device_brand, device_type, client_ip, 
role_id, role_name, job_name, role_create_date, role_create_time, reg_date,reg_time, operating_system
from 
(
  select case when channel_id=0 then 1 
         when plat_id=0 and channel_id=0 then 1
         else channel_id end as channel_id,
  plat_id, server_id, user_id, device_brand, device_type, client_ip,
  role_id, role_name, job_name, role_create_date, role_create_time, reg_date,reg_time, operating_system
  from 
  (
    select t.channel_id,t.plat_id, t.server_id, t.user_id, t.device_brand, t.device_type, t.client_ip,
    t.role_id, t.role_name, t.job_name, t.role_create_date, t.role_create_time, t.reg_date, t.reg_time, t.operating_system
    from 
    (
      select 
      case when t.channel_id is null then t1.channel_id else t.channel_id end as channel_id,
      case when t.plat_id is null then t1.plat_id else t.plat_id end as plat_id,
      case when t1.server_id is null then t.server_id else t1.server_id end as server_id,
      case when t.user_id is null then t1.user_id else t.user_id end as user_id,
      case when t.device_brand is null then '' else t.device_brand end as device_brand,
      case when t.device_type is null then '' else t.device_type end as device_type,
      case when t.client_ip is null then '' else t.client_ip end as client_ip,
      t1.role_id,t1.role_name,t1.job_name,t1.role_create_date,t1.role_create_time,
      case when t.reg_date is null then '2019-01-01' else t.reg_date end as reg_date, 
      case when t.reg_time is null then 1546300800 else t.reg_time end as reg_time,
      case when t.operating_system is null then '' else t.operating_system end as operating_system
      from
      (
        select t.channel_id, server_id,t.plat_id,t.user_id,t.device_brand,t.device_type,t.client_ip,
        from_unixtime(t.event_time,'yyyy-MM-dd') as reg_date,t.event_time as reg_time,t.operating_system
        from 
        (
          select channel_id, plat_id, user_id, server_id, device_brand, device_type, client_ip, event_time,operating_system
          from 
          (
           select channel_id, plat_id, user_id, server_id, device_brand, device_type, client_ip, event_time,operating_system,
           ROW_NUMBER() OVER(PARTITION BY channel_id, plat_id, user_id ORDER BY event_time asc) AS rn 
           from ods_game_create
          ) t where rn=1
        ) t  
      ) t
      full outer join 
      (
        select channel_id,plat_id,server_id,user_id,role_id,role_name,job_name,role_create_date,role_create_time
        from
        (
          select channel_id,plat_id,server_id,user_id,role_id,role_name,job_name,role_create_date,role_create_time,
          ROW_NUMBER() OVER(PARTITION BY plat_id,server_id,user_id,role_id ORDER BY role_create_time asc) AS rn
          from dw_role_login_merge 
        ) t where rn=1
      ) t1 on (t.plat_id=t1.plat_id and t.user_id=t1.user_id)
    ) t 
  ) t 
) t 
group by plat_id, server_id, channel_id, server_id, user_id, device_brand, device_type, client_ip, 
role_id, role_name, job_name, role_create_date, role_create_time, reg_date, reg_time, operating_system;
"

$HIVEBIN -e "$sql"