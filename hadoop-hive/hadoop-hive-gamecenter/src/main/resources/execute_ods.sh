#!/bin/bash

odsshell=`ls ods*`

for m in $odsshell
do
 echo $m
 /usr/bin/sh $m
done