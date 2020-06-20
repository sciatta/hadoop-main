package com.sciatta.hadoop.hive.gamedata.down;

import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sciatta.hadoop.hive.gamedata.util.TableNameUtil;
import org.apache.log4j.Logger;

public class DownloadData {

    private static Logger logger = Logger.getLogger(DownloadData.class);

    public void doDownLoad(String[] args) {

        // 线程个数
        int threadCount = Integer.parseInt(args[0]);

        // MySQL 连接超时时间设置
        int timeOut = Integer.parseInt(args[1]);

        // 使用的游戏
        String game = args[2];

        // mysql的地址文件路径（分库设计）
        // 要连接的文件地址，注意： 文件必须以\t制表符分割， 格式为： 平台id	区服id	Ip地址	端口号	用户名	密码	数据库名称
        String filePath = args[3];

        // 要下载的mysql表名
        // log_recharge
        String mysqlTable = args[4];

        // 表名后缀规则，name_2015_1 n_yyyy_m, name_201501 n_yyyymm, name n
        String rule = args[5];

        // 要拉取的字段, 使用注意事项，当拉取字段为 * 时，要把area_id,plat字段定义在hive表的最前面
        // String fields="\\*";
        // String fields = "platform_id,server_id,account,user_id,role_id,role_name,role_level,vip_level,time,item_rarity,item_id,item_num,reason,remark";
        String fields1 = args[6];
        String fields = fields1.replaceAll("\\\\", "");

        // 拉取的条件，注意，前后要加空格，这个时间要决定分表的月份的，所以如果是分月表必须知道时间
        // 可以使用 '2017-01-01'>='${startDate}' and '2001-01-01'<='${endDate}' 这种形式
        String where = args[7];

        // 下载的数据的存放目录，不要 / 结尾, 如果路径不存在，要创建
        String tmpDataPath = args[8];

        // 获取最终需要查询数据的表
        logger.info("filePath: " + filePath);
        logger.info("mysqlTable: " + mysqlTable);
        logger.info("rule: " + rule);
        logger.info("fields: " + fields);
        logger.info("where: " + where);
        logger.info("game: " + game);
        logger.info("tmpDataPath: " + tmpDataPath);

        // 获取统一数据库的不同间隔日期表
        Set<String> fixTables = TableNameUtil.fixTables(mysqlTable, rule, where);
        logger.info("要拉取的表为：" + fixTables);

        ConcurrentLinkedQueue<String> urls = DownloadUrl.getUrls(filePath, tmpDataPath, game, mysqlTable, where);
        logger.info("获取到文件的下载地址有: " + urls.size() + " 个");

        logger.info("开始拉取数据......请稍等......");
        long downloadStart = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(threadCount);

        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        DownloadTask task = new DownloadTask();
        task.setTaskCountQueue(timeOut, game, urls, latch, mysqlTable, fields, where, tmpDataPath, fixTables);

        for (int i = 0; i < threadCount; i++) {
            pool.submit(task);
        }

        try {
            latch.await(); // 使得主线程阻塞直到latch.countDown()为零才继续执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("拉取数据时间共消耗 ： " + (System.currentTimeMillis() - downloadStart) / 1000 + " 秒");

        // 任务处理完毕关闭线程池
        pool.shutdown();

        logger.info("连接数据成功的地址有 ： " + task.getSuccessUrl().size());
        logger.info("连接数据失败的地址有 ： " + task.getFailUrl().size());
        logger.info("写数据成功的地址有 ： " + task.getWriteUrl().size());
        logger.info("写数据失败的地址有 ： " + task.getWriteFailUrl().size());

        if (task.getFailUrl().size() != 0 || task.getWriteFailUrl().size() != 0) {

            logger.info("连接失败的地址有 ： ");
            for (String string : task.getFailUrl()) {
                logger.info(string);
            }

            logger.info("写数据失败的地址有 ： ");
            for (String string : task.getWriteFailUrl()) {
                logger.info(string);
            }

            logger.info(" -------拉取失败------- ");
            System.exit(-1);
        }

        logger.info(" -------拉取成功------- ");
    }
}
