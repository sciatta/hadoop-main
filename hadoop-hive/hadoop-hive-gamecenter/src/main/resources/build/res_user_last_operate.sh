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

drop table if exists res_user_last_operate;

create table if not exists res_user_last_operate as
select t.plat_id,t.user_id,role_name,opt_one,opt_two,opt_three
from 
(
  select plat_id,user_id from ods_user_login where part_date='2019-10-21' and op_type=1 group by plat_id,user_id
) t 
join 
(
  select plat_id,user_id, role_name,split(tt,',')[2] as opt_one, split(tt,',')[1] as opt_two, split(tt,',')[0] as opt_three
  from 
  (
    select plat_id,user_id,role_name,concat_ws(',',collect_list(button_id)) as tt
    from 
    (
      select t.plat_id,channel_id,user_id,role_id,role_name,case when t1.button_name is null then t.button_id else t1.button_name end as button_id,
      ROW_NUMBER() OVER(PARTITION BY user_id,role_name ORDER BY event_time desc) as rn 
      from ods_panel_op t 
      left outer join ods_dim_game_button t1 on (t1.button_id=t.button_id)
      where part_date='2019-10-03'
    ) t 
    where rn<=3 
    group by plat_id,user_id,role_name
  ) t 
) t1 on (t1.plat_id=t.plat_id and t1.user_id=t.user_id);

"

$HIVEBIN -e "$sql"
