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

drop table if exists tmp_user_ltv;

create table if not exists tmp_user_ltv as
select t.channel_id,t.plat_id,t.server_id, 
t.user_id,t.device_brand,t.device_type,t.reg_date,t.reg_time,t.role_id,t.role_name,t.role_create_date,t.role_create_time,
t1.pay_date,case when t1.recharge_amount is null then 0 else t1.recharge_amount end as recharge_amount, 0 as operating_system
from dw_user_basic_info t 
left outer join 
(
  select channel_id,plat_id,user_id,server_id,from_unixtime(event_time,'yyyy-MM-dd') as pay_date,role_id,sum(recharge_amount) as recharge_amount
  from ods_role_recharge  
  group by channel_id,plat_id,user_id,server_id,from_unixtime(event_time,'yyyy-MM-dd'),role_id
) t1 on (t1.channel_id=t.channel_id and t1.plat_id=t.plat_id and t1.server_id=t.server_id and t1.user_id=t.user_id and t1.role_id=t.role_id);


drop table if exists res_ltv_count;

create table if not exists res_ltv_count as
select t.plat_id,t.channel_id,t.event_date, t.reg_users,
ltv_1, ltv_2,ltv_3,ltv_4,ltv_5,ltv_6,ltv_7,ltv_10,ltv_14,ltv_30,ltv_2m,ltv_3m,ltv_6m,ltv_9m,ltv_1year,
ltv_1_users, ltv_2_users,ltv_3_users,ltv_4_users,ltv_5_users,ltv_6_users,ltv_7_users,ltv_10_users,ltv_14_users,ltv_30_users,ltv_2m_users,
ltv_3m_users,ltv_6m_users,ltv_9m_users,ltv_1year_users
from 
(
  select plat_id,channel_id,reg_date as event_date, count(distinct user_id) as reg_users
  from tmp_user_ltv 
  group by plat_id,channel_id,reg_date
) t 
left outer join 
(
  select plat_id,channel_id,reg_date as event_date, 
  sum(case when reg_date=pay_date then recharge_amount else 0 end) as ltv_1, 
  sum(case when pay_date<=date_add(reg_date,1) then recharge_amount else 0 end) as ltv_2,
  sum(case when pay_date<=date_add(reg_date,2) then recharge_amount else 0 end) as ltv_3,
  sum(case when pay_date<=date_add(reg_date,3) then recharge_amount else 0 end) as ltv_4,
  sum(case when pay_date<=date_add(reg_date,4) then recharge_amount else 0 end) as ltv_5,
  sum(case when pay_date<=date_add(reg_date,5) then recharge_amount else 0 end) as ltv_6,
  sum(case when pay_date<=date_add(reg_date,6) then recharge_amount else 0 end) as ltv_7,
  sum(case when pay_date<=date_add(reg_date,9) then recharge_amount else 0 end) as ltv_10,
  sum(case when pay_date<=date_add(reg_date,13) then recharge_amount else 0 end) as ltv_14,
  sum(case when pay_date<=date_add(reg_date,29) then recharge_amount else 0 end) as ltv_30,
  sum(case when pay_date<=date_add(reg_date,59) then recharge_amount else 0 end) as ltv_2m,
  sum(case when pay_date<=date_add(reg_date,89) then recharge_amount else 0 end) as ltv_3m,
  sum(case when pay_date<=date_add(reg_date,179) then recharge_amount else 0 end) as ltv_6m,
  sum(case when pay_date<=date_add(reg_date,269) then recharge_amount else 0 end) as ltv_9m,
  sum(case when pay_date<=date_add(reg_date,355) then recharge_amount else 0 end) as ltv_1year
  from tmp_user_ltv 
  where recharge_amount>0
  group by plat_id,channel_id,reg_date
) t1 on (t1.plat_id=t.plat_id and t1.channel_id=t.channel_id and t1.event_date=t.event_date)
left outer join 
(
  select plat_id,channel_id,reg_date as event_date,
  count(case when reg_date=pay_date then user_id end) as ltv_1_users, 
  count(case when pay_date=date_add(reg_date,1)   then user_id end) as ltv_2_users,
  count(case when pay_date=date_add(reg_date,2)   then user_id end) as ltv_3_users,
  count(case when pay_date=date_add(reg_date,3)   then user_id end) as ltv_4_users,
  count(case when pay_date=date_add(reg_date,4)   then user_id end) as ltv_5_users,
  count(case when pay_date=date_add(reg_date,5)   then user_id end) as ltv_6_users,
  count(case when pay_date=date_add(reg_date,6)   then user_id end) as ltv_7_users,
  count(case when pay_date=date_add(reg_date,9)   then user_id end) as ltv_10_users,
  count(case when pay_date=date_add(reg_date,13)  then user_id end) as ltv_14_users,
  count(case when pay_date=date_add(reg_date,29)  then user_id end) as ltv_30_users,
  count(case when pay_date=date_add(reg_date,59)  then user_id end) as ltv_2m_users,
  count(case when pay_date=date_add(reg_date,89)  then user_id end) as ltv_3m_users,
  count(case when pay_date=date_add(reg_date,179) then user_id end) as ltv_6m_users,
  count(case when pay_date=date_add(reg_date,269) then user_id end) as ltv_9m_users,
  count(case when pay_date=date_add(reg_date,355) then user_id end) as ltv_1year_users
  from 
  (
   select plat_id,channel_id,reg_date,pay_date,user_id
   from tmp_user_ltv 
   where recharge_amount>0
   group by plat_id,channel_id,reg_date,pay_date,user_id
  ) t 
  group by plat_id,channel_id,reg_date
) t2 on (t2.plat_id=t.plat_id and t2.channel_id=t.channel_id and t2.event_date=t.event_date);


drop table if exists res_stock_consumer;

create table if not exists res_stock_consumer as
select login_date as event_date, plat_id, 
acer_count as buy_num,
case when consumer>0 then consumer else 0 end as consumer_num,
case when consumer<0 then abs(consumer)-acer_count else 0 end as give_num
from 
(
  select login_date,plat_id,acer_count,acer_stock,
  last_acer_stock-acer_stock as consumer
  from 
  (
    select login_date,plat_id,acer_count,acer_stock,
    LAG(acer_stock,1,0) OVER(PARTITION BY plat_id ORDER BY login_date) AS last_acer_stock
    from 
    (
      select login_date,plat_id,sum(acer_count) as acer_count,sum(acer_stock) as acer_stock
      from dw_user_behavior_info 
      group by login_date,plat_id
    ) t 
  ) t 
) t ;


drop table if exists res_consumer_hobby;

create table if not exists res_consumer_hobby as
select part_date,plat_id,recharge_purpose,
count(order_id) as buy_times,
sum(recharge_amount)
from ods_role_recharge 
group by part_date,plat_id,recharge_purpose;


drop table if exists res_consumer_point;

create table if not exists res_consumer_point as
select part_date,plat_id,recharge_purpose,level_after,buy_times,recharge_amount,acer_count,
last_acer_stock-acer_count as consumer_num
from 
(
  select part_date,plat_id,recharge_purpose,level_after,buy_times,recharge_amount,acer_count,
  LAG(acer_count,1,0) OVER(PARTITION BY plat_id,recharge_purpose ORDER BY part_date) AS last_acer_stock
  from 
  (
    select t.part_date,t.plat_id,t.recharge_purpose,t1.level_after,
    sum(buy_times) as buy_times,
    sum(recharge_amount) as recharge_amount,
    sum(acer_count) as acer_count
    from  
    (
      select part_date,plat_id,user_id,role_id,recharge_purpose,
      count(order_id) as buy_times,
      sum(recharge_amount) as recharge_amount,
      sum(acer_count) as acer_count
      from ods_role_recharge 
      group by part_date,plat_id,user_id,role_id,recharge_purpose
    ) t 
    left outer join 
    (
      select part_date,plat_id,user_id,role_id,max(level_after) as level_after
      from ods_role_level_up 
      group by part_date,plat_id,user_id,role_id
    ) t1 on (t1.part_date=t.part_date and t1.plat_id=t.plat_id and t1.user_id=t.user_id and t1.role_id=t.role_id)
    group by t.part_date,t.plat_id,t.recharge_purpose,t1.level_after
  ) t 
) t ;

"
$HIVEBIN -e "$sql"
