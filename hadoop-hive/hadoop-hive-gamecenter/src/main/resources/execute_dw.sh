#!/bin/bash

# dw表有依赖关系
for m in dw_role_login_merge.sh dw_user_basic_info.sh dw_user_behavior_login.sh dw_fight_power.sh dw_level_up.sh dw_user_behavior_info.sh
do
 echo $m
 /usr/bin/sh $m
done