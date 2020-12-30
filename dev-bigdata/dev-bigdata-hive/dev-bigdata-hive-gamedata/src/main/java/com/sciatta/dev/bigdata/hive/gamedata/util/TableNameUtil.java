package com.sciatta.dev.bigdata.hive.gamedata.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableNameUtil {
    public static Set<String> fixTables(String mysqlTable, String rule, String where) {

        Set<String> tables = new HashSet<>();

        if (where.equals("1=1") || where.equals(" 1=1 ")) {
            tables.add(mysqlTable);
            return tables;
        }

        // 根据正则获取到日期，然后计算日期之间的月份，并获取加一个月和减一个月的月份
        URLUtil.StartEndDate startEndDate = URLUtil.getStartEndDate(where);
        List<String> monthBetween = DateUtil.getMonthBetween(DateUtil.monthLess1(startEndDate.startDate), DateUtil.monthAdd1(startEndDate.endDate));

        if (rule.equals("n")) {
            tables.add(mysqlTable);
        } else if (rule.equals("n_yyyymm")) {
            for (String string : monthBetween) {
                String table = mysqlTable + "_" + string.replace("-", "");
                tables.add(table);
            }
        } else if (rule.equals("n_yyyy_m")) {
            for (String string : monthBetween) {
                String[] split = string.split("-");
                String month = split[0] + "_" + Integer.parseInt(split[1]);
                String table = mysqlTable + "_" + month;
                tables.add(table);
            }
        }

        return tables;
    }
}
