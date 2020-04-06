-- ods
-- 开启强制分桶
set hive.enforce.bucketing=true;

-- 创建video数据库
create database video;

use video;

-- 创建ods_video表
create table ods_video (
    videoId string,
    uploader string,
    age int,
    category array<string>,
    length int,
    views int,
    rate float,
    ratings int,
    comments int,
    relatedId array<string>)
row format delimited
    fields terminated by "\t"
    collection items terminated by "&"
stored as textfile;

-- 创建ods_user表
create table ods_user (
    uploader string,
    videos int,
    friends int)
row format delimited
    fields terminated by "\t"
stored as textfile;

-- dw
-- 创建dw_video表
create table dw_video (
    videoId string,
    uploader string,
    age int,
    category array<string>,
    length int,
    views int,
    rate float,
    ratings int,
    comments int,
    relatedId array<string>)
clustered by (uploader) into 8 buckets
row format delimited
    fields terminated by "\t"
    collection items terminated by "&"
stored as orc;

-- 创建dw_user表
create table dw_user (
    uploader string,
    videos int,
    friends int)
clustered by (uploader) into 24 buckets
row format delimited
    fields terminated by "\t"
stored as orc;

-- 导入数据到ods
load data inpath "/test/hive/video/videos_etl" into table ods_video;
load data inpath "/test/hive/video/user" into table ods_user;

-- 导入数据到dw
insert overwrite table dw_video select * from ods_video;
insert overwrite table dw_user select * from ods_user;