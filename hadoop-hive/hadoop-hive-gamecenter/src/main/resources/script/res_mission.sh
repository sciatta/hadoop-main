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

drop table if exists res_mission;

create table if not exists res_mission as
select part_date,plat_id,task_type,task_id,
count(1) as enter_times,
sum(cost_time) as cost_time,
count(case when op_type=3 then task_id end) as fail_times
from ods_task_log
group by part_date,plat_id,task_type,task_id;

"

$HIVEBIN -e "$sql"
