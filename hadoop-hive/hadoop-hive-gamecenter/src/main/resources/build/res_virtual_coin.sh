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

drop table if exists res_virtual_coin;

create table if not exists res_virtual_coin as
select plat_id,channel_id,user_id,sum(monetary_stock) as monetary_stock
from 
(
  select plat_id,channel_id,user_id,role_id,monetary_stock
  from 
  (
    select plat_id,channel_id,user_id,role_id,monetary_stock,event_time,
    ROW_NUMBER() OVER(PARTITION BY plat_id,user_id,role_id ORDER BY event_time desc) rn
    from 
    (
      select plat_id,channel_id,user_id,role_id,monetary_stock,event_time
      from ods_monetary_consume 
      where part_date>='2019-10-03' and part_date<='2019-10-04' and monetary_stock>0
      union all  
      select plat_id,channel_id,user_id,role_id,monetary_stock,event_time
      from ods_monetary_output
      where part_date>='2019-10-04' and part_date<='2019-10-05' and monetary_stock>0
    ) t 
  ) t 
  where rn=1
) t 
group by plat_id,channel_id,user_id;

"

$HIVEBIN -e "$sql"
