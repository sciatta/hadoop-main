#!/bin/bash

SQOOPPATH=/bigdata/install/sqoop-1.4.6-cdh5.14.2

# res_active_users
${SQOOPPATH}/bin/sqoop export \
--connect jdbc:mysql://node03:3306/game_center \
--username root --password root --table res_active_users \
--export-dir /user/hive/warehouse/game_center.db/res_active_users \
--update-key "id"  --update-mode allowinsert \
--columns "plat_id,channel_id,event_date,new_users,old_users,all_users" \
--input-null-string '\\N' --input-null-non-string '\\N' \
--fields-terminated-by '\t' \
-m 1


# res_channel_num
${SQOOPPATH}/bin/sqoop export \
--connect jdbc:mysql://node03:3306/game_center \
--username root --password root \
--table res_channel_num --export-dir /user/hive/warehouse/game_center.db/res_channel_num  \
--columns "login_date,channel_id,device_num,reg_user,active_users,play_once_users,pay_users,pay_user,pay_money" \
--update-mode allowinsert \
--staging-table res_channel_num_temp --clear-staging-table \
--input-null-string '\\N' --input-null-non-string '\\N' \
--fields-terminated-by '\001' \
-m 1