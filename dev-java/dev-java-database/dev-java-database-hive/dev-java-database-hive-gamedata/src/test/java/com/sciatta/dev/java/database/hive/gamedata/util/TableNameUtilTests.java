package com.sciatta.dev.java.database.hive.gamedata.util;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangxiaoyu on 2020/6/18<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * TableNameUtilTests
 */
public class TableNameUtilTests {
    @Test
    public void testGetDate() {
        Matcher matcher = getMatcher(" date(time)>='2020-06-17' and date(time)<='2020-06-18' ");

        String startDate = null;
        String endDate = null;
        // 搜索匹配
        while (matcher.find()) {
            int num = matcher.groupCount();
            for (int i = 0; i <= num; i++) {
                System.out.println("group" + i + "=" + matcher.group(i));
            }
        }
    }

    private Matcher getMatcher(String where) {
        String exp;

        // 0 组
        // exp = " .*>='.*' and .*<='.*' ";

        // 0、1、2 组
        // exp = " .*>='(.*)' and .*<='(.*)' ";

        exp = " .*(.*)>='(.*)' and (.*)<='(.*)'.* ";

        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(where);

        return matcher;
    }

    @Test
    public void testFixTables() {
        // 比最小日期小一个月，比最大日期大一个月
        Set<String> fixTables = TableNameUtil.fixTables("userlog", "n_yyyymm", " date(time)>='2020-06-17' and date(time)<='2020-07-18' ");
        assertEquals(4, fixTables.size());

        System.out.println(fixTables);
    }
}
