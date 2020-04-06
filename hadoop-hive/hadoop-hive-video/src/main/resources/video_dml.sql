-- 1、统计视频观看数Top10
select *
from dw_video
order by `views` desc
limit 10;

-- 2、统计视频类别热度Top10
select category_name as category, count(videoid) as hot
from dw_video
         lateral view explode(category) category_table as category_name
group by category_name
order by hot desc
limit 10;

-- 3、统计出视频观看数最高的20个视频的所属类别以及类别包含Top20视频的个数
select category_name as category, count(t1.videoid) as count
from (select videoid, category, `views` from dw_video order by `views` desc limit 20) t1
         lateral view explode(t1.category) category_table as category_name
group by category_name
order by count desc;

-- 4、 统计视频观看数Top50所关联视频的所属类别rank
select category, host, rank() over (order by host desc) as rank
from (select category_name as category, count(videoid) as host
      from (select t2.videoid, category
            from (select distinct relatedid_name as videoid
                  from (select relatedid, `views`
                        from dw_video
                        order by `views` desc
                        limit 50) t1
                           lateral view explode(relatedid) relatedid_table as relatedid_name) t2
                     left join dw_video on t2.videoid = dw_video.videoid) t3
               lateral view explode(category) category_table as category_name
      group by category_name) t4;

-- 5、统计每个类别中的视频热度Top10，以Music为例
select category, videoid, `views`, rank
from (select category_name                                             as category,
             videoid,
             `views`,
             rank() over (partition by category order by `views` desc) as rank
      from dw_video lateral view explode(category) category_table as category_name) t1
where category = 'Music'
  and rank <= 10;

-- 6、 统计每个类别中视频流量Top10，以Music为例
select *
from (select rank() over (partition by category order by ratings desc) as rank, category, videoid, ratings
      from (select category_name as category, videoid, ratings
            from dw_video lateral view explode(category) category_table as category_name) t1) t2
where category = 'Music'
  and rank <= 10;

-- 7、 统计上传视频最多的用户Top10以及他们上传的观看次数在前20的视频
select *
from (select *, row_number() over (partition by uploader order by `views` desc) as rank
      from (select dw_video.uploader, videoid, `views`
            from (select uploader, videos from dw_user order by videos desc limit 10) t1
                     join dw_video on t1.uploader = dw_video.uploader) t2) t3
where rank <= 20;

-- 8、统计每个类别视频观看数Top10
select *
from (select videoid,
             category_name,
             `views`,
             row_number() over (partition by category_name order by `views` desc) as rank
      from (select videoid, category_name, `views`
            from dw_video lateral view explode(category) category_table as category_name) t1) t2
where rank <= 10;



