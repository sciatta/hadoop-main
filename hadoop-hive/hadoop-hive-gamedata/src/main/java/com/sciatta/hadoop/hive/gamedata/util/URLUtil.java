package com.sciatta.hadoop.hive.gamedata.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangxiaoyu on 2020/6/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * URLUtil
 */
public class URLUtil {
    public static class StartEndDate {
        public String startDate;
        public String endDate;
    }

    public static StartEndDate getStartEndDate(String where) {
        Pattern pattern = Pattern.compile(" .*(.*)>='(.*)' and (.*)<='(.*)'.* ");
        Matcher matcher = pattern.matcher(where);

        StartEndDate startEndDate = new StartEndDate();

        // 搜索匹配
        while (matcher.find()) {
            startEndDate.startDate = matcher.group(2);
            startEndDate.endDate = matcher.group(4);
        }

        return startEndDate;
    }

    /**
     * 同一张表可以分布在不同数据库中，最后登记在同一个确认文件中
     *
     * @param tmpDataPath
     * @param game
     * @param mysqlTable
     * @param where
     * @return
     */
    public static String getDoneURL(String tmpDataPath, String game, String mysqlTable, String where) {
        String fileName;

        if (where.equals("1=1") || where.equals(" 1=1 ")) {
            fileName = mysqlTable + ".txt";
        } else {
            StartEndDate startEndDate = getStartEndDate(where);
            fileName = mysqlTable + "_" + startEndDate.startDate + "_" + startEndDate.endDate + ".txt";
        }

        return tmpDataPath + "/" + game + "/" + mysqlTable + "/done_url/" + fileName;
    }

    /**
     * 同一张表，不同数据库对应一个数据文件；即多个数据库会存在多个data文件，这个文件存放了所有日期跨度的数据
     *
     * @param tmpDataPath
     * @param game
     * @param mysqlTable
     * @param plat
     * @param area_id
     * @param ip
     * @param port
     * @param database
     * @return
     */
    public static String getDataURL(String tmpDataPath, String game, String mysqlTable,
                                    String plat, String area_id, String ip, String port, String database) {
        String fileName = plat + "_" + area_id + "_" + ip + "_" + port + "_" + database + ".data";
        return tmpDataPath + "/" + game + "/" + mysqlTable + "/data/" + fileName;
    }
}
