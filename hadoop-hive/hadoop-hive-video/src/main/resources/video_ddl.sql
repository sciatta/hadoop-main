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

-- 执行过程 --
-- 1、原始数据上传
--      本地user（user.txt） -> /test/hive/video/user（user.txt）
--      本地videos(0.txt -- 4.txt) -> /test/hive/video/videos(0.txt -- 4.txt)

-- 2、com.sciatta.hadoop.hive.video.etl.ETLRunner
--      执行ETLRunner，利用MR清洗videos异常数据（ReduceTask=7）
--      /test/hive/video/videos(0.txt -- 4.txt) -> /test/hive/video/videos_etl（part-r-00000 -- part-r-00006）

-- 3、导入数据到ods（hdfs文件剪切）
--      /test/hive/video/user（user.txt） -> /user/hive/warehouse/video.db/ods_user（user.txt）
--      /test/hive/video/videos_etl（part-r-00000 -- part-r-00006） -> /user/hive/warehouse/video.db/ods_video（part-r-00000 -- part-r-00006）
load data inpath "/test/hive/video/videos_etl" into table ods_video;
load data inpath "/test/hive/video/user" into table ods_user;

-- 4、导入数据到dw
--      /user/hive/warehouse/video.db/ods_user（user.txt） -> /user/hive/warehouse/video.db/dw_user（000000_0 -- 000023_0） 24桶
--      /user/hive/warehouse/video.db/ods_video（part-r-00000 -- part-r-00006） -> /user/hive/warehouse/video.db/dw_video（000000_0 -- 000007_0） 8桶
insert overwrite table dw_video select * from ods_video;
insert overwrite table dw_user select * from ods_user;