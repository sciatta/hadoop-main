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

drop table if exists dw_user_behavior_info;

create table if not exists dw_user_behavior_info (
source                  int             comment      '整合来源1登录,2角色充值，3战斗力，4升级日志',
plat_id                 int             comment      '游戏',
channel_id              int             comment      '渠道',
server_id               int             comment      '区服',
login_date              date            comment      '登录日期yyyy-MM-dd',
user_id                 string          comment      'user_id',
role_id                 string          comment      '角色ID',
role_name               string          comment      '角色名称',
online_times            int             comment      '在线时长',
role_create_date        date            comment      '角色创建日期yyyy-MM-dd',
role_create_time        int             comment      '角色创建时间戳',
job_name                string          comment      '角色职业',
reg_date                date            comment      '注册日期',
reg_time                int             comment      '注册时间戳',
client_ip               string          comment '注册ip',
device_brand            string          comment      '设备品牌',
device_type             string          comment      '设备型号',
recharge_amount         double          comment      '充值金额',
acer_count              int             comment      '充值后获得的元宝数量',
combat_power            int             comment      '玩家变化后的战斗力',
role_level_after        int             comment      '角色升级后等级',
pay_vip_level           int             comment      '充值vip_level',
acer_stock              int             comment      '元宝存量', 
pay_times               int             comment      '充值次数' ,
login_times            int             comment      '登录次数' ,
operating_system      string             comment      '操作系统' 
)
comment '用户行为整合表'
PARTITIONED BY(part_date date)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'  
STORED AS orc tblproperties ('orc.compress'='SNAPPY'); 

insert overwrite table dw_user_behavior_info partition(part_date)
select a.source,
a.plat_id,b.channel_id,a.server_id,a.login_date,a.user_id,a.role_id,b.role_name,a.online_times,
b.role_create_date,b.role_create_time,b.job_name,b.reg_date,b.reg_time,b.client_ip,b.device_brand,b.device_type,
e.recharge_amount,e.acer_count,f.combat_power,g.role_level_after,
0 as pay_vip_level, e.acer_stock, pay_times, a.login_times, 0 as operating_system,a.part_date
from 
(
  select login_date, plat_id, server_id, user_id, role_id, sum(online_times) as online_times, source, sum(login_times) as login_times,part_date
  from dw_user_behavior_login 
  group by login_date, plat_id, server_id, user_id, role_id,source,part_date
) a
left outer join dw_user_basic_info b on (b.plat_id=a.plat_id and b.user_id=a.user_id and b.role_id=a.role_id)
left outer join 
(
  select plat_id, user_id, role_id, sum(recharge_amount) as recharge_amount, sum(acer_count) as acer_count,
  max(acer_count) as acer_stock, count(1) as pay_times,part_date
  from 
  (
    select event_time, plat_id, user_id, role_id, role_name, recharge_amount, acer_count,part_date
    from ods_role_recharge 
    group by event_time, plat_id, user_id, role_id, role_name, recharge_amount, acer_count,part_date
  ) e group by plat_id, user_id, role_id,part_date
) e on (e.plat_id=a.plat_id and e.user_id=a.user_id and e.role_id=a.role_id and e.part_date = a.part_date)
left outer join 
(
  select plat_id, user_id, role_id, max(combat_power) as combat_power,part_date
  from dw_fight_power
  group by plat_id, user_id, role_id,part_date
) f on (f.plat_id=a.plat_id and f.user_id=a.user_id and f.role_id=a.role_id and f.part_date = a.part_date)
left outer join 
(
  select plat_id, user_id, role_id, max(role_level_after) as role_level_after,part_date
  from dw_level_up 
  group by plat_id, user_id, role_id,part_date
) g on (g.plat_id=a.plat_id and g.user_id=a.user_id and g.role_id=a.role_id and g.part_date = a.part_date);
"

$HIVEBIN -e "$sql"