#!/bin/bash
for m in res_user_basic_count.sh res_user_pay.sh res_user_stay_long.sh res_user_lost.sh res_mission.sh res_channel_count.sh res_user_last_operate.sh res_virtual_coin.sh res_user_die_channel.sh res_user_share.sh res_user_pass.sh; do
  echo $m
  /usr/bin/sh $m
done
